package com.tmri.rfid.access.ctrl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tmri.rfid.access.bean.AcsUser;
import com.tmri.rfid.access.bean.RfidAccessRequest;
import com.tmri.rfid.access.service.EriAccessManager;
import com.tmri.rfid.framework.util.DbResult;
import com.tmri.rfid.framework.util.FuncUtil;
import com.tmri.rfid.framework.util.StringUtil;

@Controller
public class AccessCtrl{
	@Autowired
	EriAccessManager eriAccessManager;
	
	@RequestMapping(value="/service/access.json",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String handleRequest(HttpServletRequest request,HttpServletResponse response) {
		RfidAccessRequest rar=new RfidAccessRequest();
		rar.setYhid(request.getParameter("uid"));
		rar.setFf(request.getParameter("type"));
		rar.setZs(request.getParameter("cert"));
		rar.setQm(request.getParameter("sign"));
		rar.setIp(FuncUtil.getRemoteIpdz(request));
		rar.setSr(maxlength(request.getParameter("json")));
		String r="";
		try {
			DbResult dr=doPost(request);
			rar.setQqjg(String.valueOf(dr.getCode()));
			r=outputJSON(dr);
			rar.setSc(r);
		}catch(Exception ex) {
			rar.setQqjg("-99");
			r=outputJSON(-99,StringUtil.getErrorInfoFromException(ex));
			rar.setSc(r);
		}
		try {
			eriAccessManager.saveLog(rar);
		}catch(Exception ex) {
			ex.printStackTrace();
			r=outputJSON(-90,StringUtil.getErrorInfoFromException(ex));
		}
		return r;
	}
	private DbResult doPost(HttpServletRequest request) throws Exception{
		//-1:没提交类型；-2：提交类型不正确；-3：提交数据无法识别；-4：请求数据无法处理；-90：日志记录错误；-99：异常错误
		DbResult dr=new DbResult();
		//请求完整性检查
		dr.setCode(-1);
		if(!StringUtil.checkBN(request.getParameter("uid"))) {
			dr.setMsg("没有UID");
			return dr;
		}
		if(!StringUtil.checkBN(request.getParameter("type"))) {
			dr.setMsg("没有Type");
			return dr;
		}
		if(!StringUtil.checkBN(request.getParameter("json"))) {
			dr.setMsg("没有数据");
			return dr;
		}
		//业务备案检查
		dr.setCode(-2);
		AcsUser user=eriAccessManager.getAcsUser(request.getParameter("uid"));
		if(user==null) {
			dr.setMsg("UID不正确");
			return dr;
		}
		String ip=FuncUtil.getRemoteIpdz(request);
		if(StringUtil.checkBN(user.getIpxz())&&user.getIpxz().indexOf(ip)==-1) {
			dr.setMsg("调用方IP与备案不一致！");
			return dr;
		}
		//业务处理
		JSONArray jsonArray=null;
		try {
System.out.println(request.getParameter("json"));
			if("commit".equalsIgnoreCase(request.getParameter("type"))) {
				
			}
			jsonArray=JSONArray.parseArray(request.getParameter("json"));
		}catch(Exception ex) {
			dr.setCode(-3);
			dr.setMsg("提交数据无法转换，原因为："+ex.getLocalizedMessage());
			return dr;
		}
		if(jsonArray==null||jsonArray.size()<1) {
			dr.setCode(-1);
			dr.setMsg("没有提交数据");
			return dr;
		}
		if("commit".equalsIgnoreCase(request.getParameter("type"))) {
			if(jsonArray.size()>10) {
				dr.setCode(-2);
				dr.setMsg("提交数据超过10条");
				return dr;
			}
			String r="";
			String err="";
			String id="";
			dr.setCode(1);
			for(int i=0;i<jsonArray.size();i++) {
				try {
					JSONObject obj=jsonArray.getJSONObject(i);
					id=obj.getString("sqbh");
					DbResult result=eriAccessManager.doCommit(obj);
					if(result.getCode()==1) {
						r+=","+id;
					}else if(result.getCode()<1) {
						err+="."+id+":"+result.getMsg();
					}
				}catch(Exception ex) {
					err+="."+id+":"+ex.getLocalizedMessage();
				}
			}
			if(r.length()>1) {
				dr.setData(r.substring(1));
			}
			if(err.length()>1) {
				dr.setMsg(err.substring(1));
			}
			return dr;
		}else if("check".equalsIgnoreCase(request.getParameter("type"))) {
			if(jsonArray.size()>1000) {
				dr.setCode(-2);
				dr.setMsg("提交数据超过1000条");
				return dr;
			}
			if(jsonArray.size()<1) {
				dr.setCode(1);
				dr.setMsg("");
				return dr;
			}
			String[] datas=new String[jsonArray.size()+1];
			for(int i=0;i<jsonArray.size();i++) {
				JSONObject obj=jsonArray.getJSONObject(i);
				datas[i]=obj.getString("sqbh");
			}
			String r=eriAccessManager.doCheck(datas);
			dr.setCode(1);
			dr.setData(r);
		}else if("updatePicture".equalsIgnoreCase(request.getParameter("type"))) {
			if(jsonArray.size()>10) {
				dr.setCode(-2);
				dr.setMsg("提交数据超过10条");
				return dr;
			}
			String r="";
			String err="";
			String id="";
			for(int i=0;i<jsonArray.size();i++) {
				try {
					JSONObject obj=jsonArray.getJSONObject(i);
					id=obj.getString("sqbh");
					DbResult result=eriAccessManager.doUpdatePicture(obj);
					if(result.getCode()==1) {
						r+=","+id;
					}else if(result.getCode()<1) {
						err+="."+id+":"+result.getMsg();
					}
				}catch(Exception ex) {
					err+="."+id+":"+ex.getLocalizedMessage();
				}
			}
			if(r.length()>1) {
				dr.setData(r.substring(1));
			}
			if(err.length()>1) {
				dr.setMsg(err.substring(1));
			}
			return dr;
		}else if("update".equalsIgnoreCase(request.getParameter("type"))) {
			if(jsonArray.size()>1000) {
				dr.setCode(-2);
				dr.setMsg("提交数据超过1000条");
				return dr;
			}
			String r="";
			String err="";
			String id="";
			for(int i=0;i<jsonArray.size();i++) {
				try {
					JSONObject obj=jsonArray.getJSONObject(i);
					id=obj.getString("sqbh");
					DbResult result=eriAccessManager.doUpdate(obj);
					if(result.getCode()==1) {
						r+=","+id;
					}else if(result.getCode()<1) {
						err+="."+id+":"+result.getMsg();
					}
				}catch(Exception ex) {
					err+="."+id+":"+ex.getLocalizedMessage();
				}
			}
			if(r.length()>1) {
				dr.setData(r.substring(1));
			}
			if(err.length()>1) {
				dr.setMsg(err.substring(1));
			}
			return dr;
		}else {
			dr.setCode(-2);
			dr.setMsg("提交方法不正确");
		}
		return dr;
	}
	private String outputJSON(DbResult dr) {
		if(StringUtil.checkBN(dr.getData())&&dr.getData().startsWith("[")) {
			return "{\"code\":\""+String.valueOf(dr.getCode())+"\",\"message\":\""+StringUtil.transBlank(dr.getMsg())+"\",\"data\":"+StringUtil.transBlank(dr.getData())+"}";	
		}else {
			return "{\"code\":\""+String.valueOf(dr.getCode())+"\",\"message\":\""+StringUtil.transBlank(dr.getMsg())+"\",\"data\":\""+StringUtil.transBlank(dr.getData())+"\"}";
		}
	}
	private String outputJSON(int code,String message) {
		return "{\"code\":\""+String.valueOf(code)+"\",\"message\":\""+StringUtil.transBlank(message)+"\",\"data\":\"\"}";
	}
	private String maxlength(String i) {
		if(i!=null&&i.length()>=2000) {
			return i.substring(0,2000)+"..";
		}else {
			return i;
		}
	}
}