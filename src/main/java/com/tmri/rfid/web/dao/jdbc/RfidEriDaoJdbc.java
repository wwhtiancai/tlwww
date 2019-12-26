package com.tmri.rfid.web.dao.jdbc;
import com.tmri.rfid.rfid.dao.RfidApplyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.tmri.rfid.web.mapper.RfidEriMapper;

@Repository
public class RfidEriDaoJdbc implements RfidApplyDao {
	@Autowired
	RfidEriMapper rfidEriMapper;
}
