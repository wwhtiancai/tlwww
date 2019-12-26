package com.tmri.rfid.app.ctrl;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.tmri.rfid.app.service.AppSysuserManager;
import com.tmri.rfid.framework.bean.Department;
import com.tmri.rfid.framework.bean.RestfulSession;
import com.tmri.rfid.framework.bean.SysUser;
import com.tmri.rfid.framework.bean.UserSession;
import com.tmri.rfid.framework.ctrl.SystemCtrl;
import com.tmri.rfid.framework.util.StringUtil;
/**
 * @author st
 * @Date 2017-11-13 下午2:31:49
 */
@RestController
@RequestMapping("/be/login.do")
public class LoginEndPoint extends SystemCtrl{
	@Autowired
	private AppSysuserManager appSysuserManager;
	@RequestMapping(params="method=login",produces="application/json;charset=UTF-8")
	public String login(String yhdh,String mm){
		String resStr="";
		try{
			if(!StringUtil.checkBN(yhdh)||yhdh.indexOf("'")!=-1){
				return genErrors("该用户名参数为空！");
			}
			if(!StringUtil.checkBN(mm)||mm.indexOf("'")!=-1){
				return genErrors("该密码参数为空！");
			}
			SysUser sysuser=appSysuserManager.getSysuserByUsername(yhdh);
			if(sysuser==null){
				return genErrors("该用户不存在！");
			}
			if(!appSysuserManager.validateSysuser(sysuser.getYhdh(),sysuser.getMm(),mm)){
				return genErrors("该密码不正确！");
			}
			Department department=systemManager.getDepartment(sysuser.getGlbm());
			if(department==null){
				return genErrors("用户没有分配部门！");
			}
			String token=UUID.randomUUID().toString().replaceAll("-","");
			UserSession userSession=new UserSession();
			userSession.setSysuser(sysuser);// sysuser
			userSession.setDepartment(department);
			userSession.setYhdh(sysuser.getYhdh());
			userSession.setGlbm(department.getGlbm());
			userSession.setScdlsj(new Date().getTime()+"");
//			HashMap paras=new HashMap();
//			String XTYXMS=this.gSysparaCodeService.getSysParaValue("00","XTYXMS");
//			paras.put("XTYXMS",XTYXMS);
//			paras.put("DEBUG","1");
//			paras.put("RESTFUL","true");
//			HashMap map=programFoldManager.getFoldMenuStr(sysuser,paras);
//			if(map.get("prolist")!=null){
//				List<Program> programList=(List<Program>)map.get("prolist");
//				for(Program program:programList){
//					userSession.addMenu(program.getYmdz().startsWith("/")?program.getYmdz():"/"+program.getYmdz());
//					if(program!=null&&StringUtil.checkBN(program.getBz())&&("4".equals(program.getCxlx())||"6".equals(program.getCxlx()))){
//						String[] urls=program.getBz().split(",");
//						for(String url:urls){
//							userSession.addMenu(url.startsWith("/")?url:"/"+url);
//						}
//					}
//				}
//			}
			RestfulSession.setSession(token,userSession);
			sysuser.setMm("");
			Map<String,Object> resultMap=new HashMap<String,Object>();
			resultMap.put("resultId","00");
			resultMap.put("user",sysuser);
			resultMap.put("token",token);
			resStr=JSON.toJSONString(resultMap);
		}catch(Exception e){
			Map<String,Object> resultMap=new HashMap<String,Object>();
			resultMap.put("resultId","01");
			resultMap.put("resultMsg",e.getMessage());
			resStr=JSON.toJSONString(resultMap);
		}
//System.out.println("result:"+resStr);
		return resStr;
	}
	private String genErrors(String error){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		resultMap.put("resultId","01");
		resultMap.put("resultMsg",error);
		return JSON.toJSONString(resultMap);
	}
}
