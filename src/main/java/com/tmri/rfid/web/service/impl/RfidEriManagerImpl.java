package com.tmri.rfid.web.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tmri.rfid.framework.service.impl.SystemManagerImpl;
import com.tmri.rfid.framework.util.StringUtil;
import com.tmri.rfid.web.bean.RfidEri;
import com.tmri.rfid.web.dao.RfidEriRedisDao;
import com.tmri.rfid.web.mapper.RfidEriMapper;
import com.tmri.rfid.web.service.RfidEriManager;
@Service
public class RfidEriManagerImpl extends SystemManagerImpl implements RfidEriManager{
	@Autowired
	RfidEriMapper rfidEriMapper;
	@Autowired
	private RfidEriRedisDao rfidEriRedisDao;
	
	public List<RfidEri> queryAll() throws Exception {
		return rfidEriMapper.queryAll();
	}
	
	public RfidEri getEri(String kh) throws Exception{
		RfidEri result=rfidEriRedisDao.get(kh);
		if(result!=null&&StringUtil.checkBN(result.getKh())){
			result.setHpzlmc(getCodeValue("00","1007",result.getHpzl()));
		}
		return result;
	}
}
