package com.tmri.rfid.rfid.mapper;

import com.tmri.rfid.rfid.bean.RfidApplyDoc;
import com.tmri.rfid.web.bean.RfidEri;
import org.springframework.stereotype.Repository;

import java.sql.Blob;
import java.util.List;


@Repository
public interface RfidApplyDocMapper {
    void insert(RfidApplyDoc entity);
    void update(RfidApplyDoc entity);
    List<RfidApplyDoc> list(String sqbh);
    RfidApplyDoc get(RfidApplyDoc command) throws Exception;

}
