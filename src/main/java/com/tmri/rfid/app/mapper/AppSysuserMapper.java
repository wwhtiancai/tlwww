package com.tmri.rfid.app.mapper;

import com.tmri.rfid.framework.bean.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface AppSysuserMapper {
    SysUser getSysuserByUsername(String yhzh);

}
