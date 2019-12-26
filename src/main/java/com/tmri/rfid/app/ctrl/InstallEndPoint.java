package com.tmri.rfid.app.ctrl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tmri.rfid.framework.bean.RestfulSession;
import com.tmri.rfid.framework.bean.UserSession;
import com.tmri.rfid.framework.ctrl.SystemCtrl;
import com.tmri.rfid.framework.util.Common;
import com.tmri.rfid.framework.util.DbResult;
import com.tmri.rfid.framework.util.StringUtil;
import com.tmri.rfid.rfid.bean.RfidApply;
import com.tmri.rfid.rfid.bean.RfidApplyDoc;
import com.tmri.rfid.rfid.service.RfidApplyDocManager;
import com.tmri.rfid.rfid.service.RfidApplyManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty;
@RestController
@RequestMapping("/be/install.do")
public class InstallEndPoint extends SystemCtrl{
	private final static Logger LOGGER=Logger.getLogger(InstallEndPoint.class);
	@Autowired
	private RfidApplyManager rfidApplyManager;
	@Autowired
	private RfidApplyDocManager rfidApplyDocManager;
	@RequestMapping(params="method=install",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String install(HttpServletRequest request,RfidApply apply,String token){
		DbResult result=new DbResult();
		if(!StringUtil.checkBN(token)){
			result.setMsg("用户未登录");
			return JSON.toJSONString(result);
		}
		UserSession userSession=RestfulSession.getSession(token);
		if(userSession==null){
			result.setMsg("用户会话已过期");
			return JSON.toJSONString(result);
		}
		if(!StringUtil.checkBN(apply.getHphm())){
			result.setMsg("号牌号码为空");
			return JSON.toJSONString(result);
		}
		if(!StringUtil.checkBN(apply.getHpzl())){
			result.setMsg("号牌种类为空");
			return JSON.toJSONString(result);
		}
		if(!StringUtil.checkBN(apply.getKh())){
			result.setMsg("卡号为空");
			return JSON.toJSONString(result);
		}
		try{
			String tpdxsx=String.valueOf(1024*100);// systemManager.getParaValue("81",
																							// "TPDXSX", null);
			MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)request;
			MultipartFile photo1Pic=multipartHttpServletRequest.getFile("photo1Pic");
			if(photo1Pic==null||photo1Pic.isEmpty()){
				result.setMsg("电子标识图片为空");
				return JSON.toJSONString(result);
			}
			byte[] pic1img=photo1Pic.getBytes();
//System.out.println(pic1img.length);
//System.out.println(tpdxsx);
			if(pic1img.length>Integer.parseInt(tpdxsx)*1024){
				result.setMsg(String.format("电子标识图片大小(%.1fKB)超过上限(%sKB)！",pic1img.length/1024.0,tpdxsx));
				return JSON.toJSONString(result);
			}
			MultipartFile photo2Pic=multipartHttpServletRequest.getFile("photo2Pic");
			if(photo2Pic==null||photo2Pic.isEmpty()){
				result.setMsg("车头照为空");
				return JSON.toJSONString(result);
			}
			byte[] pic2img=photo2Pic.getBytes();
			if(pic2img.length>Integer.parseInt(tpdxsx)*1024){
				result.setMsg(String.format("车头照图片大小(%.1fKB)超过上限(%sKB)！",pic2img.length/1024.0,tpdxsx));
				return JSON.toJSONString(result);
			}
			MultipartFile photo3Pic=multipartHttpServletRequest.getFile("photo3Pic");
			if(photo3Pic==null||photo3Pic.isEmpty()){
				result.setMsg("车头照为空");
				return JSON.toJSONString(result);
			}
			byte[] pic3img=photo3Pic.getBytes();
			if(pic3img.length>Integer.parseInt(tpdxsx)*1024){
				result.setMsg(String.format("车头照图片大小(%.1fKB)超过上限(%sKB)！",pic3img.length/1024.0,tpdxsx));
				return JSON.toJSONString(result);
			}
			// 存申请主表
			String sqbh=UUID.randomUUID().toString().replaceAll("-","");
			apply.setSqbh(sqbh);
			apply.setSqlx("12"); // 申请
			apply.setZt("1"); // 受理
			apply.setGxr(userSession.getYhdh());
			apply.setGxrxm(userSession.getSysuser().getXm());
			apply.setLrr(userSession.getYhdh());
			apply.setLrrmc(userSession.getSysuser().getXm());
			apply.setYwdw(userSession.getGlbm());
			apply.setYwdwmc(userSession.getDepartment().getBmmc());
//            apply.setCzipdz(userSession.getSysuser().getIp());
			// 行驶证
			RfidApplyDoc applyDoc3=new RfidApplyDoc(UUID.randomUUID().toString().replaceAll("-",""),sqbh,"11",pic3img,"1");
			// 卡
			RfidApplyDoc applyDoc1=new RfidApplyDoc(UUID.randomUUID().toString().replaceAll("-",""),sqbh,"12",pic1img,"1");
			// 车
			RfidApplyDoc applyDoc2=new RfidApplyDoc(UUID.randomUUID().toString().replaceAll("-",""),sqbh,"13",pic2img,"1");
			rfidApplyManager.apply(apply,applyDoc1,applyDoc2,applyDoc3);
			result.setCode(1);
			String resultStr=JSON.toJSONString(result);
//System.out.println(resultStr);
			return resultStr;
		}catch(Exception e){
			result.setMsg(e.getLocalizedMessage());
			LOGGER.error(e.getMessage());
			e.printStackTrace();
			return JSON.toJSONString(result);
		}
	}
	@RequestMapping(params="method=list",produces="application/json;charset=UTF-8")
	public String list(HttpServletRequest request,@RequestParam(required=false,defaultValue="1") Integer pageNum,@RequestParam(required=false,defaultValue="5") Integer pageSize,String token,RfidApply command){
		DbResult result=new DbResult();
		if(!StringUtil.checkBN(token)){
			result.setMsg("用户未登录");
			return JSON.toJSONString(result);
		}
		UserSession userSession=RestfulSession.getSession(token);
		if(userSession==null){
			result.setMsg("用户会话已过期");
			return JSON.toJSONString(result);
		}
		try{
			command.setYwdw(userSession.getSysuser().getGlbm());
			PageHelper.startPage(pageNum,pageSize);
			PageInfo<Map<String,Object>> pi=new PageInfo<>(rfidApplyManager.listForMap(command));
//            PageInfo<RfidApply> pi = new PageInfo<>(rfidApplyManager.list(command));
			result.setCode(1);
			result.setObj(pi);
			return JSON.toJSONString(result,SerializerFeature.WriteMapNullValue);
		}catch(Exception e){
			result.setMsg(e.getMessage());
			LOGGER.error(e.getMessage());
			e.printStackTrace();
			return JSON.toJSONString(result);
		}
	}
	@RequestMapping(params="method=detail",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public String detail(HttpServletRequest request,@RequestParam(required=true) String sqbh,String token){
		DbResult result=new DbResult();
		if(!StringUtil.checkBN(token)){
			result.setMsg("用户未登录");
			return JSON.toJSONString(result);
		}
		UserSession userSession=RestfulSession.getSession(token);
		if(userSession==null){
			result.setMsg("用户会话已过期");
			return JSON.toJSONString(result);
		}
		try{
			RfidApply apply=rfidApplyManager.get(sqbh);
			result.setCode(1);
			result.setObj(apply);
			return JSON.toJSONString(result,SerializerFeature.WriteMapNullValue);
		}catch(Exception e){
			result.setMsg(e.getMessage());
			LOGGER.error(e.getMessage());
			e.printStackTrace();
			return JSON.toJSONString(result);
		}
	}
	@RequestMapping(params="method=editDoc",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String editDoc(HttpServletRequest request,String token,RfidApplyDoc applyDoc){
		DbResult result=new DbResult();
		if(!StringUtil.checkBN(token)){
			result.setMsg("用户未登录");
			return JSON.toJSONString(result);
		}
		UserSession userSession=RestfulSession.getSession(token);
		if(userSession==null){
			result.setMsg("用户会话已过期");
			return JSON.toJSONString(result);
		}
		if(!StringUtil.checkBN(applyDoc.getSqbh())){
			result.setMsg("申请编号为空");
			return JSON.toJSONString(result);
		}
		if(!StringUtil.checkBN(applyDoc.getLx())){
			result.setMsg("类型为空");
			return JSON.toJSONString(result);
		}
		try{
			MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)request;
			MultipartFile photo1Pic=multipartHttpServletRequest.getFile("photoPic");
			if(photo1Pic==null||photo1Pic.isEmpty()){
				result.setMsg("待更新图片为空");
				return JSON.toJSONString(result);
			}
			RfidApplyDoc pic=rfidApplyDocManager.get(applyDoc.getSqbh(),applyDoc.getLx());
			if(pic==null){
				result.setMsg("查询无数据");
				return JSON.toJSONString(result);
			}
			pic.setWd(photo1Pic.getBytes());
			rfidApplyDocManager.save(pic);
			result.setCode(1);
		}catch(Exception e){
			result.setMsg(e.getMessage());
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		return JSON.toJSONString(result);
	}
}
