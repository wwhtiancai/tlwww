package com.tmri.rfid.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tmri.rfid.web.bean.Test;
import com.tmri.rfid.web.mapper.TestMapper;
import com.tmri.rfid.web.service.TestService;

/**
 * 测试服务
 */
@Service("testService")
public class TestServiceImp implements TestService {
    //@Autowired(required = false)
    @Autowired
    private TestMapper testMapper;

    public Test getById(String hphm) {
        return testMapper.selectByPrimaryKey(hphm);
    }
}