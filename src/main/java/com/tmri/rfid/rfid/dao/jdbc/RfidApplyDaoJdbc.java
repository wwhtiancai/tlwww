package com.tmri.rfid.rfid.dao.jdbc;
import java.util.List;

import com.tmri.rfid.rfid.dao.RfidApplyDao;
import com.tmri.rfid.rfid.mapper.RfidApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.tmri.rfid.web.bean.RfidEri;

@Repository
public class RfidApplyDaoJdbc implements RfidApplyDao {
	@Autowired
	RfidApplyMapper rfidApplyMapper;
	public List<RfidEri> getEriList(RfidEri command) throws Exception{
		return rfidApplyMapper.getEriList(command);
	}
}
