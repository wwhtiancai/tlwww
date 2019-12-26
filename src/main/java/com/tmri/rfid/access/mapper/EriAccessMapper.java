package com.tmri.rfid.access.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.tmri.rfid.access.bean.AcsUser;
import com.tmri.rfid.access.bean.RfidAccessRequest;
import com.tmri.rfid.framework.bean.Fold;
import com.tmri.rfid.rfid.bean.RfidApply;
import com.tmri.rfid.rfid.bean.RfidApplyDoc;

@Repository
public interface EriAccessMapper {
	List<AcsUser> getAcsUserList(AcsUser cmd) throws Exception;
	int existApply(RfidApply apply) throws Exception;
	int insertApply(RfidApply apply) throws Exception;
	int updateApply(RfidApply apply) throws Exception;
	int existApplyDoc(RfidApplyDoc applyDoc) throws Exception;
	int insertApplyDoc(RfidApplyDoc applyDoc) throws Exception;
  int updateApplyDoc(RfidApplyDoc applyDoc) throws Exception;
  int saveAccessRequest(RfidAccessRequest log) throws Exception;
  List<RfidApply> getCheckList(String[] datas) throws Exception;
  
}
