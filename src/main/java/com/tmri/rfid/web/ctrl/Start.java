package com.tmri.rfid.web.ctrl;
import java.util.List;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.tmri.rfid.framework.bean.Code;
import com.tmri.rfid.framework.dao.SystemDao;
import com.tmri.rfid.framework.util.Constants;
import com.tmri.rfid.web.bean.RfidEri;
import com.tmri.rfid.web.dao.RfidEriRedisDao;
import com.tmri.rfid.web.service.RfidEriManager;
/**
 * Created by wuweihong on 2018/9/19.
 */
public class Start extends HttpServlet{
	public void init() throws ServletException{
		ApplicationContext ctx=null;
		try{
			Constants.SYS_VERSION=this.getServletContext().getInitParameter("version");
			Constants.SYS_MADE_DATE=this.getServletContext().getInitParameter("versiondate");
			Constants.SYS_TYPE=this.getServletContext().getInitParameter("systype");
			if(ctx==null){
				ctx=WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
			}
		}catch(Exception e){
			e.printStackTrace();
			return;
		}
		// 初始化_site_等公共引用变量
		String site="";
		site+="<link href=\"/css/bootstrap.min.css\" rel=\"stylesheet\">";
		site+="<script src=\"/js/jquery.min.js\"></script>";
		site+="<script src=\"/js/bootstrap.min.js\"></script>";
		site+="<link href=\"/font/css/font-awesome.min.css\" rel=\"stylesheet\">";
		site+="<link href=\"/css/global.css\" rel=\"stylesheet\">";
		site+="<script src=\"/js/tools.js\"></script>";
		this.getServletContext().setAttribute("_site_global",site);
		site="";
		site+="<script src=\"/js/jquery.qrcode.min.js\"></script>";
		this.getServletContext().setAttribute("_site_qrcode",site);
		site="";
		site+="<script src=\"/js/qrscan/lib/zepto.js\"></script>";
		site+="<script src=\"/js/qrscan/lib/qrcode.lib.min.js\"></script>";
		site+="<script src=\"/js/qrscan/lib/qrcode.js\"></script>";
		this.getServletContext().setAttribute("_site_qrscan",site);
		site="";
		site+="<link href=\"/css/bootstrap-datetimepicker.min.css\" rel=\"stylesheet\">";
		site+="<script type=\"text/javascript\" src=\"/js/bootstrap-datetimepicker.min.js\" charset=\"UTF-8\"></script>";
		this.getServletContext().setAttribute("_site_datetimepicker",site);
		site="";
		site+="<link href=\"/css/company.css\" rel=\"stylesheet\">";
		this.getServletContext().setAttribute("_site_company",site);
		
		site="";
		site+="<div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\r\n";
		site+="	<div class=\"modal-dialog\">\r\n";
		site+="		<div class=\"modal-content\">\r\n";
		site+="			<div class=\"modal-header\">\r\n";
		site+="				<h4 class=\"modal-title\" id=\"myModalLabel\">错误提示：</h4>\r\n";
		site+="			</div>\r\n";
		site+="			<div class=\"modal-body\" id=\"myModalMessage\" style=\"min-height:250px;\"></div>\r\n";
		site+="			<div class=\"modal-footer\">\r\n";
		site+="				<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">　关闭　</button>\r\n";
		site+="			</div>\r\n";
		site+="		</div>\r\n";
		site+="	</div>\r\n";
		site+="</div>\r\n";
		this.getServletContext().setAttribute("_site_alterbox",site);
		
		//初始化参数、代码、白名单
		try {
			SystemDao systemDao=(SystemDao)ctx.getBean(SystemDao.class);
			systemDao.loadPara();
			systemDao.loadDepartment();
			List<Code> tempWhiteList=systemDao.getWhiteList();
			List<Code> whitelist=new Vector<Code>();
			List<Code> redlist=new Vector<Code>();
			if(tempWhiteList!=null&&tempWhiteList.size()>0){
				for(Code cd:tempWhiteList){
					if(cd.getDmlb().equals("1")){
						whitelist.add(cd);
					}else if(cd.getDmlb().equals("2")){
						redlist.add(cd);
					}
				}
			}
			this.getServletContext().setAttribute("whitelist",whitelist);
			this.getServletContext().setAttribute("redlist",redlist);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//加载redis
		try {
			RfidEriManager rfidEriManager=(RfidEriManager)ctx.getBean(RfidEriManager.class);
			RfidEriRedisDao rfidEriRedisDao=(RfidEriRedisDao)ctx.getBean(RfidEriRedisDao.class);
			List<RfidEri> list=rfidEriManager.queryAll();
			rfidEriRedisDao.batchAdd(list);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		//初始化HTML
		String html="";
		html+="<nav class=\"navbar navbar-inverse\" role=\"navigation\">";
		html+="	<div class=\"container-fluid\">";
		html+="		<div class=\"navbar-header\">";
		html+="			<a class=\"navbar-brand\" href=\"/index.html\"><i class=\"icon-credit-card mr5\"></i>汽车电子标识</a>";
		html+="		</div>";
		html+="		<div>";
		html+="			<ul class=\"nav navbar-nav\">";
		html+="				<li><a href=\"#\">产品简介</a></li>";
		html+="				<li><a href=\"/eri.htm?method=forwardCard\">标签查询</a></li>";
		html+="				<li class=\"dropdown\">";
		html+="					<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">";
		html+="						办卡安装<b class=\"caret\"></b>";
		html+="					</a>";
		html+="					<ul class=\"dropdown-menu\">";
		html+="						<li><a href=\"#\">如何申请办卡</a></li>";
		html+="						<li class=\"divider\"></li>";
		html+="						<li><a href=\"/company/index.htm\">企业发行管理系统</a></li>";
		html+="					</ul>";
		html+="				</li>";
		html+="				<li><a href=\"#\">APP</a></li>";
		html+="			</ul>";
		html+="		</div>";
		html+="	</div>";
		html+="</nav>";
		this.getServletContext().setAttribute("_site_navigation",html);
	}
}
