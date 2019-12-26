package com.tmri.rfid.web.service;

import com.tmri.rfid.web.bean.RfidEri;

import java.util.List;

/**
 * Created by wuweihong on 2018/9/21.
 */
public interface RfidEriManager {
	public List<RfidEri> queryAll() throws Exception;
  public RfidEri getEri(String kh) throws Exception;
}
