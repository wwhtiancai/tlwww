package com.tmri.rfid.web.dao.jdbc;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import com.tmri.rfid.web.bean.RfidEri;
import com.tmri.rfid.web.dao.AbstractBaseRedisDao;
import com.tmri.rfid.web.dao.RfidEriRedisDao;
/**
 * Created by wuweihong on 2018/9/19.
 */
@Service
public class RfidEriRedisDaoJdbc extends AbstractBaseRedisDao<String,Serializable> implements RfidEriRedisDao{
	@Override
	public boolean add(final RfidEri eri) throws Exception{
		boolean result=redisTemplate.execute(new RedisCallback<Boolean>(){
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException{
				ValueOperations<String,Serializable> valueOper=redisTemplate.opsForValue();
				valueOper.set(eri.getKh(),eri);
				return true;
			}
		},false,true);
		return result;
	}
	@Override
	public boolean batchAdd(final List<RfidEri> eris) throws Exception{
		boolean result=redisTemplate.execute(new RedisCallback<Boolean>(){
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException{
				ValueOperations<String,Serializable> valueOper=redisTemplate.opsForValue();
				for(RfidEri eri:eris){
					valueOper.set(eri.getKh(),eri);
				}
				return true;
			}
		},false,true);
		return result;
	}
	@Override
	public void delete(String key) throws Exception{
		List<String> list=new ArrayList<String>();
		list.add(key);
		delete(list);
	}
	@Override
	public void delete(List<String> keys) throws Exception{
		redisTemplate.delete(keys);
	}
	@Override
	public boolean update(final RfidEri eri) throws Exception{
		String id=eri.getKh();
		if(get(id)==null){
			throw new NullPointerException("数据行不存在, key = "+id);
		}
		boolean result=redisTemplate.execute(new RedisCallback<Boolean>(){
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException{
				ValueOperations<String,Serializable> valueOper=redisTemplate.opsForValue();
				valueOper.set(eri.getKh(),eri);
				return true;
			}
		});
		return result;
	}
	@Override
	public RfidEri get(final String keyId) throws Exception{
		RfidEri result=redisTemplate.execute(new RedisCallback<RfidEri>(){
			public RfidEri doInRedis(RedisConnection connection) throws DataAccessException{
				ValueOperations<String,Serializable> operations=redisTemplate.opsForValue();
				RfidEri eri=(RfidEri)operations.get(keyId);
				return eri;
			}
		});
		return result;
	}
}
