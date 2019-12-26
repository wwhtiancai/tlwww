package com.tmri.rfid.rfid.mapper;

import com.tmri.rfid.rfid.bean.BlobBean;
import com.tmri.rfid.rfid.bean.RfidApplyDoc;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;


@Repository
public interface BaseMapper {

    public LinkedHashMap<String, Object> selectById(String sqlContent);
    public int countSql(String sqlContent);

    public void insertSql(String sqlContent);
    public void updateSql(String sqlContent);

    public BlobBean selectBlobById(@Param("table")String table, @Param("item")String item, @Param("zjSql")String zjSql);
    public void updateBlobById(BlobBean blobBean);

}
