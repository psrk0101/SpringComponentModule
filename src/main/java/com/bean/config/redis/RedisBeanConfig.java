package com.bean.config.redis;

import com.bean.config.redis.type.ClusterConfig;
import com.bean.config.redis.type.RedisConfigIF;
import com.bean.config.redis.type.SentinelConfig;
import com.bean.config.redis.type.StandaloneConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;

import java.util.Properties;

// @Configuration
class RedisBeanConfig {
	private final Properties redisProperties;
	private final RedisConfigIF redisConfigIF;

	@Autowired(
			required = false
	)
	public RedisBeanConfig(Properties redisProperties){
		this.redisProperties = redisProperties;
		this.redisConfigIF = RedisBeanConfigFactory.getBean(redisProperties);
	}

	@Bean
	public RedisConfiguration redisConfiguration(){
		return redisConfigIF.getConfiguration();
	}

	@Bean
	public RedisConnectionFactory redisConnectionFactory(RedisConfiguration redisConfiguration){
		return redisConfigIF.redisConnectionFactory(redisConfiguration);
	}

	@Bean
	public RedisHttpSessionConfiguration redisHttpSessionConfiguration(){
		RedisHttpSessionConfiguration httpSessionConfig = new RedisHttpSessionConfiguration();
		httpSessionConfig.setMaxInactiveIntervalInSeconds(Integer.parseInt(redisProperties.getProperty("mvc.redis.sessiontime")));
		return httpSessionConfig;
	}

	static class RedisBeanConfigFactory{

		static RedisConfigIF getBean(Properties properties){
			String type = properties.getProperty("mvc.redis.type");
			RedisConfigIF bean;

			switch (type){
				case "sentinel":
					bean = new SentinelConfig(properties);
					break;
				case "cluster":
					bean = new ClusterConfig(properties);
					break;
				default:
					bean = new StandaloneConfig(properties);
					break;
			}

			return bean;
		}
	}
}
