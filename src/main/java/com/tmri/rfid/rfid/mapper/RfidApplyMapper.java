package com.tmri.rfid.rfid.mapper;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.tmri.rfid.rfid.bean.RfidApply;
import com.tmri.rfid.web.bean.RfidEri;

/**
 * Created by wuweihong on 2018/9/20.
 */
@Repository
public interface RfidApplyMapper {
    List<RfidEri> getEriList(RfidEri command) throws Exception;
    RfidApply get(String sqbh);
    List<RfidApply> list(RfidApply command);
    int insert(RfidApply apply);
    int update(RfidApply apply);
    List<Map<String, Object>>  listForMap(RfidApply command);
}
