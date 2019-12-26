package com.tmri.rfid.company.ctrl;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.tmri.rfid.company.service.CpyLoginManager;
import com.tmri.rfid.framework.bean.Department;
import com.tmri.rfid.framework.bean.Fold;
import com.tmri.rfid.framework.bean.Program;
import com.tmri.rfid.framework.bean.SysUser;
import com.tmri.rfid.framework.bean.UserSession;
import com.tmri.rfid.framework.ctrl.SystemCtrl;
import com.tmri.rfid.framework.interceptor.Ajax;
import com.tmri.rfid.framework.util.CommonResponse;
import com.tmri.rfid.framework.util.DbResult;
import com.tmri.rfid.framework.util.FuncUtil;
import com.tmri.rfid.framework.util.StringUtil;


@Controller
public class CpyLoginCtrl extends SystemCtrl{
	@Autowired
	CpyLoginManager loginManager;
	
	@RequestMapping("/company/login.htm")
	@Ajax
	public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response,SysUser command) throws Exception{
		if(!StringUtil.checkBN(command.getYhzh())) {
			return CommonResponse.JSON(CommonResponse.NoInput);
		}
		if(!StringUtil.checkBN(command.getMm())) {
			return CommonResponse.JSON(CommonResponse.NoInput);
		}
		String strRemoteAddr=FuncUtil.getRemoteIpdz(request);
		DbResult dr=loginManager.validateSysuser(command);
		if(dr.getCode()==1) {
			SysUser sysuser=loginManager.getYhdh(dr.getMsg());
			sysuser.setIp(strRemoteAddr);
			UserSession userSession=new UserSession();
			Department department=systemManager.getDepartment(sysuser.getGlbm());
			if(department==null) {
				return CommonResponse.JSON("该用户没有管理部门！");
			}
			userSession.setSysuser(sysuser);
			userSession.setDepartment(department);
			userSession.setYhdh(sysuser.getYhdh());
			userSession.setGlbm(department.getGlbm());
			HttpSession session=request.getSession();
			session.setAttribute("userSession",userSession);
			HashMap<String,List> map=loginManager.getFoldProgramList(sysuser.getYhdh());
			if(map==null) {
				return CommonResponse.JSON("该用户没有授权菜单！");
			}
			String html="";
			html+="<div class=\"row banner\">\r\n";
			html+="	<div class=\"col-xs-8 font24 white\" style=\"padding:10px 20px;\"><i class=\"icon-credit-card mr5\"></i>汽车电子标识企业管理系统<small>（公共版）</small></div>\r\n";
			html+="		<div class=\"col-xs-3 tr\" style=\"padding:5px 10px;\">\r\n";
			html+="			<div class=\"btn-group\">\r\n";
			html+="				<button type=\"button\" class=\"btn btn-link dropdown-toggle font16\" style=\"color:#fff;text-decoration:none;\" data-toggle=\"dropdown\">"+userSession.getSysuser().getXm()+"&nbsp;<span class=\"caret\"></span></button>\r\n";
			html+="				<ul class=\"dropdown-menu\" role=\"menu\">\r\n";
			html+="					<li><a href=\"main.htm?method=index\">返回首页</a></li>\r\n";
			html+="					<li><a href=\"main.htm?method=editPassword\">修改密码</a></li>\r\n";
			html+="					<li class=\"divider\"></li>\r\n";
			html+="					<li><a href=\"main.htm?method=exit\">退出</a></li>\r\n";
			html+="				</ul>\r\n";
			html+="			</div>\r\n";
			html+="		</div>\r\n";
			html+="	<div class=\"col-xs-1\"></div>\r\n";
			html+="</div>\r\n";
			session.setAttribute("_user_top",html);
			html="";
			List<Program> programList=map.get("program");
			session.setAttribute("_programList",programList);
			List<Fold> foldList=map.get("fold");
			session.setAttribute("_foldList",foldList);
			for(Fold f:foldList) {
				html+="<ul class=\"list-group\">\r\n";
				html+="<li class=\"list-group-item active\"><b>"+f.getMlmc()+"</b></li>\r\n";
				for(Program p:programList) {
					if(p.getMldh().equals(f.getMldh()))
					html+="<li class=\"list-group-item\"><a href=\""+p.getYmdz()+"&cdbh="+p.getCdbh()+"\">"+p.getCxmc()+"</a></li>\r\n";
				}
				html+="</ul>\r\n";
			}
			session.setAttribute("_user_menus",html);
			
			return CommonResponse.JSON(dr);
		}else {
			return CommonResponse.JSON(dr.getMsg());
		}
	}
}