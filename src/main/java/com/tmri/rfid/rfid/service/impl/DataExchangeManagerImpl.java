package com.tmri.rfid.rfid.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tmri.rfid.framework.interceptor.AdditiveAttribute;
import com.tmri.rfid.framework.service.ExtendsSystemManager;
import com.tmri.rfid.framework.service.impl.SystemManagerImpl;
import com.tmri.rfid.framework.util.DbResult;
import com.tmri.rfid.framework.util.StringUtil;
import com.tmri.rfid.rfid.bean.*;
import com.tmri.rfid.rfid.mapper.BaseMapper;
import com.tmri.rfid.rfid.mapper.FryCommandsMapper;
import com.tmri.rfid.rfid.mapper.FryDatasMapper;
import com.tmri.rfid.rfid.mapper.FryFeedbacksMapper;
import com.tmri.rfid.rfid.service.DataExchangeManager;
import com.tmri.rfid.rfid.util.DataExchangeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by stone on 2018/12/19.
 */
@Service
@Transactional
public class DataExchangeManagerImpl extends SystemManagerImpl implements DataExchangeManager {


    @Autowired
    private BaseMapper baseMapper;
    @Autowired
    private FryCommandsMapper fryCommandsMapper;
    @Autowired
    private FryDatasMapper fryDatasMapper;
    @Autowired
    private FryFeedbacksMapper fryFeedbacksMapper;
    @Autowired
    protected ExtendsSystemManager systemManager;

    @Override
    public List<FryCommands> getDataExchange(String sjbbh) {
        List<FryCommands> list = fryCommandsMapper.getListBySjbbh(sjbbh);

        return list;
    }

    public LinkedHashMap<String, Object> getObjectByCommands(FryCommands fryCommands){
        String zj = fryCommands.getZj();
        String zjz = fryCommands.getZjz();

        String[] zjList = zj.split(",");
        String[] zjzList = zjz.split(",");

        String zjSql = "";
        for(int i = 0; i<zjList.length ;i++){
            zjSql += " and " + zjList[i] + " ='" + zjzList[i] + "'";
        }
        zjSql = zjSql.substring(4);
        String sql = "select * from " + fryCommands.getBm() + " where" + zjSql;
        LinkedHashMap<String, Object> list = baseMapper.selectById(sql);

        String zds = fryCommands.getZd();
        String[] zdArr = zds.split(",");

        for(int j = 0; j<zdArr.length ;j++){
            String zd = zdArr[j];
            String[] zdList = zd.split(":");
            if(zdList[0].equals("B")){
                String item = zdList[1];
                String table = fryCommands.getBm();
                BlobBean blob = baseMapper.selectBlobById(table,item,zjSql);
                //以byte[] 方式取出blog类型的数据
                //String blobStr = new String((byte[])blob.getWd());
                BASE64Encoder encoder = new BASE64Encoder();
                String blobStr = encoder.encode(blob.getWd());

                list.replace(zdList[1],blobStr);
            }
        }


        return list;
    }

    /**
     * 根据sjbbh获取需要传输的数据并转化为json
     */
    @Override
    public String getExchangeJSON(String sjbbh)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        List<FryCommands> list = this.getDataExchange(sjbbh);

        for(int i=0;i<list.size();i++){
            FryCommands fryCommands = list.get(i);
            //--------------------根据commands获取具体数据--------------------------------
            LinkedHashMap<String, Object> obj = this.getObjectByCommands(fryCommands);

            String jsonStr = JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteDateUseDateFormat);//,SerializerFeature.WriteMapNullValue
            fryCommands.setSj(jsonStr);
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("sjbbh", sjbbh);
        map.put("commands", list);
        String json = JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteDateUseDateFormat);

        return json;
    }

    @Override
    public boolean exchangeData(String sjbbh) throws Exception{
        String json = this.getExchangeJSON(sjbbh);
        //System.out.println("json:"+json);
        List<FryDatas> dataList = DataExchangeUtil.parseSJ(json,sjbbh);
        long id = fryDatasMapper.insertFryDatasBatch(dataList);
        System.out.println("id:"+id);
        //修改frycommands状态为"2"已发送
        fryCommandsMapper.updateZt(sjbbh);

        return true;
    }

    @Override
    public boolean decodeData(String sjbbh,ApplicationContext ctx) throws Exception{
        List<FryDatas> list = fryDatasMapper.getBySjbbh(sjbbh);

        if(null != list){
            if(list.size() > list.get(0).getSjbdx().intValue()){
                System.out.println("sjbbh（"+sjbbh+"）的任务获取到的数据数超过数据包大小!");
            }else if(list.size() > list.get(0).getSjbdx().intValue()){
                System.out.println("sjbbh（"+sjbbh+"）的任务还未传输完成!");
            }else{//个数与sjbdx一致
                try {
                    String str = DataExchangeUtil.toJSONString(list);
                    //System.out.println("jsonStr:"+str);
                    parseDataStr(str,ctx);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }




        return true;
    }


    public void parseDataStr(String sj,ApplicationContext ctx) throws Exception{
        JSONObject obj = (JSONObject) JSONObject.parseObject(sj);
        String sjbbhStr = obj.getString("sjbbh");
        JSONArray commandsList = (JSONArray) obj.get("commands");

        DbResult result = null;
        String code = "0";

        for(int i=0; i<commandsList.size() ;i++){
            JSONObject jsonObj = (JSONObject) commandsList.get(i);

            /*if(StringUtil.checkBN(jsonObj.getString("ccgc"))){
                result=dataExchangeDao.callCcgc(jsonObj);
                code=result.getCode()+"";
                if(!code.equals("1")){
                    String resMgs="Êý¾Ý°ü±àºÅÎª'"+sjbbhStr+"',jhxhÎª'"+jsonObj.getString("jhxh")+"'µÄÊý¾Ý½»»»´æ´¢¹ý³Ìµ÷ÓÃÊ§°Ü£¡Ô­ÒòÎª£º"+result.getMsg();
                    result.setMsg(resMgs);
                    break;
                }
            }
            if(StringUtil.checkBN(jsonObj.getString("hdhs"))){
                //result=dataExchangeDao.callHdhs(jsonObj,ctx);
                code=result.getCode()+"";
                if(!code.equals("1")){
                    String resMgs="Êý¾Ý°ü±àºÅÎª'"+sjbbhStr+"',jhxhÎª'"+jsonObj.getString("jhxh")+"'µÄÊý¾Ý½»»»»Øµ÷º¯Êýµ÷ÓÃÊ§°Ü£¡Ô­ÒòÎª£º"+result.getMsg();
                    result.setMsg(resMgs);
                    break;
                }
            }
            if(!StringUtil.checkBN(jsonObj.getString("ccgc"))&&!StringUtil.checkBN(jsonObj.getString("hdhs"))){
                code=this.setObject(jsonObj);
            }*/
            code=this.setObject(jsonObj);
        }


        //处理完一个数据包，新建feedback，删除fry_datas
        FryFeedbacks feedbacks = new FryFeedbacks();
        feedbacks.setSjbbh(sjbbhStr);
        feedbacks.setZt("1");
        feedbacks.setCzjg(code);
        if(null!=result)feedbacks.setCwnr(result.getMsg());
        fryFeedbacksMapper.insertFryFeedbacks(feedbacks);
        fryDatasMapper.deleteBySjbbh(sjbbhStr);
        /*int EXCHANGETYPE = FTPExchangeUtil.EXCHANGETYPE;
        if(EXCHANGETYPE == FTPExchangeUtil.DATABASE_EXCHANGE){//数据库交换方式
            result=systemDao.setOracleData("fry_setdata_pkg.setFRY_FEEDBACKS",feedbacks);
            if(result.getCode()==1){
                result=systemDao.save("fry_pkg.saveFRY_FEEDBACKS");
            }
        }else if(EXCHANGETYPE == FTPExchangeUtil.FTP_EXCHANGE){//FTP交换方式
            String json = JSON.toJSONString(feedbacks);
            String fileName = EriUtil.generateUniqueSerialNo()+"_FB";
            FTPExchangeUtil.saveDataByFTP(json,fileName,sjbbhStr.substring(0, 4));
        }*/
    }

    public String setObject(JSONObject jsonObj) throws Exception {

        String code = "0";
        String fzl = jsonObj.getString("fzl");
        String bm = jsonObj.getString("bm");
        String zd = jsonObj.getString("zd");
        String zj = jsonObj.getString("zj");
        String zjz = jsonObj.getString("zjz");
        String sjStr = jsonObj.getString("sj");

        JSONObject jsonObject = JSONObject.parseObject(sjStr);

        final String[] zdList = zd.split(",");
        String[] zjList = zj.split(",");
        String[] zjzList = zjz.split(",");

        String zjSql = "";
        for(int i = 0; i<zjList.length ;i++){
            zjSql += " and " + zjList[i] + " ='" + zjzList[i] + "'";
        }
        zjSql = zjSql.substring(4);

        String querySql = "select count(*) from " + bm + " where" + zjSql;
        int count = baseMapper.countSql(querySql);
        if(count > 1){
            throw new Exception("数据库表（"+bm+"）已存在多个主键（"+zj+"）为（"+zjz+"）的数据");
        }else if(count == 0) {//没有数据插入

            String fieldStr = "(";
            String valueStr = "(";

            for(int j=0;j<zdList.length;j++){
                String fieldType = zdList[j].substring(0, 1);
                String fieldName = zdList[j].substring(2);
                Object fieldValue = jsonObject.get(fieldName);

                if(null != fieldValue){
                    //字段不为BLOB类型的时候，插入
                    if(!fieldType.equals("B")){
                        fieldStr += fieldName + ", ";
                    }

                    if(fieldType.equals("D")){
                        valueStr += "to_date('"+fieldValue+"','yyyy-mm-dd HH24:mi:ss'), ";
                    }else if(fieldType.equals("V")){
                        valueStr += "'" + fieldValue + "', ";
                    }else if(fieldType.equals("N")){
                        valueStr += fieldValue + ", ";
                    }else{

                    }
                }

            }

            fieldStr = fieldStr.substring(0,fieldStr.length()-2) + ") ";
            valueStr = valueStr.substring(0,valueStr.length()-2) + ") ";

            String insertSql = "insert into " + bm + " " + fieldStr + " values"+valueStr;
            //jdbcTemplate.execute(insertSql);
            System.out.println("insertSql:"+insertSql);
            baseMapper.insertSql(insertSql);

        }else{//更新
            String setStr = "";

            for(int j=0;j<zdList.length;j++){
                String fieldType = zdList[j].substring(0, 1);
                String fieldName = zdList[j].substring(2);
                Object fieldValue = jsonObject.get(fieldName);

                if(null != fieldValue){
                    if(fieldType.equals("D")){
                        setStr += fieldName + "=to_date('"+jsonObject.get(fieldName)+"','yyyy-mm-dd HH24:mi:ss'), ";
                    }else if(fieldType.equals("V")){
                        setStr += fieldName + "='" + jsonObject.get(fieldName)+"', ";
                    }else if(fieldType.equals("N")){
                        setStr += fieldName + "=" + jsonObject.get(fieldName)+", ";
                    }
                }
            }

            setStr = setStr.substring(0,setStr.length()-2) + " ";

            String updateSql = "update " + bm + " set " + setStr + " where" + zjSql;
            //jdbcTemplate.execute(insertSql);
            System.out.println("updateSql:"+updateSql);
            baseMapper.updateSql(updateSql);

        }

        for(int k=0;k<zdList.length;k++){
            String fieldType = zdList[k].substring(0, 1);
            String fieldName = zdList[k].substring(2);
            Object fieldValue = jsonObject.get(fieldName);

            if(null != fieldValue){
                if(fieldType.equals("B")){
                    BlobBean blobBean = new BlobBean();
                    blobBean.setTable(bm);
                    blobBean.setItem(fieldName);
                    blobBean.setZjSql(zjSql);
                    String wd = (String) fieldValue;
                    BASE64Decoder decoder = new BASE64Decoder();
                    byte[] bytes = decoder.decodeBuffer(wd);
                    blobBean.setWd(bytes);
                    baseMapper.updateBlobById(blobBean);
                }
            }
        }

        return "1";
    }

}
