package com.sparrow.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Sparrow
 * @Description: TODO
 * @DateTime: 2025/3/17 21:46
 **/
@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Component
public class RedisCache {

	@Autowired
	private RedisTemplate redisTemplate;


	public <T> T getCacheObject(final String key) {
		ValueOperations<String, T> operations = redisTemplate.opsForValue();
		return  operations.get(key);
	}

	//缓存的基本对象Integer String 实体等
	public <T> void  setCacheObject(final String key, final T value) {
		redisTemplate.opsForValue().set(key, value);
	}

	public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
		redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
	}

	public boolean expire(final String key, final long timeout) {
		return expire(key, timeout, TimeUnit.SECONDS);
	}
	public boolean expire(final String key, final long timeout, final TimeUnit timeUnit) {
		return redisTemplate.expire(key, timeout, timeUnit);
	}

	//删除单个对象
	public boolean deleteObject(final String key) {
		return redisTemplate.delete(key);
	}

	public long deleteObject(final Collection collection) {
		return redisTemplate.delete(collection);
	}

	public <T> List<T> getCacheList(final String key) {
		return redisTemplate.opsForList().range(key, 0, -1);
	}

	//缓存list数据
	public <T> long setCacheList(final String key, final List<T> list) {
		Long count = redisTemplate.opsForList().rightPushAll(key, list);
		return count == null ? 0 : count;
	}

	public <T> BoundSetOperations<String,T> setCacheSet(final String key, final Set<T> dataSet) {
		BoundSetOperations<String, T> boundSetOperations = redisTemplate.boundSetOps(key);
		Iterator<T> iterator = dataSet.iterator();
		while (iterator.hasNext()) {
			boundSetOperations.add(iterator.next());
		}
		return boundSetOperations;
	}

	public <T> void serCacheMap(final String key, final Map<String, T> dataMap) {
		if(dataMap != null) {
			redisTemplate.opsForHash().putAll(key, dataMap);
		}
	}

	public <T> Set<T> getCacheSet(final String key) {
		return redisTemplate.opsForSet().members(key);
	}

	public <T> Map<String, T> getCacheMap(final String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	public void delCacheMapValue(final String key, final String hkey) {
		HashOperations hashOperations = redisTemplate.opsForHash();
		hashOperations.delete(key, hkey);
	}

	public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hkeys) {
		return redisTemplate.opsForHash().multiGet(key, hkeys);
	}

	public Collection<String> keys(final String pattern) {
		return redisTemplate.keys(pattern);
	}

}
