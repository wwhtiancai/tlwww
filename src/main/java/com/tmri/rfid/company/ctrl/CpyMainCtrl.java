package com.tmri.rfid.company.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import com.tmri.rfid.company.service.CpyLoginManager;
import com.tmri.rfid.framework.bean.SysUser;
import com.tmri.rfid.framework.bean.UserSession;
import com.tmri.rfid.framework.ctrl.SystemCtrl;
import com.tmri.rfid.framework.interceptor.Ajax;
import com.tmri.rfid.framework.util.CommonResponse;
import com.tmri.rfid.framework.util.DbResult;
import com.tmri.rfid.framework.util.StringUtil;

@Controller
@RequestMapping("/company/main.htm")
public class CpyMainCtrl extends SystemCtrl{
	@Autowired
	CpyLoginManager loginManager;
	
	@RequestMapping(params="method=index")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String view="company/main";
		UserSession userSession=(UserSession)WebUtils.getSessionAttribute(request,"userSession");
		request.setAttribute("user",userSession.getSysuser());
		request.setAttribute("department",userSession.getDepartment());
		return new ModelAndView(view);
	}
	
	@RequestMapping(params="method=exit")
	public ModelAndView exit(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();
		session.invalidate();
		response.sendRedirect("index.htm");
		return null;
	}
	
	@RequestMapping(params="method=menu")
	public ModelAndView menu(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String view="";
		if(!StringUtil.checkBN(request.getParameter("yhzh"))) {
			CommonResponse.setAlerts(CommonResponse.NoInput,request);
			return new ModelAndView(view);
		}
		return new ModelAndView(view);
	}
	
	@RequestMapping(params="method=editPassword")
	public ModelAndView editPassword(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String view="company/password";
		UserSession userSession=(UserSession)WebUtils.getSessionAttribute(request,"userSession");
		request.setAttribute("user",userSession.getSysuser());
		request.setAttribute("department",userSession.getDepartment());
		return new ModelAndView(view);
	}
	
	@RequestMapping(params="method=savePassword")
	@Ajax
	public ModelAndView savePassword(HttpServletRequest request,HttpServletResponse response,SysUser command) throws Exception{
		UserSession userSession=(UserSession)WebUtils.getSessionAttribute(request,"userSession");
		if(!StringUtil.checkBN(command.getMm())){
			return CommonResponse.JSON(CommonResponse.NoInput);
		}
		if(!StringUtil.checkBN(command.getXm())){
			return CommonResponse.JSON(CommonResponse.NoInput);
		}
		command.setYhdh(userSession.getYhdh());
		DbResult result=loginManager.savePassword(command);
		return CommonResponse.JSON(result);
	}
}