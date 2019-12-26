package com.tmri.rfid.web.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller

public class MIndexCtrl{
	@RequestMapping("/m/index.htm")
	public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		return new ModelAndView("m/index");
	}
}