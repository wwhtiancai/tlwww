package com.tmri.rfid.web.ctrl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmri.rfid.rfid.service.RfidApplyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.tmri.rfid.framework.ctrl.SystemCtrl;
import com.tmri.rfid.framework.util.CommonResponse;
import com.tmri.rfid.framework.util.StringUtil;
import com.tmri.rfid.web.bean.RfidEri;
import com.tmri.rfid.web.service.RfidEriManager;


@Controller
@RequestMapping("/m/eri.htm")
public class MEriCtrl extends SystemCtrl{
	@Autowired
	private RfidEriManager rfidEriManager;
	@Autowired
	private RfidApplyManager rfidApplyManager;
	
	@RequestMapping(params="method=forwardCard")
	public ModelAndView forwardCard(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String view="m/rfid/cardMain";
		return new ModelAndView(view);
	}
	
	@RequestMapping(params="method=getCard")
	public ModelAndView getCard(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String view="m/rfid/cardDetail";
		String kh=request.getParameter("id");
		if(!StringUtil.checkBN(kh)) {
			request.setAttribute("error",CommonResponse.NoInput);
			return new ModelAndView(view);
		}
		if(kh.length()==115) {
			request.setAttribute("showprivate","1");
		}else {
			request.setAttribute("showprivate","0");
		}
		if(kh.length()>13) {
			kh=kh.substring(0,12);
		}
		RfidEri eri=rfidEriManager.getEri(kh);
		if(eri==null||!StringUtil.checkBN(eri.getTid())) {
			request.setAttribute("error","该汽车标识标识卡不存在！");
			return new ModelAndView(view);
		}else {
			request.setAttribute("eri",eri);
		}
		if(StringUtil.checkBN(request.getParameter("back"))){
			request.setAttribute("back","1");	
		}else {
			request.setAttribute("back","0");
		}
		return new ModelAndView(view);
	}
	
	@RequestMapping(params="method=forwardApply")
	public ModelAndView forwardApply(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String view="m/rfid/applyMain";
		return new ModelAndView(view);
	}
	
	@RequestMapping(params="method=listApply")
	public ModelAndView listApply(HttpServletRequest request,HttpServletResponse response,RfidEri command) throws Exception{
		String view="m/rfid/applyDetail";
		RfidEri eri=rfidApplyManager.checkApply(command.getKh());
//        System.out.print(eri.getTid());
		if(eri==null) {
			request.setAttribute("msg","");
		}else{
			if("6".equals(eri.getZt())){
				
			}
		}
		return new ModelAndView(view);
	}
}