package com.tmri.rfid.framework.mapper;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.tmri.rfid.framework.bean.Code;
import com.tmri.rfid.framework.bean.Department;
import com.tmri.rfid.framework.bean.Fold;
import com.tmri.rfid.framework.bean.Program;
import com.tmri.rfid.framework.bean.SysPara;
import com.tmri.rfid.framework.bean.SysUser;
import com.tmri.rfid.framework.bean.SysparaValue;
import com.tmri.rfid.framework.bean.Sysrun;
@Repository
public interface FrmMapper{
	List<SysPara> getSysparaList();
	List<SysparaValue> getSysparaValueList();
	SysPara getSyspara(SysPara command);
	List<Code> getCodeList(Code command);
	List<Department> getDepartmentList();
	Department getDepartment(Department command);
	List<Code> getWhiteList();
	List<SysUser> getUserList(SysUser command) throws Exception;
	List<Program> getProgramList(String yhdh);
	List<Fold> getFoldList(String[] datas);
	int insertSysrun(Sysrun sysrun);
}
