package com.bean.config.redis.type;

import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.util.Properties;

abstract class RedisConfigAB implements RedisConfigIF{
	protected Properties properties;

	RedisConfigAB(Properties properties){
		this.properties = properties;
	}

	@Override
	public RedisConnectionFactory redisConnectionFactory(RedisConfiguration redisConfiguration) {
		return new LettuceConnectionFactory(redisConfiguration);
	}

	protected RedisNode getRedisNode(String transportLayer){
		String[] val = transportLayer.split(":");
		return new RedisNode(val[0], Integer.parseInt(val[1]));
	}
}
