package com.bean.config.redis.type;

import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;

import java.util.Properties;

public class StandaloneConfig extends RedisConfigAB{
	public StandaloneConfig(Properties properties) {
		super(properties);
	}

	@Override
	public RedisConfiguration getConfiguration() {
		String[] IP_PORT = properties.getProperty("mvc.redis.master").split(":");
		return new RedisStandaloneConfiguration(IP_PORT[0], Integer.parseInt(IP_PORT[1]));
	}
}
