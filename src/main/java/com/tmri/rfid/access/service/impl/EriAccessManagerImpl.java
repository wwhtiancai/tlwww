package com.tmri.rfid.access.service.impl;
import java.util.List;
import java.util.UUID;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.tmri.rfid.access.bean.AcsUser;
import com.tmri.rfid.access.bean.RfidAccessRequest;
import com.tmri.rfid.access.dao.EriAccessDao;
import com.tmri.rfid.access.mapper.EriAccessMapper;
import com.tmri.rfid.access.service.EriAccessManager;
import com.tmri.rfid.framework.service.impl.SystemManagerImpl;
import com.tmri.rfid.framework.util.DbResult;
import com.tmri.rfid.framework.util.StringUtil;
import com.tmri.rfid.rfid.bean.RfidApply;
import com.tmri.rfid.rfid.bean.RfidApplyDoc;
import com.tmri.rfid.rfid.mapper.RfidApplyMapper;
@Service
public class EriAccessManagerImpl extends SystemManagerImpl implements EriAccessManager{
	@Autowired
	private EriAccessDao eriAccessDao;
	@Autowired
	private EriAccessMapper eriAccessMapper;
	private RfidApplyMapper rfidApplyMapper;
	
	@Override
	public AcsUser getAcsUser(String xh) throws Exception{
		AcsUser cmd=new AcsUser();
		cmd.setXh(xh);
		List<AcsUser> list=eriAccessMapper.getAcsUserList(cmd);
		if(list!=null&&list.size()==1) {
			return list.get(0);
		}else {
			return null;
		}
	}
	
	public void saveLog(RfidAccessRequest log) throws Exception{
		int r=eriAccessMapper.saveAccessRequest(log);
		if(r!=1) {
			throw new RuntimeException("日志表数据新增错误，返回为："+String.valueOf(r));
		}
	}
	
	public DbResult doCommit(JSONObject obj) throws Exception{
		RfidApply app=new RfidApply();
		app.setSqbh(obj.getString("sqbh"));
		app.setHpzl(obj.getString("hpzl"));
		app.setHphm(obj.getString("hphm"));
		app.setKh(obj.getString("kh"));
		app.setXszh(obj.getString("ssz"));
		app.setYwdw(obj.getString("lrdw"));
		app.setYwdwmc(obj.getString("lrdwmc"));
		app.setLrr(obj.getString("lrr"));
		app.setLrrmc(obj.getString("lrrmc"));
		app.setSycplx(obj.getString("sycplx"));
		app.setSycpid(obj.getString("sycpid"));
		
		DbResult result=eriAccessDao.saveApply(app);
		if(result.getCode()==1) {
			if(StringUtil.checkBN(obj.getString("xsztp"))) {
				app.setXsztp(Base64.decodeBase64(obj.getString("xsztp")));
				result=eriAccessDao.saveApplyDoc(new RfidApplyDoc(app.getSqbh()+"11",app.getSqbh(),"11",app.getBsktp(),"1"));
				if(result.getCode()<0){
					throw new RuntimeException("异常："+result.getCode()+"，错误："+result.getMsg());
				}
			}
			if(StringUtil.checkBN(obj.getString("bsktp"))) {
				app.setBsktp(Base64.decodeBase64(obj.getString("bsktp")));
				result=eriAccessDao.saveApplyDoc(new RfidApplyDoc(app.getSqbh()+"12",app.getSqbh(),"12",app.getBsktp(),"1"));
				if(result.getCode()<0){
					throw new RuntimeException("异常："+result.getCode()+"，错误："+result.getMsg());
				}
			}
			if(StringUtil.checkBN(obj.getString("qctp"))) {
				app.setQctp(Base64.decodeBase64(obj.getString("qctp")));
				result=eriAccessDao.saveApplyDoc(new RfidApplyDoc(app.getSqbh()+"13",app.getSqbh(),"13",app.getBsktp(),"1"));
				if(result.getCode()<0){
					throw new RuntimeException("异常："+result.getCode()+"，错误："+result.getMsg());
				}
			}
		}
		if(result.getCode()<0){
			throw new RuntimeException("异常："+result.getCode()+"，错误："+result.getMsg());
		}
		return result;
	}
	
	public String doCheck(String[] datas) throws Exception{
		List<RfidApply> list=eriAccessMapper.getCheckList(datas);
		if(list==null||list.size()<1) {
			return "";
		}else {
			String r="";
			for(RfidApply bean:list) {
				r+=",{\"sqbh\": \""+bean.getSqbh()+"\",\"jg\": \""+bean.getClzt()+"\"}";
			}
			if(r.length()>1) {
				r="["+r.substring(1)+"]";
			}
			return r;
		}
	}
	
	public DbResult doUpdatePicture(JSONObject obj) throws Exception{
		RfidApply app=new RfidApply();
		DbResult result=null;
		app.setSqbh(obj.getString("sqbh"));
		if(StringUtil.checkBN(obj.getString("xsztp"))) {
			app.setXsztp(Base64.decodeBase64(obj.getString("xsztp")));
			result=eriAccessDao.saveApplyDoc(new RfidApplyDoc(UUID.randomUUID().toString().replaceAll("-",""),app.getSqbh(),"11",app.getXsztp(),"1"));
			if(result.getCode()<0){
				throw new RuntimeException("异常："+result.getCode()+"，错误："+result.getMsg());
			}
		}
		if(StringUtil.checkBN(obj.getString("bsktp"))) {
			app.setBsktp(Base64.decodeBase64(obj.getString("bsktp")));
			result=eriAccessDao.saveApplyDoc(new RfidApplyDoc(UUID.randomUUID().toString().replaceAll("-",""),app.getSqbh(),"12",app.getBsktp(),"1"));
			if(result.getCode()<0){
				throw new RuntimeException("异常："+result.getCode()+"，错误："+result.getMsg());
			}
		}
		if(StringUtil.checkBN(obj.getString("qctp"))) {
			app.setQctp(Base64.decodeBase64(obj.getString("qctp")));
			result=eriAccessDao.saveApplyDoc(new RfidApplyDoc(UUID.randomUUID().toString().replaceAll("-",""),app.getSqbh(),"13",app.getQctp(),"1"));
			if(result.getCode()<0){
				throw new RuntimeException("异常："+result.getCode()+"，错误："+result.getMsg());
			}
		}
		if(result==null) {
			result=new DbResult();
			result.setCode(1);
			result.setMsg("");
			result.setData("");
		}
		return result;
	}
	public DbResult doUpdate(JSONObject obj) throws Exception{
		RfidApply app=new RfidApply();
		DbResult result=null;
		app.setSqbh(obj.getString("sqbh"));
		if("0".equals(obj.getString("sqbh"))){
			RfidApply bean=rfidApplyMapper.get(app.getSqbh());
			if(bean!=null) {
				app.setTbyzt(bean.getZt());
			}
			app.setTbyy("92");
			app.setZt("9");
			result=eriAccessDao.saveOrDeleteApplyFK(app);
			if(result.getCode()<0){
				throw new RuntimeException("异常："+result.getCode()+"，错误："+result.getMsg());
			}		
		}else if("1".equals(obj.getString("sqbh"))){
			app.setKh(obj.getString("kh"));
			app.setSycplx(obj.getString("sycplx"));
			app.setSycpid(obj.getString("sycpid"));
			result=eriAccessDao.saveOrDeleteApplyFK(app);
			if(result.getCode()<0){
				throw new RuntimeException("异常："+result.getCode()+"，错误："+result.getMsg());
			}		
		}else {
			throw new RuntimeException("异常：操作类型不正确");
		}
		return result;
	}
}
