package com.tmri.rfid.company.ctrl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tmri.rfid.company.bean.CpyRfidApply;
import com.tmri.rfid.company.service.CpyFlowManager;
import com.tmri.rfid.framework.bean.UserSession;
import com.tmri.rfid.framework.ctrl.SystemCtrl;
import com.tmri.rfid.framework.interceptor.Ajax;
import com.tmri.rfid.framework.util.Common;
import com.tmri.rfid.framework.util.CommonResponse;
import com.tmri.rfid.framework.util.DbResult;
import com.tmri.rfid.framework.util.StringUtil;
import com.tmri.rfid.rfid.service.RfidApplyManager;
import com.tmri.rfid.web.bean.RfidEri;
@Controller
@RequestMapping("/company/flow.htm")
public class CpyFlowCtrl extends SystemCtrl{
	@Autowired
	CpyFlowManager cpyFlowManager;


	@RequestMapping(params="method=queryCheck")
	public ModelAndView queryCheck(HttpServletRequest request,HttpServletResponse response,CpyRfidApply command) throws Exception{
		String view="company/checkList";
		request.setAttribute("hpzl",this.systemManager.getCodes("00","1007"));
		if(!StringUtil.checkBN(command.getKssj())){
			command.setKssj(Common.getNow().substring(0,10));
		}
		if(!StringUtil.checkBN(command.getJssj())){
			command.setJssj(Common.getNow().substring(0,10));
		}
		UserSession userSession=(UserSession)WebUtils.getSessionAttribute(request,"userSession");
		command.setYwdw(userSession.getGlbm());
		List<CpyRfidApply> queryList=cpyFlowManager.getCheckList(command);
		request.setAttribute("queryList",queryList);
		request.setAttribute("command",command);
		return new ModelAndView(view);
	}
	@RequestMapping(params="method=detailCheck")
	public ModelAndView detailCheck(HttpServletRequest request,HttpServletResponse response,CpyRfidApply command) throws Exception{
		String view="company/checkDetail";
		if(!StringUtil.checkBN(command.getSqbh())){
			CommonResponse.setAlerts(CommonResponse.NoInput,true,request);
			return new ModelAndView(view);
		}
		UserSession userSession=(UserSession)WebUtils.getSessionAttribute(request,"userSession");
		CpyRfidApply bean=cpyFlowManager.getCheck(command.getSqbh(),userSession.getGlbm());
		request.setAttribute("bean",bean);
		return new ModelAndView(view);
	}
	@RequestMapping(params="method=auditCheck")
	@Ajax
	public ModelAndView auditCheck(HttpServletRequest request,HttpServletResponse response,CpyRfidApply command) throws Exception{
		UserSession userSession=(UserSession)WebUtils.getSessionAttribute(request,"userSession");
		if(!StringUtil.checkBN(command.getSqbh())){
			return CommonResponse.JSON(CommonResponse.NoInput);
		}
		if(!StringUtil.checkBN(command.getZt())){
			return CommonResponse.JSON(CommonResponse.NoInput);
		}
		boolean isAudit=false;
		if("1".equals(command.getZt())){
			isAudit=true;
		}
        DbResult result=cpyFlowManager.auditCheck(command.getSqbh(),isAudit,userSession,request);
		return CommonResponse.JSON(result);
	}
	@RequestMapping(params="method=queryPageInfo")
	public ModelAndView queryCancel(@RequestParam(required=false,defaultValue="1") Integer startPage,@RequestParam(required=false,defaultValue="5") Integer PageSize,HttpServletRequest request,HttpServletResponse response,CpyRfidApply command) throws Exception{
		String view="company/cancelList1";
		PageHelper.startPage(startPage,PageSize,true);
		List<CpyRfidApply> queryList=cpyFlowManager.getCancelList(command);
		PageInfo<CpyRfidApply> pi=new PageInfo<>(queryList);
		request.setAttribute("queryList",pi.getList());
		request.setAttribute("controller",getPageInfo(pi,request));
		return new ModelAndView(view);
	}
	
	/*
	@RequestMapping("/getAllUser")
	@ResponseBody
	public List<RfidApply> getAllUser(@RequestParam(required=false,defaultValue="1") Integer startPage,@RequestParam(required=false,defaultValue="5") Integer PageSize,HttpServletRequest request) throws Exception{
		PageHelper.startPage(startPage,PageSize);
		RfidApply command=new RfidApply();
		List<RfidApply> queryList=cpyFlowManager.getCheckList(command);
		PageInfo<RfidApply> pi=new PageInfo<>(queryList);
		return queryList;
	}
	@RequestMapping(params="method=forwardCancel")
	public ModelAndView forwardCancel(HttpServletRequest request,HttpServletResponse response,RfidApply command) throws Exception{
		String view="company/cancelList";
		return new ModelAndView(view);
	}
	@RequestMapping(params="method=queryCancel")
	public ModelAndView queryCancel(@RequestParam(required=false,defaultValue="1") Integer startPage,@RequestParam(required=false,defaultValue="5") Integer PageSize,HttpServletRequest request,HttpServletResponse response,RfidApply command) throws Exception{
		PageHelper.startPage(startPage,PageSize);
		String view="company/cancelList1";
		if(!StringUtil.checkBN(command.getKssj())){
			command.setKssj(Common.getNow().substring(0,10));
		}
		if(!StringUtil.checkBN(command.getJssj())){
			command.setJssj(Common.getNow().substring(0,10));
		}
		UserSession userSession=(UserSession)WebUtils.getSessionAttribute(request,"userSession");
		command.setYwdw(userSession.getGlbm());
		List<RfidEri> queryList=rfidApplyManager.queryList();
		PageInfo<RfidEri> pi=new PageInfo<>(queryList);
		request.setAttribute("queryList",pi.getList());
		request.setAttribute("command",command);
		request.setAttribute("controller",getPageInfo(pi,request));
		return new ModelAndView(view);
	}
	@RequestMapping(params="method=detailCancel")
	public ModelAndView detailCancel(HttpServletRequest request,HttpServletResponse response,RfidApply command) throws Exception{
		String view="company/cancelDetail";
		if(!StringUtil.checkBN(command.getSqbh())){
			CommonResponse.setAlerts(CommonResponse.NoInput,true,request);
			return new ModelAndView(view);
		}
		request.setAttribute("tbyy",this.systemManager.getCodes("81","1120"));
		UserSession userSession=(UserSession)WebUtils.getSessionAttribute(request,"userSession");
		RfidApply bean=cpyFlowManager.getCancel(command.getSqbh(),userSession.getGlbm());
		request.setAttribute("bean",bean);
		return new ModelAndView(view);
	}
	@RequestMapping(params="method=saveCancel")
	@Ajax
	public ModelAndView saveCancel(HttpServletRequest request,HttpServletResponse response,RfidApply command) throws Exception{
		UserSession userSession=(UserSession)WebUtils.getSessionAttribute(request,"userSession");
		if(!StringUtil.checkBN(command.getSqbh())){
			return CommonResponse.JSON(CommonResponse.NoInput);
		}
		command.setGdr(userSession.getYhdh());
		command.setGdrmc(userSession.getSysuser().getXm());
		command.setYwdw(userSession.getGlbm());
		DbResult result=cpyFlowManager.saveCancel(command);
		return CommonResponse.JSON(result);
	}
	*/
}