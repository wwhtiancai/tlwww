package com.tmri.rfid.company.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.tmri.rfid.company.bean.CpyRfidApply;
import com.tmri.rfid.company.bean.CpyRfidApplyDoc;

@Repository
public interface CpyFlowMapper {
  List<CpyRfidApply> list(CpyRfidApply command);
  int update(CpyRfidApply apply);
  CpyRfidApply getApply(String sqbh) throws Exception;
  List<CpyRfidApplyDoc> getApplyDoc(String sqbh) throws Exception;
}
