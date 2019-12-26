package com.tmri.rfid.rfid.mapper;

import com.tmri.rfid.rfid.bean.FryDatas;
import com.tmri.rfid.rfid.bean.FryFeedbacks;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FryFeedbacksMapper {
    void insertFryFeedbacks(FryFeedbacks fryFeedbacks);

}
