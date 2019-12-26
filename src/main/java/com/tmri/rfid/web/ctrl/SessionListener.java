package com.tmri.rfid.web.ctrl;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by wuweihong on 2018/9/19.
 */
public class SessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent arg0){
        try{
            ApplicationContext ctx=null;
            if(ctx==null){
                ctx= WebApplicationContextUtils.getRequiredWebApplicationContext(arg0.getSession().getServletContext());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sessionDestroyed(HttpSessionEvent arg0){
        try{
            ApplicationContext ctx=null;
            if(ctx==null){
                ctx=WebApplicationContextUtils.getRequiredWebApplicationContext(arg0.getSession().getServletContext());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
