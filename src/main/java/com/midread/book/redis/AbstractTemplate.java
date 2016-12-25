package com.midread.book.redis;

import javax.annotation.Resource;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class AbstractTemplate<T> extends RedisTemplate<String, T> {
	protected RedisSerializer<String> stringSerializer = new StringRedisSerializer();

	@Resource(name = "jedisConnectionFactory")
	private RedisConnectionFactory selfConnectionFactory;
	
	public AbstractTemplate(Class<T> t) {
		setDefaultSerializer(stringSerializer);
		setKeySerializer(stringSerializer);
		setHashKeySerializer(stringSerializer);
		if(t == String.class){
			setValueSerializer(stringSerializer);
			setHashValueSerializer(stringSerializer);
		}else{
			setValueSerializer(new Jackson2JsonRedisSerializer<T>(t));
			setHashValueSerializer(new Jackson2JsonRedisSerializer<T>(t));
		}
	}
	
	@Override
	public RedisConnectionFactory getConnectionFactory() {
		return selfConnectionFactory;
	}
}
