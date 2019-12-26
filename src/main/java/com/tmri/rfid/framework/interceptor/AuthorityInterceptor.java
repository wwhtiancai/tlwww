package com.tmri.rfid.framework.interceptor;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import com.tmri.rfid.framework.bean.Code;
import com.tmri.rfid.framework.bean.RestfulSession;
import com.tmri.rfid.framework.bean.Sysrun;
import com.tmri.rfid.framework.bean.UserSession;
import com.tmri.rfid.framework.dao.SystemDao;
import com.tmri.rfid.framework.util.CommonResponse;
import com.tmri.rfid.framework.util.StringUtil;

public class AuthorityInterceptor implements HandlerInterceptor {
	@Autowired
	protected SystemDao systemDao;
	
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
		doBeginCall(request);
		if(accreditList(handler,request)){
			return true;
		}
		Long timeout = (long) 1800000;//超时时间，30min
		UserSession userSession;
		String token = request.getParameter("token");
		if(StringUtil.checkBN(token)){
			// 有token，接口访问
			boolean flag=false;
			try{
				userSession=RestfulSession.getSession(token);
				if(userSession!=null){
					Calendar now=Calendar.getInstance();
					if(now.getTime().getTime()-Long.parseLong(userSession.getScdlsj())<timeout){// 如果该用户最后访问时间在30分钟以内
						flag=true;
						RestfulSession.renewal(token);
					}else{
						sendRedirectJSON(request,response,"本次登录已超时");
						return false;
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				sendRedirectJSON(request,response,"请重新登录");
			}
			if(!flag){
				sendRedirectJSON(request,response,"未登录");
				return false;
			}else{
				return true;
			}
		}else{
			//正常网页访问
			userSession=(UserSession)request.getSession().getAttribute("userSession");
			if(userSession==null){
				sendRedirect(request,response);
				return false;
			}
			return true;
		}
	}
	public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView modelAndView) throws Exception{
	}
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex) throws Exception{
		if(request.getAttribute("isCheckCode")!=null&&"1".equals(request.getAttribute("isCheckCode"))){
//			systemDao.abc();
		}
		if(exceptList(handler,request)){
			return;
		}else {
			doEndCall(request);	
		}
	}
	private void sendRedirectJSON(HttpServletRequest request,HttpServletResponse response,String msg){
		try{
			System.out.println("请求错误的地址！"+request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getRequestURI()+"?"+request.getQueryString());
			Map<String,Object> resultMap=new HashMap<String,Object>();
			resultMap.put("resultId","01");
			resultMap.put("resultMsg",msg);
			String resultStr=JSON.toJSONString(resultMap);
			response.setContentType("text/plain; charset=utf-8");
			response.getWriter().write(resultStr);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private void sendRedirect(HttpServletRequest request,HttpServletResponse response){
		try{
			if(request.getQueryString()==null) {
				System.out.println("无法请求的错误地址！"+request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getRequestURI());
			}else {
				System.out.println("无法请求的错误地址！"+request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getRequestURI()+"?"+request.getQueryString());
			}
			response.sendRedirect("index.html");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	/** 白名单，无SESSION也给予通过 */
	private boolean accreditList(Object handler,HttpServletRequest request){
		try{
			String className=((HandlerMethod)handler).getBean().getClass().getName();
			List<Code> whitelist=(List<Code>)request.getSession().getServletContext().getAttribute("whitelist");
			if(whitelist!=null&&whitelist.size()>0){
				for(Code cd:whitelist){
					if(className.indexOf(cd.getDmsm1())>=0){
//						if(request.getQueryString()==null) {
//							System.out.println("无需验证的请求地址！"+request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getRequestURI());
//						}else {
//							System.out.println("无需验证的请求地址！"+request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getRequestURI()+"?"+request.getQueryString());
//						}
						return true;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	/** 红名单，不记录访问日志 */
	private boolean exceptList(Object handler,HttpServletRequest request){
		try{
			String className=((HandlerMethod)handler).getBean().getClass().getName();
			List<Code> redlist=(List<Code>)request.getSession().getServletContext().getAttribute("redlist");;
			if(redlist!=null&&redlist.size()>0){
				for(Code cd:redlist){
					if(StringUtil.checkBN(cd.getDmsm2())){
						if(className.indexOf(cd.getDmsm1())>=0&&cd.getDmsm2().equals(request.getParameter("method"))){
							return true;
						}
					}else{
						if(className.indexOf(cd.getDmsm1())>=0){
							return true;
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	private void doBeginCall(HttpServletRequest request){
		HttpSession session=request.getSession();
		session.setAttribute("timer",System.currentTimeMillis());
	}
	private void doEndCall(HttpServletRequest request){
		HttpSession session=request.getSession();
		long start=Long.parseLong(String.valueOf(session.getAttribute("timer")));
		long end=System.currentTimeMillis();
		Sysrun run=new Sysrun();
		UserSession userSession=(UserSession)request.getSession().getAttribute("userSession");
		if(userSession!=null){
			run.setYhdh(userSession.getSysuser().getYhdh());
			run.setGlbm(userSession.getDepartment().getGlbm());
			if(session.getAttribute("_user_menuid")!=null){
				run.setCdbh((String)session.getAttribute("_user_menuid"));
			}
		}
		if(request.getQueryString()==null){
			run.setFwym(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getRequestURI());
		}else{
			run.setFwym(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getRequestURI()+"?"+request.getQueryString());	
		}
		run.setIp(CommonResponse.getRemoteIpdz(request));
		run.setFwys(new BigDecimal(end-start));
		try {
			systemDao.insertSysrun(run);	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
