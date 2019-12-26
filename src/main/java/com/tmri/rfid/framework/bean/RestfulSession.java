package com.tmri.rfid.framework.bean;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
/**
 * @author st
 * @Date 2017-11-13 9:56:45
 */
public class RestfulSession{
	private static Map<String,UserSession> session=new HashMap<String,UserSession>(10000);
	private static Map<String,String> clients=new HashMap<String,String>();
	public static UserSession getSession(String sessionId){
		return session.get(sessionId);
	}
	public static void setSession(String sessionId,UserSession clientUser){
		String yhdh=clientUser.getYhdh();
		String token=clients.get(yhdh);
		if(StringUtils.isNotEmpty(token)){
			session.remove(token);
		}
		session.put(sessionId,clientUser);
		clients.put(clientUser.getYhdh(),sessionId);
	}
	public static void renewal(String sessionId){
		session.get(sessionId).setScdlsj(new Date().getTime()+"");
	}
}
