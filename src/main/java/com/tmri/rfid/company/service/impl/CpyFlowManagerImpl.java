package com.tmri.rfid.company.service.impl;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tmri.rfid.company.bean.CpyRfidApply;
import com.tmri.rfid.company.bean.CpyRfidApplyDoc;
import com.tmri.rfid.company.dao.CpyFlowDao;
import com.tmri.rfid.company.mapper.CpyFlowMapper;
import com.tmri.rfid.company.service.CpyFlowManager;
import com.tmri.rfid.framework.bean.UserSession;
import com.tmri.rfid.framework.service.impl.SystemManagerImpl;
import com.tmri.rfid.framework.util.DbResult;
import com.tmri.rfid.framework.util.StringUtil;
import com.tmri.rfid.rfid.util.HttpClientUtil;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class CpyFlowManagerImpl extends SystemManagerImpl implements CpyFlowManager{
	@Autowired
	private CpyFlowDao cpyFlowDao;
	@Autowired
	private CpyFlowMapper cpyFlowMapper;
	
	public List<CpyRfidApply> getCheckList(CpyRfidApply command) throws Exception {
		command.setQssj(command.getKssj());
		command.setZzsj(command.getJssj());
		if(StringUtil.checkBN(command.getHphm())) {
			command.setHphm(command.getHphm().toUpperCase());
		}
		command.setZt("1");
		command.setBz("lrsj desc");
		List<CpyRfidApply> list=cpyFlowMapper.list(command);
		if(list!=null&&list.size()>0) {
			for(CpyRfidApply bean:list) {
				translateCpyRfidApply(bean);
			}
		}
		return list;
	}
	public CpyRfidApply getCheck(String sqbh,String ywdw) throws Exception{
		CpyRfidApply command=new CpyRfidApply();
		command.setSqbh(sqbh);
		command.setYwdw(ywdw);
		command.setZt("1");
		List<CpyRfidApply> list=cpyFlowMapper.list(command);
		if(list!=null&&list.size()>0) {
			return translateCpyRfidApply(list.get(0));
		}else {
			return null;
		}
	}
	public CpyRfidApply translateCpyRfidApply(CpyRfidApply bean) throws Exception{
		if(bean!=null) {
			bean.setHpzlmc(this.systemDao.getCodeValue("00","1007",bean.getHpzl()));
			bean.setYwdwmc(this.systemDao.getDepartmentValue(bean.getYwdw()));
			bean.setZtmc(this.systemDao.getCodeValue("81","1123",bean.getZt()));
			bean.setTbyztmc(this.systemDao.getCodeValue("81","1123",bean.getTbyzt()));
			bean.setTbyymc(this.systemDao.getCodeValue("81","1120",bean.getTbyy()));
		}
		return bean;
	}

//	@Transactional(rollbackFor={RuntimeException.class,Exception.class})
	public DbResult auditCheck(String sqbh,boolean isAudit,UserSession userSession,HttpServletRequest request) throws Exception {
		DbResult dr=new DbResult();

		try {
			dr.setCode(-1);
			CpyRfidApply bean=getCheck(sqbh,userSession.getGlbm());
			if(bean==null) {
				dr.setMsg("待审核的数据不存在！");
				return dr;
			}
			bean.setGdr(userSession.getYhdh());
			bean.setGdrmc(userSession.getSysuser().getXm());
			bean.setGdsj("sysdate");
			bean.setGxr(userSession.getYhdh());
			bean.setGxrxm(userSession.getSysuser().getXm());
			if(isAudit) {
				bean.setZt("5");
			}else {
				bean.setZt("8");
			}
			int m = cpyFlowMapper.update(bean);
			dr.setCode(1);
			if(dr.getCode()==1&&isAudit) {
				String url=systemDao.getParaValue("81","JKDZ",request)+"/service/access.json";
				CpyRfidApply mybean=getApply(sqbh);
				HashMap<String,String> params = new HashMap<String,String>();
				params.put("uid","00100");
				params.put("type","commit");
				params.put("cert","1");
				params.put("sign","2");
				String json="["+JSON.toJSONString(mybean,SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteDateUseDateFormat)+"]";
				params.put("json",json);
				String result=HttpClientUtil.doPost(url,params);
				JSONObject obj=null;
				try {
					obj=JSONObject.parseObject(result);
					if("1".equals(obj.getString("code"))&&StringUtil.checkBN(obj.getString("message"))&&obj.getString("message").length()<=32) {
						dr.setCode(1);
						dr.setMsg("提交成功");
					}else {
						throw new RuntimeException("业务："+bean.getSqbh()+"错误，原因为："+obj.getString("message"));
					}
				}catch(Exception ex) {
					throw new RuntimeException("业务："+bean.getSqbh()+"异常，原因为："+ex.getLocalizedMessage());
				}
			}
			return dr;
		}catch(Exception ex) {
            throw new RuntimeException("异常："+ex.getLocalizedMessage());
		}
	}
	public CpyRfidApply getApply(String sqbh) throws Exception{
		CpyRfidApply bean=cpyFlowMapper.getApply(sqbh);
		if(bean!=null&&StringUtil.checkBN(bean.getSqbh())) {
			List<CpyRfidApplyDoc> docs=cpyFlowMapper.getApplyDoc(sqbh);
			if(docs!=null&&docs.size()>0) {
				for(CpyRfidApplyDoc doc:docs) {
					if("11".equals(doc.getLx())) {
						bean.setXsztp(Base64.encodeBase64String(doc.getWd()));
					}else if("12".equals(doc.getLx())) {
						bean.setBsktp(Base64.encodeBase64String(doc.getWd()));
					}else if("13".equals(doc.getLx())) {
						bean.setQctp(Base64.encodeBase64String(doc.getWd()));
					}
				}
			}
		}
		return bean;
	}
	public List<CpyRfidApply> getCancelList(CpyRfidApply command) throws Exception {
		command.setQssj(command.getKssj());
		command.setZzsj(command.getJssj());
		if(StringUtil.checkBN(command.getHphm())) {
			command.setHphm(command.getHphm().toUpperCase());
		}
		if("1".equals(command.getClzt())) {
			command.setZt("'1','8'");	
		}else {
			command.setZt("9");
		}
		List<CpyRfidApply> list=cpyFlowMapper.list(command);
		if(list!=null&&list.size()>0) {
			for(CpyRfidApply bean:list) {
				translateCpyRfidApply(bean);
			}
		}
		return list;
	}
	public CpyRfidApply getCancel(String sqbh,String ywdw) throws Exception{
		CpyRfidApply command=new CpyRfidApply();
		command.setSqbh(sqbh);
		command.setYwdw(ywdw);
		List<CpyRfidApply> list=cpyFlowMapper.list(command);
		if(list!=null&&list.size()>0) {
			return translateCpyRfidApply(list.get(0));
		}else {
			return null;
		}
	}
	public DbResult saveCancel(CpyRfidApply command) throws Exception{
		DbResult dr=new DbResult();
		dr.setCode(-1);
		CpyRfidApply bean=this.getCancel(command.getSqbh(),command.getYwdw());
		if(bean==null) {
			dr.setMsg("待退办的数据不存在！");
			return dr;
		}
		if(!"1".equals(bean.getZt())&&!"8".equals(bean.getZt())) {
			dr.setMsg("待退办的数据不符合业务状态的操作要求！");
			return dr;
		}
		if(StringUtil.checkBN(bean.getClzt())) {
			dr.setMsg("待退办的数据不符合内网处理的操作要求！");
			return dr;
		}
		bean.setTbyy(command.getTbyy());
		bean.setTbsm(command.getTbsm());
		bean.setTbyzt(bean.getZt());
		bean.setGdr(command.getGdr());
		bean.setGdrmc(command.getGdrmc());
		bean.setGdsj("sysdate");
		bean.setGxr(command.getGdr());
		bean.setGxrxm(command.getGdrmc());
		bean.setZt("9");
		cpyFlowMapper.update(bean);
		dr.setCode(1);
		return dr;
	}
	

}
