package com.tmri.rfid.rfid.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tmri.rfid.framework.service.impl.SystemManagerImpl;
import com.tmri.rfid.rfid.bean.RfidApplyDoc;
import com.tmri.rfid.rfid.mapper.RfidApplyDocMapper;
import com.tmri.rfid.rfid.service.RfidApplyDocManager;
@Service
@Transactional
public class RfidApplyDocManagerImpl extends SystemManagerImpl implements RfidApplyDocManager{
	@Autowired
	private RfidApplyDocMapper rfidApplyDocMapper;
	@Override
	public void save(RfidApplyDoc entity) throws Exception{
		RfidApplyDoc cmd=new RfidApplyDoc();
		cmd.setXh(entity.getXh());
		RfidApplyDoc doc=rfidApplyDocMapper.get(cmd);
		if(doc!=null){
			rfidApplyDocMapper.update(entity);
		}else{
			rfidApplyDocMapper.insert(entity);
		}
	}
	@Override
	public List<RfidApplyDoc> list(String sqbh) throws Exception{
		return rfidApplyDocMapper.list(sqbh);
	}
	@Override
	public RfidApplyDoc get(String sqbh,String lx) throws Exception{
		RfidApplyDoc cmd=new RfidApplyDoc();
		cmd.setSqbh(sqbh);
		cmd.setLx(lx);
		return rfidApplyDocMapper.get(cmd);
	}
}
