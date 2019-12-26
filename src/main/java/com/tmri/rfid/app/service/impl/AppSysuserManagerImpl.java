package com.tmri.rfid.app.service.impl;
import java.math.BigInteger;
import java.security.MessageDigest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tmri.rfid.app.mapper.AppSysuserMapper;
import com.tmri.rfid.app.service.AppSysuserManager;
import com.tmri.rfid.framework.bean.SysUser;
@Service
public class AppSysuserManagerImpl implements AppSysuserManager{
	@Autowired
	private AppSysuserMapper appSysuserMapper;
	@Override
	public SysUser getSysuserByUsername(String yhzh) throws Exception{
		return appSysuserMapper.getSysuserByUsername(yhzh);
	}
	@Override
	public boolean validateSysuser(String yhdh,String mm,String password) throws Exception{
		MessageDigest m=MessageDigest.getInstance("MD5");
		String s=yhdh+password;
		m.update(s.getBytes(),0,s.length());
		String pwd=new BigInteger(1,m.digest()).toString();
		return mm.equals(pwd);
	}
}
