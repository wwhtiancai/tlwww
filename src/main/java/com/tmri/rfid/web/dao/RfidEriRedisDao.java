package com.tmri.rfid.web.dao;

import com.tmri.rfid.web.bean.RfidEri;

import java.util.List;

/**
 * Created by wuweihong on 2018/9/19.
 */
public interface RfidEriRedisDao {
    /**
     * 新增数据
     * @return
     */
    boolean add(RfidEri eri) throws Exception;
    /**
     * 批量新增，使用list的方式
     * @param eris
     * @return
     */
    boolean batchAdd(List<RfidEri> eris) throws Exception;
    /**
     * 删除一条数据
     * @param key
     */
    void delete(String key) throws Exception;

    /**
     * 删除多个
     * @param keys
     */
    void delete(List<String> keys) throws Exception;
    /**
     * 修改
     * @param eri
     * @return
     */
    boolean update(RfidEri eri) throws Exception;
    /**
     * 根据key获取用户
     * @param keyId
     * @return
     */
    RfidEri get(String keyId) throws Exception;
}
