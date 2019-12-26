package com.tmri.rfid.access.service;

import com.alibaba.fastjson.JSONObject;
import com.tmri.rfid.access.bean.AcsUser;
import com.tmri.rfid.access.bean.RfidAccessRequest;
import com.tmri.rfid.framework.util.DbResult;

public interface EriAccessManager {
	public AcsUser getAcsUser(String xh) throws Exception;
	public void saveLog(RfidAccessRequest log) throws Exception;
	public DbResult doCommit(JSONObject obj) throws Exception;
	public String doCheck(String[] datas) throws Exception;
	public DbResult doUpdatePicture(JSONObject obj) throws Exception;
	public DbResult doUpdate(JSONObject obj) throws Exception;
}
