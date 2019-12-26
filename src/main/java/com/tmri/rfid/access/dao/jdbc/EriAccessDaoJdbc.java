package com.tmri.rfid.access.dao.jdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.tmri.rfid.access.dao.EriAccessDao;
import com.tmri.rfid.access.mapper.EriAccessMapper;
import com.tmri.rfid.framework.util.DbResult;
import com.tmri.rfid.rfid.bean.RfidApply;
import com.tmri.rfid.rfid.bean.RfidApplyDoc;

@Repository
public class EriAccessDaoJdbc implements EriAccessDao {
	@Autowired
	private EriAccessMapper eriAccessMapper;
	
	public DbResult saveApply(RfidApply app) throws Exception{
		DbResult dr=new DbResult();
		dr.setCode(-1);
		app.setClyw("1");
		app.setClzt("0");
		app.setHphm(null);
		app.setKh(null);
		app.setSycplx(null);
		app.setSycpid(null);
		int count=eriAccessMapper.existApply(new RfidApply(app.getSqbh(),null));
		if(count<1) {
			if(app.getXsztp()==null||app.getXsztp().length<1) {
				dr.setMsg("汽车电子标识卡（"+app.getKh()+"）业务中缺少行驶证上传图片");
				return dr;
			}
			if(app.getBsktp()==null||app.getBsktp().length<1) {
				dr.setMsg("汽车电子标识卡（"+app.getKh()+"）业务中缺少标识卡上传图片");
				return dr;
			}
			if(app.getQctp()==null||app.getQctp().length<1) {
				dr.setMsg("汽车电子标识卡（"+app.getKh()+"）业务中缺少汽车正面上传图片");
				return dr;
			}
			int r=eriAccessMapper.insertApply(app);
			if(r==1) {
				dr.setCode(1);
			}else {
				dr.setMsg("业务主表数据新增错误，返回为："+String.valueOf(r));
			}
		}else {
			app.setHpzl(null);
			app.setHphm(null);
			app.setKh(null);
			app.setSycplx(null);
			app.setSycpid(null);
			int r=eriAccessMapper.updateApply(app);
			if(r==1) {
				dr.setCode(1);
			}else {
				dr.setMsg("业务主表数据更新错误，返回为："+String.valueOf(r));
			}
		}
		return dr;
	}
	
	public DbResult saveOrDeleteApplyFK(RfidApply app) throws Exception{
		DbResult dr=new DbResult();
		dr.setCode(-1);
		int count=eriAccessMapper.existApply(new RfidApply(app.getSqbh(),null));
		if(count<1) {
			dr.setMsg("该笔业务不存在");
			return dr;
		}
		app.setHpzl(null);
		app.setHphm(null);
		int r=eriAccessMapper.updateApply(app);
		if(r==1) {
			dr.setCode(1);
		}else {
			dr.setMsg("业务主表数据更新错误，返回为："+String.valueOf(r));
		}
		return dr;
	}
	
	public DbResult saveApplyDoc(RfidApplyDoc appDoc) throws Exception{
		DbResult dr=new DbResult();
		dr.setCode(-1);
		int count=eriAccessMapper.existApplyDoc(new RfidApplyDoc(appDoc.getXh()));
		if(count<1) {
			int r=eriAccessMapper.insertApplyDoc(appDoc);
			if(r==1) {
				dr.setCode(1);
			}else {
				dr.setMsg("图片主表数据新增错误，返回为："+String.valueOf(r));
			}
		}else {
			int r=eriAccessMapper.updateApplyDoc(appDoc);
			if(r==1) {
				dr.setCode(1);
			}else {
				dr.setMsg("图片主表数据更新错误，返回为："+String.valueOf(r));
			}
		}
		return dr;
	}
}
