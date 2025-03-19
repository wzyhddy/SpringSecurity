package com.sparrow.config;

import com.sparrow.utils.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author: Sparrow
 * @Description: TODO
 * @DateTime: 2025/3/17 21:22
 **/
@Configuration
public class RedisConfig {
	@Bean
	@SuppressWarnings(value = {"unchecked", "rawtypes"})
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);
		//使用StringRedisSerializer来序列号和反序列化redis的key值
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(serializer);

		//hash的key也采用StringRedisSerializer
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(serializer);

		template.afterPropertiesSet();
		return template;
	}
}
