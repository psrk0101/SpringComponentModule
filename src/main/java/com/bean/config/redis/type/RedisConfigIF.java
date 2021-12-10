package com.bean.config.redis.type;

import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

public interface RedisConfigIF {
	RedisConfiguration getConfiguration();
	RedisConnectionFactory redisConnectionFactory(RedisConfiguration redisConfiguration);
}
