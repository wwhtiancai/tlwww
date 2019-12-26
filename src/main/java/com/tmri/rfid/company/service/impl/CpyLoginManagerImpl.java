package com.tmri.rfid.company.service.impl;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tmri.rfid.company.mapper.CpyLoginMapper;
import com.tmri.rfid.company.service.CpyLoginManager;
import com.tmri.rfid.framework.bean.Fold;
import com.tmri.rfid.framework.bean.Program;
import com.tmri.rfid.framework.bean.SysUser;
import com.tmri.rfid.framework.mapper.FrmMapper;
import com.tmri.rfid.framework.service.impl.SystemManagerImpl;
import com.tmri.rfid.framework.util.DbResult;
@Service
public class CpyLoginManagerImpl extends SystemManagerImpl implements CpyLoginManager{
	@Autowired
	private CpyLoginMapper cpyLoginMapper;
	@Autowired
	private FrmMapper frmMapper;
	
	public DbResult validateSysuser(SysUser command) throws Exception{
		DbResult result=new DbResult();
		result.setCode(-1);
		SysUser user=null;
		List<SysUser> queryList=systemDao.getUserList(command);
		if(queryList==null||queryList.size()!=1) {
			result.setMsg("该用户不存在！");
			return result;
		}else {
			user=queryList.get(0);
		}
		String s=user.getYhdh()+command.getMm();
		MessageDigest m=MessageDigest.getInstance("MD5");
		m.update(s.getBytes(),0,s.length());
		String mm=new BigInteger(1,m.digest()).toString();
//		System.out.println(mm);
		if(!user.getMm().equals(mm)) {
			result.setMsg("该用户密码不正确！");
			return result;
		}
		result.setCode(1);
		result.setMsg(user.getYhdh());
		return result;
	}
	public SysUser getYhdh(String yhdh) throws Exception{
		SysUser command=new SysUser();
		command.setYhdh(yhdh);
		List<SysUser> queryList=systemDao.getUserList(command);
		if(queryList!=null&&queryList.size()==1) {
			return queryList.get(0);
		}else {
			return null;
		}
	}
	public HashMap<String,List> getFoldProgramList(String yhdh) throws Exception{
		HashMap<String,List> map=new HashMap<String,List>();
		List<Program> programList=frmMapper.getProgramList(yhdh);
		if(programList==null||programList.size()<1) {
			return null;
		}
		String mldh="";
		for(Program p:programList) {
			if(mldh.indexOf("'"+p.getMldh()+"'")==-1) {
				mldh+=","+p.getMldh();
			}
		}
		if(mldh.length()>1) {
			String[] mldhs=mldh.substring(1).split(",");
			List<Fold> foldList=frmMapper.getFoldList(mldhs);
			map.put("program",programList);
			map.put("fold",foldList);
			return map;
		}else {
			return null;
		}
	}
	
	public DbResult savePassword(SysUser command) throws Exception{
		DbResult dr=new DbResult();
		dr.setCode(-1);
		SysUser user=null;
		List<SysUser> queryList=systemDao.getUserList(command);
		if(queryList==null||queryList.size()!=1) {
			dr.setMsg("该用户不存在！");
			return dr;
		}else {
			user=queryList.get(0);
		}
		String s=command.getYhdh()+command.getMm();
		MessageDigest m=MessageDigest.getInstance("MD5");
		m.update(s.getBytes(),0,s.length());
		String mm=new BigInteger(1,m.digest()).toString();
		if(!mm.equals(user.getMm())){
			dr.setMsg("原密码不正确！");
			return dr;
		}
		s=command.getYhdh()+command.getXm();
		m=MessageDigest.getInstance("MD5");
		m.update(s.getBytes(),0,s.length());
		SysUser cmd=new SysUser();
		cmd.setYhdh(user.getYhdh());
		cmd.setMm(new BigInteger(1,m.digest()).toString());
		cpyLoginMapper.savePassword(cmd);
		dr.setCode(1);
		return dr;
	}
}
