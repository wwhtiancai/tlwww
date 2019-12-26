package com.tmri.rfid.framework.service.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.tmri.rfid.framework.bean.Code;
import com.tmri.rfid.framework.bean.Department;
import com.tmri.rfid.framework.bean.SysPara;
import com.tmri.rfid.framework.bean.SysUser;
import com.tmri.rfid.framework.dao.SystemDao;
import com.tmri.rfid.framework.service.SystemManager;
@Service("systemManagerImpl")
public class SystemManagerImpl implements SystemManager{
  @Autowired
	protected SystemDao systemDao;

	public SysPara getPara(String xtlb,String gjz,HttpServletRequest request) throws Exception{
		return systemDao.getPara(xtlb, gjz, request);
	}
	public String getParaValue(String xtlb,String gjz,HttpServletRequest request) throws Exception{
		return systemDao.getParaValue(xtlb, gjz, request);
	}
	public String getDepartmentParaValue(String xtlb,String gjz,String glbm) throws Exception{
		return systemDao.getDepartmentParaValue(xtlb, gjz, glbm);
	}
	public List<Code> getCodes(String xtlb,String dmlb) throws Exception{
		return systemDao.getCodes(xtlb, dmlb);
	}
	public Code getCode(String xtlb,String dmlb,String dmz) throws Exception{
		return systemDao.getCode(xtlb, dmlb, dmz);
	}
	public String getCodeValue(String xtlb,String dmlb,String dmz) throws Exception{
		return systemDao.getCodeValue(xtlb, dmlb, dmz);
	}
	public List<Department> getDepartments() throws Exception{
		return systemDao.getDepartments();
	}
	public Department getDepartment(String glbm) throws Exception{
		return systemDao.getDepartment(glbm);
	}
	public String getDepartmentValue(String glbm) throws Exception{
		return systemDao.getDepartmentValue(glbm);
	}
	public List<Department> getSubDepartments(String glbm,boolean hasSelf) throws Exception{
		return systemDao.getSubDepartments(glbm,hasSelf);
	}
	public String getSubDepartmentsForSQL(String glbm,boolean hasSelf) throws Exception{
		return systemDao.getSubDepartmentsForSQL(glbm,hasSelf);
	}
	public List<SysUser> getUserList(SysUser command) throws Exception{
		return systemDao.getUserList(command);
	}
}
