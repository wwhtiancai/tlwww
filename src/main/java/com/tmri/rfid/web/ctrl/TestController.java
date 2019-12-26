package com.tmri.rfid.web.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tmri.rfid.web.bean.Test;
import com.tmri.rfid.web.service.TestService;

/**
 * 测试
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    @Qualifier("testService")
    private TestService testService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Test getTestById(@RequestParam("hphm") String hphm) {
        return testService.getById(hphm);

    }
}