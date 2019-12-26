package com.tmri.rfid.rfid.service.impl;
import com.tmri.rfid.framework.service.impl.SystemManagerImpl;
import com.tmri.rfid.framework.util.Common;
import com.tmri.rfid.rfid.bean.RfidApply;
import com.tmri.rfid.rfid.bean.RfidApplyDoc;
import com.tmri.rfid.rfid.mapper.RfidApplyMapper;
import com.tmri.rfid.rfid.service.RfidApplyDocManager;
import com.tmri.rfid.rfid.service.RfidApplyManager;
import com.tmri.rfid.web.bean.RfidEri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class RfidApplyManagerImpl extends SystemManagerImpl implements RfidApplyManager{
	@Autowired
	private RfidApplyMapper rfidApplyMapper;
	@Autowired
	private RfidApplyDocManager rfidApplyDocManager;
	public RfidEri checkApply(String kh) throws Exception{
		RfidEri command=new RfidEri();
		command.setKh(kh);
		List<RfidEri> list=rfidApplyMapper.getEriList(command);
		if(list==null||list.size()!=1){
			return null;
		}else{
			return list.get(0);
		}
	}
	@Override
	public void save(RfidApply entity){
		RfidApply apply=rfidApplyMapper.get(entity.getSqbh());
		if(apply!=null){
			rfidApplyMapper.update(entity);
		}else{
			rfidApplyMapper.insert(entity);
		}
	}
	@Override
	public void apply(RfidApply apply,RfidApplyDoc doc1,RfidApplyDoc doc2,RfidApplyDoc doc3) throws Exception{
		this.save(apply);
		rfidApplyDocManager.save(doc1);
		rfidApplyDocManager.save(doc2);
		rfidApplyDocManager.save(doc3);
	}
	@Override
	public List<RfidApply> list(RfidApply command){
		return rfidApplyMapper.list(command);
	}
	@Override
	public List<Map<String,Object>> listForMap(RfidApply command){
		return rfidApplyMapper.listForMap(command);
	}
	@Override
	public RfidApply get(String id){
		return rfidApplyMapper.get(id);
	}
	@Override
	public List<RfidEri> queryList() throws Exception{
		return rfidApplyMapper.getEriList(new RfidEri());
	}
}
