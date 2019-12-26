package com.tmri.rfid.company.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.tmri.rfid.company.bean.CpyRfidApply;
import com.tmri.rfid.framework.bean.UserSession;
import com.tmri.rfid.framework.util.DbResult;

public interface CpyFlowManager {
	public List<CpyRfidApply> getCheckList(CpyRfidApply command) throws Exception;
	public CpyRfidApply getCheck(String sqbh,String ywdw) throws Exception;
	public DbResult auditCheck(String sqbh,boolean isAudit,UserSession userSession,HttpServletRequest request) throws Exception;
	public CpyRfidApply getApply(String sqbh) throws Exception;
	public List<CpyRfidApply> getCancelList(CpyRfidApply command) throws Exception;
	public CpyRfidApply getCancel(String sqbh,String ywdw) throws Exception;
	public DbResult saveCancel(CpyRfidApply command) throws Exception;
	
}
