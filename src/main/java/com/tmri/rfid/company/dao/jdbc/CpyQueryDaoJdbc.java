package com.tmri.rfid.company.dao.jdbc;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.tmri.rfid.company.bean.CpyRfidApplyDoc;
import com.tmri.rfid.company.dao.CpyQueryDao;
import com.tmri.rfid.company.mapper.CpyQueryMapper;

@Repository
public class CpyQueryDaoJdbc implements CpyQueryDao {
	@Autowired
	private CpyQueryMapper cpyQueryMapper;
	public CpyRfidApplyDoc getApplyDoc(String sqbh, String lx) throws Exception{
		List<CpyRfidApplyDoc> list=cpyQueryMapper.getApplyDocList(sqbh, lx);
		if(list==null||list.size()<1) {
			return null;
		}else{
			return list.get(0);
		}
	}
}
