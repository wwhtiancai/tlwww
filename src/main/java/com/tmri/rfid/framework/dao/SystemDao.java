package com.tmri.rfid.framework.dao;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.tmri.rfid.framework.bean.Code;
import com.tmri.rfid.framework.bean.Department;
import com.tmri.rfid.framework.bean.SysPara;
import com.tmri.rfid.framework.bean.SysUser;
import com.tmri.rfid.framework.bean.Sysrun;

public interface SystemDao{
	public void loadPara() throws Exception;
	public void loadDepartment() throws Exception;
	
	public SysPara getPara(String xtlb,String gjz,HttpServletRequest request) throws Exception;
	/** 获取系统参数值，自动判断全局或部门参数 */
	public String getParaValue(String xtlb,String gjz,HttpServletRequest request) throws Exception;
	/** 获取系统参数值，仅部门参数 */
	public String getDepartmentParaValue(String xtlb,String gjz,String glbm) throws Exception;
	/** 获取系统代码数据列表 */
	public List<Code> getCodes(String xtlb,String dmlb) throws Exception;
	/** 获取系统代码数据 */
	public Code getCode(String xtlb,String dmlb,String dmz) throws Exception;
	/** 获取系统代码值 */
	public String getCodeValue(String xtlb,String dmlb,String dmz) throws Exception;
	/** 获取所有部门 */
	public List<Department> getDepartments() throws Exception;
	/** 获取单个部门 */
	public Department getDepartment(String glbm) throws Exception;
	/** 获取部门全称 */
	public String getDepartmentValue(String glbm) throws Exception;
	/** 获取下级部门数据 */
	public List<Department> getSubDepartments(String glbm,boolean hasSelf) throws Exception;
	/** 获取下级部门数据的SQL条件语句 */
	public String getSubDepartmentsForSQL(String glbm,boolean hasSelf) throws Exception;
	/** 获取系统拦截器的白、红名单 */
	public List<Code> getWhiteList() throws Exception;
	/** 获取用户数据 */
	List<SysUser> getUserList(SysUser command) throws Exception;
	/** 记录页面访问日志 */
	public void insertSysrun(Sysrun sysrun) throws Exception;
}
