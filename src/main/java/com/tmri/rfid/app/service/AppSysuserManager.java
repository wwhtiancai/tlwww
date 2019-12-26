package com.tmri.rfid.app.service;

import com.tmri.rfid.framework.bean.SysUser;

public interface AppSysuserManager {
    public SysUser getSysuserByUsername(String yhzh) throws Exception;
    public boolean validateSysuser(String yhdh, String mm, String password) throws Exception ;
}
