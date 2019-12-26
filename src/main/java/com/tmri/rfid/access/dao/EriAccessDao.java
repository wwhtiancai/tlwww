package com.tmri.rfid.access.dao;

import com.tmri.rfid.framework.util.DbResult;
import com.tmri.rfid.rfid.bean.RfidApply;
import com.tmri.rfid.rfid.bean.RfidApplyDoc;

public interface EriAccessDao {
	public DbResult saveApply(RfidApply app) throws Exception;
	public DbResult saveOrDeleteApplyFK(RfidApply app) throws Exception;
	public DbResult saveApplyDoc(RfidApplyDoc appDoc) throws Exception;
	
}
