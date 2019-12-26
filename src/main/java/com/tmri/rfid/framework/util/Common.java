package com.tmri.rfid.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * @author WengYufeng
 * @Date:Apr 4, 2007 9:51:00 AM
 * 
 */
public class Common{
	
	
	public static String getNow(){
		Date now=new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(now);
	}
	public static String ScriptToHtml(String strJava){
		String r=null;
		if(strJava!=null){
			r=strJava.replaceAll("'","’");
			r=r.replaceAll("\r","");
			r=r.replaceAll("\n","");
			r=r.replaceAll("\\(","（");
			r=r.replaceAll("\\)","）");
		}
		return r;
	}
	public static String ScriptToText(String str){
		String r=null;
		if(str==null){
			r="";
		}else{
			r=str.replaceAll("<","〈");
			r=r.replaceAll(">","〉");
		}
		return r;
	}
	public static String TextToHtml(String str){
		String r="";
		if(str==null){
			r="";
		}else{
			r=str.replaceAll("\r\n","<br>");
		}
		return r;
	}
	public static String replace(String strOriginal,String strOld,String strNew){
		int i=0;
		StringBuffer strBuffer=new StringBuffer(strOriginal);
		while((i=strOriginal.indexOf(strOld,i))>=0){
			strBuffer.delete(i,i+strOld.length());
			strBuffer.insert(i,strNew);
			i=i+strNew.length();
			strOriginal=strBuffer.toString();
		}
		return strOriginal;
	}

	public static String translate(Map<String,String> map,String key){
		if(map==null||!map.containsKey(key)||map.get(key)==null)
			return key;
		else
			return (String)map.get(key);
	}
    
	public static String JavaToScript(String strJava){
		String r=null;
		if(strJava!=null){
			r=strJava.replaceAll("'","’");
			r=r.replaceAll("\r","");
			r=r.replaceAll("\n","");
			r=r.replaceAll("\\(","（");
			r=r.replaceAll("\\)","）");
			r=r.replaceAll("<","〈");
			r=r.replaceAll(">","〉");
		}
		return r;
	}
}
