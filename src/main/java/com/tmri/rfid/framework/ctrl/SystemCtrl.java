package com.tmri.rfid.framework.ctrl;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.tmri.rfid.framework.bean.Fold;
import com.tmri.rfid.framework.bean.Program;
import com.tmri.rfid.framework.service.ExtendsSystemManager;
import com.tmri.rfid.framework.util.CommonResponse;
import com.tmri.rfid.framework.util.StringUtil;
@Controller
public class SystemCtrl extends MultiActionController {

	@Autowired
	protected ExtendsSystemManager systemManager;
	
	@ModelAttribute
	public void setBaseInfo(HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(request.getParameter("method")!=null){
			Method[] mts=getClass().getMethods();
			for(Method mt:mts){
				if(mt.getName().equals(request.getParameter("method"))){
					Annotation[] as=mt.getAnnotations();
					for(Annotation a:as){
						if("com.tmri.framework.interceptor.Ajax".equals(a.annotationType().getName())){
							request.setAttribute("isAJAX","1");
						}
					}
					for(Annotation a:as){
						if("com.tmri.framework.interceptor.CheckCode".equals(a.annotationType().getName())){
							request.setAttribute("isCheckCode","1");
							//systemManager.def();
						}else if("com.tmri.framework.interceptor.ProgramFunction".equals(a.annotationType().getName())){
							request.setAttribute("idx",request.getParameter("idx"));
						}
					}
				}
			}
		}
		HttpSession session=request.getSession();
		String html="",nav="";
		html+="<div class=\"row\">\r\n";
		html+="	<div class=\"col-xs-10\">\r\n";
		html+="		<ul class=\"breadcrumbs breadcrumb white\">\r\n";
		html+="			<li>系统</li>\r\n";
		if(StringUtil.checkBN(request.getParameter("cdbh"))) {
			List<Program> programList=(List<Program>)session.getAttribute("_programList");
			List<Fold> foldList=(List<Fold>)session.getAttribute("_foldList");
			if(programList!=null&&programList.size()>0&&foldList!=null&&foldList.size()>0) {
				for(Program p:programList) {
					if(p.getCdbh().equals(request.getParameter("cdbh"))) {
						for(Fold f:foldList) {
							if(f.getMldh().equals(p.getMldh())) {
								nav+="			<li>"+f.getMlmc()+"</li>\r\n";				
							}
						}
						nav+="			<li>"+p.getCxmc()+"</li>\r\n";
					}
				}
			}
			session.setAttribute("_user_menuid",request.getParameter("cdbh"));
			session.setAttribute("_user_navigation",nav);
			html+=nav;
		}else {
			nav=(String)session.getAttribute("_user_navigation");
			if(StringUtil.checkBN(nav)) {
				html+=nav;
			}
		}
		html+="		</ul>\r\n";
		html+="	</div>\r\n";
		html+="	<div class=\"col-xs-2 tr\"><button type=\"button\" class=\"btn-link\" style=\"font-size:22px;font-weight:bold;color:#fff;text-decoration:none;margin-right:10px;\" onClick=\"javascript:window.close();\">×</button></div>\r\n";
		html+="</div>\r\n";
		session.setAttribute("_user_breadcrumb",html);
	}
	@ExceptionHandler(value=Exception.class)
	public ModelAndView exception(HttpServletRequest request,HttpServletResponse response,Exception e) throws IOException {
		e.printStackTrace();
//		systemManager.saveErrLog(request,getClass().getName(),request.getParameter("method"),e);
		if(request.getAttribute("isAJAX")!=null&&"1".equals(request.getAttribute("isAJAX"))){
			return CommonResponse.JSON(e);
		}else{
			return new ModelAndView("jsp_core/framework/error");	
		}
	}

    /**
     * @param request
     * @return String
     */
    private String getUrl(HttpServletRequest request){
        String url="";
        Enumeration param=request.getParameterNames();
        while(param.hasMoreElements()){
            String pname=param.nextElement().toString();
            if(pname.equalsIgnoreCase("method")) url+="?"+pname+"="+request.getParameter(pname);
        }
        return url;
    }

    /**
     * 得到页面显示的分页html
     * @return
     */
    public Map getPageInfo(PageInfo pageInfo, HttpServletRequest request){
        String formAction = request.getRequestURI()+getUrl(request);

        StringBuffer buffer=new StringBuffer();
        StringBuffer pageFormBuffer = new StringBuffer();
        buffer.append("<script language='javascript' type='text/JavaScript'>\n");
        buffer.append("function submitController(startPage){\n");
        buffer.append("if(startPage){document.myform.startPage.value=startPage;}");
        buffer.append("query_cmd();");
//        buffer.append("if (typeof isShowModal == 'undefined' ){document.pageController.target='_self';}\n");
//        buffer.append("document.pageController.toPage.value=page;\n");
//        buffer.append("document.pageController.submit();\n");
        buffer.append("}\n");
        // buffer.append("function refreshCurrentPage(){\n");
        // buffer.append("submitController("+this.currentPage+");\n");
        // buffer.append("}\n");
        buffer.append("</script>\n");

        buffer.append("<form name=\"pageController\" action=\"");
        buffer.append(formAction);
        buffer.append("\" method=\"POST\" target=\"dialog\">\n");
        //buffer.append("<input type=\"hidden\" name=\"hexQueryCondition\" value=\""+this.getHexQueryCondition()+"\"/>\n");
        //zhoujn 20100716,增加记录总数
        buffer.append("<input type=\"hidden\" name=\"totalRowsAmount\" value=\""+pageInfo.getTotal()+"\"/>\n");
        buffer.append("<input type=\"hidden\" name=\"page\" value=\""+pageInfo.getPages()+"\"/>\n");
        buffer.append("<input type=\"hidden\" name=\"formAction\" value=\""+formAction+"\"/>\n");
        //buffer.append(this.getElements());
        buffer.append("</form>\n");

        pageFormBuffer.append("<form name=\"returnPagedFrom\" action=\"");
        pageFormBuffer.append(formAction);
        pageFormBuffer.append("\" method=\"POST\">\n");
        //pageFormBuffer.append("<input type=\"hidden\" name=\"hexQueryCondition\" value=\""+this.getHexQueryCondition()+"\"/>\n");
        //zhoujn 20100716,增加记录总数
        pageFormBuffer.append("<input type=\"hidden\" name=\"totalRowsAmount\" value=\""+pageInfo.getTotal()+"\"/>\n");
        pageFormBuffer.append("<input type=\"hidden\" name=\"page\" value=\""+pageInfo.getPages()+"\"/>\n");
        pageFormBuffer.append("<input type=\"hidden\" name=\"formAction\" value=\""+formAction+"\"/>\n");
        //pageFormBuffer.append(this.getElements());
        pageFormBuffer.append("</form>\n");

        //pageFormelements = StringUtil.byte2hex(pageFormBuffer.toString().getBytes());

        int totalPages = pageInfo.getPages();
        int previousPage = pageInfo.getPrePage();
        int nextPage = pageInfo.getNextPage();

        String pageScript=buffer.toString();
        buffer.delete(0, buffer.length());
        buffer.append("共");
        buffer.append(pageInfo.getTotal());
        buffer.append("条&nbsp;共");
        buffer.append(totalPages);
        buffer.append("页&nbsp;第");
        buffer.append(pageInfo.getPageNum());
        buffer.append("页 &nbsp;");
        buffer.append("<label onclick='submitController(");
        buffer.append(1);
        buffer.append(")' style='cursor:hand'>首页</label>&nbsp;");
        if(pageInfo.isHasPreviousPage()){// 存在前页
            buffer.append("<label onclick='submitController(");
            buffer.append(previousPage);
            buffer.append(")' style='cursor:hand'>上一页</label>&nbsp;");
        }else{
            buffer.append("<label ");
            buffer.append(">上一页</label>&nbsp;");
        }
        if(pageInfo.isHasNextPage()){// 存在后页
            buffer.append("<label onclick='submitController(");
            buffer.append(nextPage);
            buffer.append(")' style='cursor:hand'>下一页</label>&nbsp;");
        }else{
            buffer.append("<label ");
            buffer.append(">下一页</label>&nbsp;");
        }
        buffer.append("<label onclick='submitController(");
        buffer.append(totalPages);
        buffer.append(")' style='cursor:hand'>末页</label>&nbsp;");
        String pageCtrlDesc=buffer.toString();
        buffer.delete(0,buffer.length());

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("clientScript", pageScript);
        map.put("clientPageCtrlDesc", pageCtrlDesc);
        return map;
    }
}
