package com.tmri.rfid.rfid.service;
import java.util.List;
import com.tmri.rfid.rfid.bean.RfidApplyDoc;
public interface RfidApplyDocManager{
	void save(RfidApplyDoc entity) throws Exception;
	List<RfidApplyDoc> list(String sqbh) throws Exception;
	RfidApplyDoc get(String sqbh,String lx) throws Exception;
}
