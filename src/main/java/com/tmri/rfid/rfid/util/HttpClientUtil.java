package com.tmri.rfid.rfid.util;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import com.alibaba.fastjson.JSONObject;
import com.tmri.rfid.framework.util.DbResult;
/**
 * @author st
 * @Date 2017-10-19 8:59:04
 */
public class HttpClientUtil{
	public static String doPost(String url,Map<String,String> map){
		String charset="utf-8";
		return doPost(url,map,charset);
	}
	public static String doPost(String url,Map<String,String> map,String charset){
		HttpClient httpClient=null;
		HttpPost httpPost=null;
		String result=null;
		try{
			httpClient=new SSLClient();
			httpPost=new HttpPost(url);
			List<NameValuePair> list=new ArrayList<NameValuePair>();
			if(null!=map){
				Iterator iterator=map.entrySet().iterator();
				while(iterator.hasNext()){
					Entry<String,String> elem=(Entry<String,String>)iterator.next();
					list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
				}
				if(list.size()>0){
					UrlEncodedFormEntity entity=new UrlEncodedFormEntity(list,charset);
					httpPost.setEntity(entity);
				}
			}
			HttpResponse response=httpClient.execute(httpPost);
			if(response!=null){
				HttpEntity resEntity=response.getEntity();
				if(resEntity!=null){
					StringBuilder resultSb=new StringBuilder();// ��Ӧ����
					InputStream instream=resEntity.getContent();
					BufferedReader br=new BufferedReader(new InputStreamReader(instream,"utf-8"));
					String temp="";
					while((temp=br.readLine())!=null){
						resultSb.append(temp+"\n");
					}
					result=resultSb.toString();
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		if(result==null){
			DbResult dbResult=new DbResult();
			dbResult.setCode(0);
			result=JSONObject.toJSONString(dbResult);
		}
		return result;
	}
	public static void main(String[] args){
		
	}
}
