package com.tmri.rfid.web.mapper;

import com.tmri.rfid.web.bean.RfidEri;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wuweihong on 2018/9/20.
 */
@Repository
public interface RfidEriMapper {
    List<RfidEri> queryAll();
}
