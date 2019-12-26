package com.tmri.rfid.company.mapper;

import org.springframework.stereotype.Repository;
import com.tmri.rfid.framework.bean.SysUser;

@Repository
public interface CpyLoginMapper {
	int savePassword(SysUser cmd) throws Exception;
}
