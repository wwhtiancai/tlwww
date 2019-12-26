package com.tmri.rfid.rfid.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tmri.rfid.framework.util.StringUtil;
import com.tmri.rfid.rfid.bean.FryDatas;

/**
 * @author st
 * @Date 2017-9-20 下午3:18:13
 */
public class DataExchangeUtil {

	public static int SJNRSIZE = 1000;//每个数据内容能存4000byte，若为中文，一个字符占4个，长度为1000
	public static int BHSIZE = 25;//14位编号+10位顺序号+1位存储类型（1为有子记录，2为没有子记录, 3为子记录）
	public static int SJNRCOUNT = 100;//数据内容字段数

	/**
	 * 将json数据转为数据交换格式
	 * @param sj
	 * @param sjbbh
	 * @param sy
	 * @param dx
	 * @return
	 */
	public static FryDatas parseSJNR(String sj , String sjbbh, int sy , int dx){
		int sjLeng = sj.length();

		FryDatas frydatas = new FryDatas();
		frydatas.setZt("1");
		frydatas.setSjbbh(sjbbh);
		frydatas.setSjbsy(BigDecimal.valueOf(sy));
		frydatas.setSjbdx(BigDecimal.valueOf(dx));

		Class<? extends FryDatas> myClass = frydatas.getClass();
		int sjnrCount = (int) Math.ceil((float)sjLeng/SJNRSIZE);//几个数据字段

		try {
			for(int i=sjnrCount ; i > 0 ;i--){
				Method m = myClass.getMethod("setSj"+i , String.class);
				m.invoke(frydatas, sj.substring(SJNRSIZE*(i-1)));
				if(i>1)sj = sj.substring(0,SJNRSIZE*(i-1));
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return frydatas;
	}

	/**
	 * 将字符串转化为list
	 * @param str
	 * @param sjbbh
	 * @return
	 */
	public static List<FryDatas> parseSJ(String str, String sjbbh){
		List<FryDatas> list = new ArrayList<FryDatas>();

		int sjLeng = str.length();//数据总长
		int dtLeng = SJNRSIZE*SJNRCOUNT;//单条记录的数据长度
		int sjCount = (int) Math.ceil((float)sjLeng/dtLeng);//几条记录
		for(int i=sjCount ; i > 0 ;i--){
			FryDatas fryDatas = parseSJNR(str.substring(dtLeng*(i-1)),sjbbh,i,sjCount);
			if(i>1)str = str.substring(0,dtLeng*(i-1));
			list.add(fryDatas);
		}
		return list;
	}


	/**
	 * 将FryDatas转化为str
	 * @param obj
	 * @return
	 */
	public static String toJSONString(FryDatas obj) {

		Class<? extends FryDatas> myClass = FryDatas.class;
		String returnStr = "";
		try {
			for(int i=1; i <= 100 ;i++){
				Method m = myClass.getMethod("getSj"+i , null);
				String str = (String) m.invoke(obj, null);
				if(StringUtil.checkBN(str)){
					returnStr += str;
				}else{
					break;//若为空，结束循环
				}
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnStr;
	}

	public static String toJSONString(JSONObject obj) {
		String returnStr = "";
		for(int i=1; i <= 100 ;i++){
			String str = obj.getString("sj"+i);
			if(StringUtil.checkBN(str)){
				returnStr += str;
			}else{
				break;//若为空，结束循环
			}
		}
		return returnStr;
	}


	/**
	 * 将list转化为str
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static String toJSONString(List<FryDatas> list) throws Exception {
		String returnStr = "";
		for(int i=0;i<list.size();i++){
			int sjbsy = list.get(i).getSjbsy().intValueExact();
			if(sjbsy == i+1){
				String str = toJSONString(list.get(i));
				returnStr += str;
			}else{
				throw new Exception("传输的数据包有遗漏，丢失的数据包索引为（"+ sjbsy +")");
			}
		}
		return returnStr;
	}

	public static String JSONArray2JSONString(JSONArray jsonArray) throws Exception {
		String returnStr = "";
		for(int i=0;i<jsonArray.size();i++){
			JSONObject obj = (JSONObject) jsonArray.get(i);
			int sjbsy = obj.getIntValue("sjbsy"); //getSjbsy().intValueExact();
			if(sjbsy == i+1){
				String str = toJSONString(obj);
				returnStr += str;
			}else{
				throw new Exception("传输的数据包有遗漏，丢失的数据包索引为（"+ sjbsy +")");
			}
		}
		return returnStr;
	}




	public static void main(String[] args){

		String str = "";
		for(int j=0;j<11;j++){
			for(int i = 0 ; i< 999; i++){
				str+= "1234567890";
			}
			str += "0000000000";
		}


		List<FryDatas> list = parseSJ(str,"1");

		System.out.println(JSON.toJSONString(list));

	}
}
