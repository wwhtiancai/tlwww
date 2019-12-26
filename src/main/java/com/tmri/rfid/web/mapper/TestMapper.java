package com.tmri.rfid.web.mapper;

import org.springframework.stereotype.Repository;
import com.tmri.rfid.web.bean.Test;

@Repository
public interface TestMapper {
    Test selectByPrimaryKey(String hphm);
    
}