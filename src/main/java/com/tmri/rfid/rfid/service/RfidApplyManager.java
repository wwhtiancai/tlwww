package com.tmri.rfid.rfid.service;
import com.tmri.rfid.rfid.bean.RfidApply;
import com.tmri.rfid.rfid.bean.RfidApplyDoc;
import com.tmri.rfid.web.bean.RfidEri;
import java.util.List;
import java.util.Map;
/**
 * Created by wuweihong on 2018/9/21.
 */
public interface RfidApplyManager{
	public RfidEri checkApply(String kh) throws Exception;
	void save(RfidApply entity);
	void apply(RfidApply apply,RfidApplyDoc doc1,RfidApplyDoc doc2,RfidApplyDoc doc3) throws Exception;
	List<RfidApply> list(RfidApply command);
	public List<Map<String,Object>> listForMap(RfidApply command);
	RfidApply get(String id);
	public List<RfidEri> queryList() throws Exception;
}
