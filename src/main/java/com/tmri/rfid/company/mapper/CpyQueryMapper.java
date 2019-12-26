package com.tmri.rfid.company.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.tmri.rfid.company.bean.CpyRfidApply;
import com.tmri.rfid.company.bean.CpyRfidApplyDoc;

@Repository
public interface CpyQueryMapper {
	List<CpyRfidApply> getQueryFlowList(CpyRfidApply command) throws Exception;
  List<CpyRfidApplyDoc> getApplyDocList(String sqbh, String lx) throws Exception;
}
