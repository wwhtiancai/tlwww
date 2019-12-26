package com.tmri.rfid.framework.dao.jdbc;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.WebUtils;
import com.tmri.rfid.framework.bean.Code;
import com.tmri.rfid.framework.bean.Department;
import com.tmri.rfid.framework.bean.SysPara;
import com.tmri.rfid.framework.bean.SysUser;
import com.tmri.rfid.framework.bean.SysparaValue;
import com.tmri.rfid.framework.bean.Sysrun;
import com.tmri.rfid.framework.bean.UserSession;
import com.tmri.rfid.framework.dao.SystemDao;
import com.tmri.rfid.framework.mapper.FrmMapper;
import com.tmri.rfid.framework.util.Constants;
import com.tmri.rfid.framework.util.DepartmentUtil;
import com.tmri.rfid.framework.util.StringUtil;
import com.tmri.rfid.framework.util.SystemCache;
@Repository
public class SystemDaoJdbc implements SystemDao{
	@Autowired
  private FrmMapper frmMapper;
	
	public void loadPara() throws Exception{
		System.out.println("系统参数信息加载.....");
		String key=null;
		List<SysPara> sysparas=frmMapper.getSysparaList();
		Hashtable<String,SysPara> tab1=new Hashtable<String,SysPara>();
		SysPara sysPara=null;
		for(int i=0;i<sysparas.size();i++){
			sysPara=sysparas.get(i);
			key="syspara:"+sysPara.getXtlb()+"_"+sysPara.getGjz();
			tab1.put(key,sysPara);
		}
		SystemCache.getInstance().remove(Constants.MEM_SYSPARA);
		SystemCache.getInstance().reg(Constants.MEM_SYSPARA,tab1);
		SystemCache.getInstance().putCachServiceList(Constants.MEM_SYSPARA.toLowerCase(),"系统参数信息");
		System.out.println("系统参数信息加载结束.");
		System.out.println("部门参数信息加载.....");
		key=null;
		Hashtable<String,String> tab2=new Hashtable<String,String>();
		List<SysparaValue> sysparavalues=frmMapper.getSysparaValueList();
		if(sysparavalues!=null&&sysparavalues.size()>0) {
			SysparaValue sysparaValue=null;
			for(int i=0;i<sysparas.size();i++){
				sysparaValue=sysparavalues.get(i);
				key="sysparavalue:"+sysparaValue.getXtlb()+"_"+sysparaValue.getGlbm()+"_"+sysparaValue.getGjz();
				tab2.put(key,sysparaValue.getCsz());
			}	
		}
		SystemCache.getInstance().remove(Constants.MEM_SYSPARAVALUE);
		SystemCache.getInstance().reg(Constants.MEM_SYSPARAVALUE,tab2);
		SystemCache.getInstance().putCachServiceList(Constants.MEM_SYSPARAVALUE.toLowerCase(),"部门参数信息");
		System.out.println("部门参数信息加载结束.");
	}

	public void loadDepartment() throws Exception{
		System.out.println("部门信息加载.....");
		String key=null;
		String key1=null;
		List<Department> departments=frmMapper.getDepartmentList();
		Department department=null;
		Hashtable<String,Object> tab=new Hashtable<String,Object>();
		for(int i=0;i<departments.size();i++){
			department=(Department)departments.get(i);
			key="department:"+department.getGlbm();
			tab.put(key,department);
			key1="department_xj:"+department.getSjbm();
			List<Department> list=(List<Department>)tab.get(key1);
			if(list==null){
				list=new Vector<Department>();
			}
			list.add(department);
			tab.put(key1,list);
		}
		SystemCache.getInstance().remove(Constants.MEM_DEPARTMENT);
		SystemCache.getInstance().reg(Constants.MEM_DEPARTMENT,tab);
		SystemCache.getInstance().putCachServiceList(Constants.MEM_DEPARTMENT,"部门信息");
		System.out.println("部门信息加载结束.");
	}
	
	public SysPara getPara(String xtlb,String gjz,HttpServletRequest request) throws Exception{
		SysPara sysPara=new SysPara();
		String key="";
		if(request==null){
			key="syspara:"+xtlb+"_"+gjz;
			Hashtable tab1=(Hashtable)SystemCache.getInstance().getValue(Constants.MEM_SYSPARA);
			if(tab1!=null){
				sysPara=(SysPara)tab1.get(key);	
			}
			if(tab1==null||sysPara==null||!StringUtil.checkBN(sysPara.getMrz())){
				sysPara=frmMapper.getSyspara(new SysPara(xtlb,gjz));
			}
		}else{
			UserSession userSession=(UserSession)WebUtils.getSessionAttribute(request,"userSession");
			String glbm=userSession.getGlbm();
			key="sysparavalue:"+xtlb+"_"+glbm+"_"+gjz;
			Hashtable tab2=(Hashtable)SystemCache.getInstance().getValue(Constants.MEM_SYSPARAVALUE);
			String paravalue=null;
			if(tab2!=null){
				paravalue=(String)tab2.get(key);
			}
			if(StringUtil.checkBN(paravalue)){
				sysPara.setXtlb(xtlb);
				sysPara.setGjz(gjz);
				sysPara.setMrz(paravalue);
			}else{
				key="syspara:"+xtlb+"_"+gjz;
				Hashtable tab1=(Hashtable)SystemCache.getInstance().getValue(Constants.MEM_SYSPARA);
				if(tab1!=null){
					sysPara=(SysPara)tab1.get(key);
				}
				if(tab1==null||sysPara==null||!StringUtil.checkBN(sysPara.getMrz())){
					sysPara=frmMapper.getSyspara(new SysPara(xtlb,gjz));
				}
			}
		}
		return sysPara;
	}
	public String getParaValue(String xtlb,String gjz,HttpServletRequest request) throws Exception{
		SysPara para=getPara(xtlb,gjz,request);
		if(para==null){
			return "";
		}else{
			return para.getMrz();
		}
	}
	public String getDepartmentParaValue(String xtlb,String gjz,String glbm) throws Exception{
		String r="";
		SysPara sysPara=new SysPara();
		String key="sysparavalue:"+xtlb+"_"+glbm+"_"+gjz;
		Hashtable tab2=(Hashtable)SystemCache.getInstance().getValue(Constants.MEM_SYSPARAVALUE);
		String paravalue=(String)tab2.get(key);
		if(paravalue!=null){
			r=paravalue;
		}else{
			key="syspara:"+xtlb+"_"+gjz;
			Hashtable tab1=(Hashtable)SystemCache.getInstance().getValue(Constants.MEM_SYSPARA);
			sysPara=(SysPara)tab1.get(key);
			if(sysPara!=null&&StringUtil.checkBN(sysPara.getMrz())){
				r=sysPara.getMrz();
			}else{
				sysPara=frmMapper.getSyspara(new SysPara(xtlb,gjz));
				if(sysPara!=null&&StringUtil.checkBN(sysPara.getMrz())){
					r=sysPara.getMrz();
				}
			}
		}
		return r;
	}
	public List<Code> getCodes(String xtlb,String dmlb) throws Exception{
		Hashtable<String,List<Code>> tab=(Hashtable<String,List<Code>>)SystemCache.getInstance().getValue("code");
		if(tab==null) tab=new Hashtable<String,List<Code>>();
		List<Code> codeList=null;
		SystemCache.getInstance().reg("code",tab);
		String key="code:"+xtlb+"-"+dmlb;
		if(tab.containsKey(key)){
			codeList=tab.get(key);
		}else{
			codeList=frmMapper.getCodeList(new Code(xtlb,dmlb));
			tab.put(key,codeList);
		}
		return codeList;
	}
	public Code getCode(String xtlb,String dmlb,String dmz) throws Exception{
		Code cd=new Code();
		cd.setXtlb(xtlb);
		cd.setDmlb(dmlb);
		cd.setDmsm1(dmz);
		cd.setDmsm2(dmz);
		cd.setDmsm3(dmz);
		cd.setDmsm4(dmz);
		List<Code> codes=getCodes(xtlb,dmlb);
		if(codes==null||codes.size()<1){
			return cd;
		}else{
			for(Code code:codes){
				if(code.getDmz().equals(dmz)){
					return code;
				}
			}
			return cd;
		}
	}
	public String getCodeValue(String xtlb,String dmlb,String dmz) throws Exception{
		List<Code> codes=getCodes(xtlb,dmlb);
		if(codes==null||codes.size()<1){
			return dmz;
		}else{
			for(Code code:codes){
				if(code.getDmz().equals(dmz)){
					return code.getDmsm1();
				}
			}
			return dmz;
		}
	}
	public List<Department> getDepartments() throws Exception{
		List<Department> departments=new Vector<Department>();
		Hashtable<String,Department> tab=(Hashtable<String,Department>)SystemCache.getInstance().getValue(Constants.MEM_DEPARTMENT);
		if(tab!=null){
			Iterator<Map.Entry<String,Department>> iter=tab.entrySet().iterator();
			while(iter.hasNext()){
				Map.Entry<String,Department> entry=iter.next();
				if(entry.getKey().startsWith("department:")){
					departments.add(entry.getValue());
				}
			}
		}
		Collections.sort(departments,new Comparator<Department>(){
			public int compare(Department o1,Department o2){
				return o1.getGlbm().compareTo(o2.getGlbm());
			}
		});
		return departments;
	}
	public Department getDepartment(String glbm) throws Exception{
		Department department = null;
		String key = "department:" + glbm;
		Hashtable tab = (Hashtable) SystemCache.getInstance().getValue(Constants.MEM_DEPARTMENT);		
		if(tab!=null){
			department = (Department) (tab.get(key));
		}else{
			tab = (Hashtable) SystemCache.getInstance().getValue(Constants.MEM_DEPARTMENT);		
		}
		if(department==null){
			department=frmMapper.getDepartment(new Department(glbm));			
			if(department!=null && tab!=null){
				tab.put(key,department);
			}
		}
		return department;
	}
	public String getDepartmentValue(String glbm) throws Exception{
		Department department=getDepartment(glbm);
		if(department!=null){
			return department.getBmqc();
		}else{
			return glbm;
		}
	}
	private List<String> getSubDepartmentsForPrimary(String babh,boolean hasSelf) throws Exception{
		List<Department> departments=getDepartments();
		if(departments!=null){
			DepartmentUtil mt=new DepartmentUtil();
			List<String> list=mt.getChildDepartments(departments,babh);
			if(!hasSelf){
				list.remove(babh);
			}
			return list;
		}else{
			return null;
		}
	}
	public List<Department> getSubDepartments(String glbm,boolean hasSelf) throws Exception{
		List<Department> r=new Vector<Department>();
		List<String> glbms=getSubDepartmentsForPrimary(glbm,hasSelf);
		if(glbms!=null){
			for(String bean:glbms){
				r.add(getDepartment(bean));
			}
			return r;
		}else{
			return null;
		}
	}
	public String getSubDepartmentsForSQL(String glbm,boolean hasSelf) throws Exception{
		List<String> glbms=getSubDepartmentsForPrimary(glbm,hasSelf);
		if(glbms!=null){
			if(glbms.size()>999){
				if(hasSelf){
					return "(select glbm from frm_department q where jlzt='1' start with glbm='"+glbm+"' connect by prior q.glbm=q.sjbm)";	
				}else{
					return "(select glbm from frm_department q where jlzt='1' and glbm<>'"+glbm+"' start with glbm='"+glbm+"' connect by prior q.glbm=q.sjbm)";
				}
			}else{
				String sql="";
				for(String bean:glbms){
					sql+=",'"+bean+"'";
				}
				if(sql.length()>0){
					return "("+sql.substring(1)+")";
				}else{
					return "('')";	
				}
			}
			
		}else{
			return "('')";
		}
	}
	public List<Code> getWhiteList() throws Exception{
		return frmMapper.getWhiteList();
	}
	public List<SysUser> getUserList(SysUser command) throws Exception{
		return frmMapper.getUserList(command);
	}
	public void insertSysrun(Sysrun sysrun) throws Exception{
		frmMapper.insertSysrun(sysrun);
	}
}
