package com.tmri.rfid.company.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tmri.rfid.company.bean.CpyRfidApply;
import com.tmri.rfid.company.bean.CpyRfidApplyDoc;
import com.tmri.rfid.company.dao.CpyQueryDao;
import com.tmri.rfid.company.mapper.CpyQueryMapper;
import com.tmri.rfid.company.service.CpyQueryManager;
import com.tmri.rfid.framework.service.impl.SystemManagerImpl;
import com.tmri.rfid.framework.util.StringUtil;
@Service
public class CpyQueryManagerImpl extends SystemManagerImpl implements CpyQueryManager{
	@Autowired
	private CpyQueryDao cpyQueryDao;
	@Autowired
	private CpyQueryMapper cpyQueryMapper;
	
	public List<CpyRfidApply> getQueryFlowList(CpyRfidApply command) throws Exception{
		List<CpyRfidApply> list=cpyQueryMapper.getQueryFlowList(command);
		for (CpyRfidApply bean:list) {
			translateApply(bean);
		}
		return list;
	}
	private CpyRfidApply translateApply(CpyRfidApply bean) throws Exception {
		if (bean!=null) {
			bean.setHpzlmc(this.systemDao.getCodeValue("00","1007",bean.getHpzl()));
			if(StringUtil.checkBN(bean.getSqlx())){
				bean.setSqlxmc(this.systemDao.getCodeValue("81","1122",bean.getSqlx().substring(0,1)));
			}
			bean.setSqyy(this.systemDao.getCodeValue("81","1122",bean.getSqlx()));
			bean.setZtmc(this.systemDao.getCodeValue("81","1123",bean.getZt()));
			bean.setTbyztmc(this.systemDao.getCodeValue("81","1123",bean.getTbyzt()));
		}
		return bean;
	}
	@Override
	public CpyRfidApplyDoc getApplyDoc(String sqbh, String lx) throws Exception{
		return cpyQueryDao.getApplyDoc(sqbh, lx);
	}
}
