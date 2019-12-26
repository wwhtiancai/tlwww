package com.tmri.rfid.company.service;

import java.util.HashMap;
import java.util.List;
import com.tmri.rfid.framework.bean.SysUser;
import com.tmri.rfid.framework.util.DbResult;

public interface CpyLoginManager {
	public DbResult validateSysuser(SysUser command) throws Exception;
	public SysUser getYhdh(String yhdh) throws Exception;
	public HashMap<String,List> getFoldProgramList(String yhdh) throws Exception;
	public DbResult savePassword(SysUser sysuser) throws Exception;
	
}
