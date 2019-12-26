package com.tmri.rfid.company.ctrl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.tmri.rfid.company.bean.CpyRfidApply;
import com.tmri.rfid.company.service.CpyQueryManager;
import com.tmri.rfid.framework.ctrl.SystemCtrl;
import com.tmri.rfid.framework.util.CommonResponse;
import com.tmri.rfid.framework.util.StringUtil;

@Controller
@RequestMapping("/company/query.htm")
public class CpyQueryCtrl extends SystemCtrl{
	@Autowired
	CpyQueryManager cpyQueryManager;
	
	@RequestMapping(params="method=forwardQueryFlow")
	public ModelAndView forwardQueryFlow(HttpServletRequest request,HttpServletResponse response,CpyRfidApply command) throws Exception{
		String view="company/queryFlowList";
		request.setAttribute("hpzl",this.systemManager.getCodes("00","1007"));
		return new ModelAndView(view);
	}
	@RequestMapping(params="method=listQueryFlow")
	public ModelAndView listQueryFlow(HttpServletRequest request,HttpServletResponse response,CpyRfidApply command) throws Exception{
		String view="company/queryFlowList";
		request.setAttribute("hpzl",this.systemManager.getCodes("00","1007"));
		List<CpyRfidApply> queryList=cpyQueryManager.getQueryFlowList(command);
		request.setAttribute("queryList",queryList);
		request.setAttribute("command",command);
		return new ModelAndView(view);
	}
	@RequestMapping(params="method=detailQueryFlow")
	public ModelAndView detailQueryFlow(HttpServletRequest request,HttpServletResponse response,CpyRfidApply command) throws Exception{
		String view="company/queryFlowDetail";
		if(!StringUtil.checkBN(command.getSqbh())){
			CommonResponse.setAlerts(CommonResponse.NoInput,true,request);
			return new ModelAndView(view);
		}
		
		return new ModelAndView(view);
	}
}