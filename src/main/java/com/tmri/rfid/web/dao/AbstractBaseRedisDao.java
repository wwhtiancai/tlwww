package com.tmri.rfid.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import com.tmri.rfid.framework.dao.jdbc.SystemDaoJdbc;

/**
 * Created by wuweihong on 2018/9/19.
 */
public class AbstractBaseRedisDao<K,V> {

    @Autowired
    protected RedisTemplate<K, V> redisTemplate;

    public RedisTemplate<K, V> getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * 获取 RedisSerializer
     */
    protected RedisSerializer<String> getRedisSerializer() {

        return redisTemplate.getStringSerializer();
    }

}
