package com.tmri.rfid.company.service;

import java.util.List;
import com.tmri.rfid.company.bean.CpyRfidApply;
import com.tmri.rfid.company.bean.CpyRfidApplyDoc;

public interface CpyQueryManager {
	public List<CpyRfidApply> getQueryFlowList(CpyRfidApply command) throws Exception;
	public CpyRfidApplyDoc getApplyDoc(String sqbh, String lx) throws Exception;
}
