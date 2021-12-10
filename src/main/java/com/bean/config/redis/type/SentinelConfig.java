package com.bean.config.redis.type;

import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;

import java.util.Properties;

public class SentinelConfig extends RedisConfigAB{
	public SentinelConfig(Properties properties) {
		super(properties);
	}

	@Override
	public RedisConfiguration getConfiguration() {
		RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
		setMasterName(redisSentinelConfiguration);
		setSentinel(redisSentinelConfiguration);
		return redisSentinelConfiguration;
	}

	private void setMasterName(RedisSentinelConfiguration redisSentinelConfiguration){
		redisSentinelConfiguration.setMaster(properties.getProperty("mvc.redis.mastername"));
	}

	private void setSentinel(RedisSentinelConfiguration redisSentinelConfiguration){
		String[] sentinels = properties.getProperty("mvc.redis.sentinels").split(",");
		for (String sentinel : sentinels) {
			redisSentinelConfiguration.addSentinel(getRedisNode(sentinel));
		}
	}
}
