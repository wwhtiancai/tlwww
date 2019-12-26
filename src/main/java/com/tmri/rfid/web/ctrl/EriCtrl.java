package com.tmri.rfid.web.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/eri.htm")
public class EriCtrl extends SystemCtrl{
	@Autowired
	private RfidEriManager rfidEriManager;
	
	@RequestMapping(params="method=forwardCard")
	public ModelAndView forwardCard(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String view="web/cardMain";
		return new ModelAndView(view);
	}
	
	@RequestMapping(params="method=getCard")
	public ModelAndView getCard(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String view="web/cardDetail";
		String kh=request.getParameter("id");
		if(!StringUtil.checkBN(kh)) {
			request.setAttribute("error",CommonResponse.NoInput);
			return new ModelAndView(view);
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
		return new ModelAndView(view);
	}
}