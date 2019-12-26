package com.tmri.rfid.company.dao;

import com.tmri.rfid.company.bean.CpyRfidApplyDoc;

public interface CpyQueryDao {
	public CpyRfidApplyDoc getApplyDoc(String sqbh, String lx) throws Exception;
}
