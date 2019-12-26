package com.tmri.rfid.framework.util;

import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public final class CommonResponse {

	public static final String success="1";
	public static final String failed="0";
	public static final String forward="4";
	public static final String query="5";
	public static final String add="1";
	public static final String edit="2";
	public static final String del="3";
	public static final String NoInput="请输入必填项！";
	public static final String ErrorPost="非法的请求！";
	public static final String ErrorCheck="逻辑校验错误！";
	public static final String ErrorChecked="数据逻辑校验失败，上传部级监管系统！";
	public static final String SuccessMessage="操作成功！";
	public static final String FailedMessage="操作失败！";
	public static final String NoAccess="您不是系统管理员，禁止操作该菜单功能！";
	
	public static ModelAndView JSON(DbResult r) {
		ModelAndView view = new ModelAndView(JsonView.instance);
		try{
			String msg=r.getMsg();
			if(msg==null){
				msg="NullPointerException!";
			}else{
				msg=URLEncoder.encode(Common.JavaToScript(msg),"UTF-8");
			}
			String data="";
			if(StringUtil.checkBN(r.getData())){
				data=URLEncoder.encode(Common.JavaToScript(r.getData()),"UTF-8");
			}
			String jsonString="{'code':'"+r.getCode()+"','message':'"+msg+"','data':'"+data+"'}";
			view.addObject(JsonView.JSON_ROOT,jsonString);
		}catch(Exception ex){
			System.out.println("URLEncoder Exception:"+ex.getMessage());
			String jsonString="{'code':'0','message':'%E4%BF%9D%E5%AD%98%E5%BC%82%E5%B8%B8','data':''}";
			view.addObject(JsonView.JSON_ROOT,jsonString);
		}
		return view;
	}
	public static ModelAndView JSON(String message) {
		ModelAndView view = new ModelAndView(JsonView.instance);
		try{
			String msg=message;
			if(msg==null){
				msg="NullPointerException!";
			}else{
				msg=URLEncoder.encode(Common.JavaToScript(message),"UTF-8");
			}
			String jsonString="{'code':'0','message':'"+msg+"','data':''}";
			view.addObject(JsonView.JSON_ROOT,jsonString);
		}catch(Exception ex){
			System.out.println("URLEncoder Exception:"+ex.getMessage());
			String jsonString="{'code':'0','message':'%E4%BF%9D%E5%AD%98%E6%88%90%E5%8A%9F','data':''}";
			view.addObject(JsonView.JSON_ROOT,jsonString);
		}
		return view;
	}
	public static ModelAndView JsonAjax(String message) {
		ModelAndView view = new ModelAndView(JsonView.instance);
		try{
			view.addObject(JsonView.JSON_ROOT,message);
		}catch(Exception ex){
			System.out.println("URLEncoder Exception:"+ex.getMessage());
			String jsonString="{}";
			view.addObject(JsonView.JSON_ROOT,jsonString);
		}
		return view;
	}
	public static ModelAndView JSON(Exception e) {
		ModelAndView view = new ModelAndView(JsonView.instance);
		try{
			String msg=e.getMessage();
			if(msg==null){
				msg="NullPointerException!";
			}else{
				msg=URLEncoder.encode(msg,"UTF-8");
			}
			String jsonString="{'code':'0','message':'"+msg+"','data':''}";
			view.addObject(JsonView.JSON_ROOT,jsonString);
		}catch(Exception ex){
			System.out.println("URLEncoder Exception:"+ex.getMessage());
			String jsonString="{'code':'0','message':'%E4%BF%9D%E5%AD%98%E6%88%90%E5%8A%9F','data':''}";
			view.addObject(JsonView.JSON_ROOT,jsonString);
		}
		return view;
	}
	
	public static void setAlerts(String message,HttpServletRequest request){
		String msg=message;
		if(message==null){
			msg="NullPointerException!";
		}
		request.setAttribute("error","<script>alert(\'"+Common.JavaToScript(msg)+"\');</script>");
	}
	public static void setAlerts(String message,boolean closed,HttpServletRequest request){
		String msg=message;
		if(message==null){
			msg="NullPointerException!";
		}
		if(closed)
			request.setAttribute("error","<script>alert(\'"+Common.JavaToScript(msg)+"\');window.close();location.href='500.html'</script>");
		else
			request.setAttribute("error","<script>alert(\'"+Common.JavaToScript(msg)+"\');</script>");
	}
	public static void setErrors(Exception e,HttpServletRequest request){
		String msg=e.getMessage();
		if(msg==null){
			msg="NullPointerException!";
		}
		request.setAttribute("error","<script>alert('"+Common.JavaToScript(msg)+"');top.location.href='500.html'</script>");
	}
	
	public static void pageRedirect(String funcName, String jsonMsg, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.println("<script>parent." + funcName + "(\""+ jsonMsg + "\");</script>");
		} catch (Exception e) {
			System.out.println("returnform error:" + e.getMessage() + " at " + Common.getNow());
		}
	}
	// 获取远程客户端地址
	public static String getRemoteIpdz(HttpServletRequest request){
		String ipString=request.getHeader("X-Forwarded-For");
		if(ipString==null||ipString.length()==0||"unknown".equalsIgnoreCase(ipString)){
				ipString=request.getRemoteAddr();
		}
		return ipString;
	}
	
	public static ModelAndView JSON(DbResult r,boolean multiValue){
		ModelAndView view=new ModelAndView(JsonView.instance);
		try{
			String msg=r.getMsg();
			if(msg==null){
				msg="NullPointerException!";
			}else{
				msg=URLEncoder.encode(Common.ScriptToHtml(msg),"UTF-8");
			}
			String msg1=r.getMsg1();
			if(msg1==null){
				msg1="";
			}else{
				msg1=URLEncoder.encode(Common.ScriptToHtml(msg1),"UTF-8");
			}
			String msg2=r.getMsg2();
			if(msg2==null){
				msg2="";
			}else{
				msg2=URLEncoder.encode(Common.ScriptToHtml(msg2),"UTF-8");
			}
			String data="";
			if(StringUtil.checkBN(r.getData())){
				data=URLEncoder.encode(Common.JavaToScript(r.getData()),"UTF-8");
			}
			String jsonString="";
			if(multiValue){
				jsonString="{'code':'"+r.getCode()+"','message':'"+msg+"','msg1':'"+msg1+"','msg2':'"+msg2+"','data':'"+data+"'}";
			}else{
				jsonString="{'code':'"+r.getCode()+"','message':'"+msg+"','data':'"+data+"'}";
			}
			view.addObject(JsonView.JSON_ROOT,jsonString);
		}catch(Exception ex){
			System.out.println("URLEncoder Exception:"+ex.getMessage());
			String jsonString="{'code':'0','message':'%E4%BF%9D%E5%AD%98%E5%BC%82%E5%B8%B8','data':''}";
			view.addObject(JsonView.JSON_ROOT,jsonString);
		}
		return view;
	}
	
	/**
   * 根据 name 从 request 接受图片
   * 优先接收 BASE64 字符串，其次图片文件
   * @param name 图片的表单字段
   * @return 图片二进制数据(byte[]类型)
   */
	public static byte[] receivePic(HttpServletRequest request,String name,String tpdxsx) throws Exception{
		byte[] img;
		String base64str=request.getParameter(name+"Str");
		if(base64str!=null){
			if(base64str.length()==0)
				throw new Exception(CommonResponse.NoInput);
			img=Base64.decodeBase64(base64str);
		}else{
			if(!(request instanceof MultipartHttpServletRequest)){
				throw new Exception(CommonResponse.NoInput);
			}
			MultipartFile file=((MultipartHttpServletRequest)request).getFile(name);
			if(file==null||file.isEmpty()){
				throw new Exception(CommonResponse.NoInput);
			}
			img=file.getBytes();
		}
		if(img.length>Integer.parseInt(tpdxsx)*1024){
			throw new Exception(String.format("您上传的图片大小(%.1fKB)超过上限(%sKB)！",img.length/1024.0,tpdxsx));
		}
		return img;
	}
}
