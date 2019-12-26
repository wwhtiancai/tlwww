package com.tmri.rfid.rfid.mapper;

import com.tmri.rfid.rfid.bean.FryCommands;
import com.tmri.rfid.rfid.bean.FryDatas;
import com.tmri.rfid.rfid.bean.FryFeedbacks;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FryCommandsMapper {
    List<FryCommands> getListBySjbbh(String sjbbh);

    void updateZt(String sjbbh);
}
