package com.tmri.rfid.rfid.mapper;

import com.tmri.rfid.rfid.bean.FryDatas;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FryDatasMapper {
    long insertFryDatasBatch(List<FryDatas> list);
    List<FryDatas> getBySjbbh(@Param("sjbbh")String sjbbh);

    void deleteBySjbbh(@Param("sjbbh")String sjbbh);
}
