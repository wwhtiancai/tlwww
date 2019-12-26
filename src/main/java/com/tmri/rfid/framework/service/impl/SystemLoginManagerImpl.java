package com.tmri.rfid.framework.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tmri.rfid.framework.dao.SystemLoginDao;
import com.tmri.rfid.framework.mapper.FrmMapper;
import com.tmri.rfid.framework.service.SystemLoginManager;
@Service
public class SystemLoginManagerImpl extends SystemManagerImpl implements SystemLoginManager{
	@Autowired
	private SystemLoginDao systemloginDao;
	@Autowired
	private FrmMapper frmMapper;
}
