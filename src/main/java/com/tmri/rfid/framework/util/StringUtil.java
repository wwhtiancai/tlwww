package com.tmri.rfid.framework.util;
import java.io.PrintWriter;
import java.io.StringWriter;
/**
 * Created by wuweihong on 2018/9/19.
 */
public class StringUtil{
	// 转换空
	public static String transBlank(String str){
		if(str==null||str.equals("null")||str.equals("NULL")||str.length()<1){
			return "";
		}else{
			return str;
		}
	}

	// 验证string 不是null且blank
	public static boolean checkBN(String value){
		boolean result=true;
		if(value==null||value.trim().length()==0||value.toLowerCase().equals("null")){
			result=false;
		}
		return result;
	}
	/**
	 * 转换Byte数组为16进制字符串
	 *
	 * @param b
	 *          byte数组
	 * @return 16进制字符串
	 */
	public static String byte2hex(byte[] b){
		StringBuffer hs=new StringBuffer();
		String stmp="";
		for(int n=0;n<b.length;n++){
			stmp=(java.lang.Integer.toHexString(b[n]&0XFF));
			if(stmp.length()==1){
				hs.append("0");
				hs.append(stmp);
			}else{
				hs.append(stmp);
			}
		}
		return hs.toString().toUpperCase();
	}
	/**
	 * 转换16进制字符串为byte数组
	 *
	 * @param strkey
	 *          16进制字符串
	 * @return byte[] byte数组
	 */
	public static byte[] hex2byte(String strkey){
		int keylength=strkey.length()/2;
		String strValue="";
		byte[] key=new byte[keylength];
		for(int i=0;i<keylength;i++){
			strValue=strkey.substring(2*i,2*(i+1));
			key[i]=Integer.valueOf(strValue,16).byteValue();
		}
		return key;
	}
	/** 获取异常中的输出 */
	public static String getErrorInfoFromException(Exception e){
		try{
			StringWriter sw=new StringWriter();
			PrintWriter pw=new PrintWriter(sw);
			e.printStackTrace(pw);
			String s=sw.toString().replaceAll("	","");
			if(s.length()>=2000){
				s=s.substring(0,2000)+"\r\n......";
			}
			return s;
		}catch(Exception e2){
			return "bad getErrorInfoFromException";
		}
	}
	/**
	 * 显示最大字符串quan zhoujn 20100609
	 */
	public static String displayMax(String value,int maxlength){
		String result="";
		if(value==null){
			result="";
		}else{
			if(value.length()>maxlength){
				result="<span style='cursor:hand' title='"+value+"'>"+value.substring(0,maxlength)+"...</span>";
			}else{
				result=value;
			}
		}
		return result;
	}

}
