package com.bean.config;

import io.lettuce.core.ReadFrom;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class RedisBeanConfig {
	private RedisConfiguration redisConfiguration;

	private LettuceClientConfiguration lettuceClientConfiguration;

	private Properties redisProperties;

	public RedisBeanConfig(Properties properties){
		redisProperties = properties;
	}

	public void redisStandaloneConfiguration(){
		redisProperties.get("jdbc_driver");

		this.redisConfiguration = new RedisStandaloneConfiguration("192.168.1.34", 6379);
	}

	public void redisSentinelConfiguration(){
		RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
		redisSentinelConfiguration.setMaster("mymaster");

		RedisNode sentinel1 = new RedisNode("192.168.1.34", 6391);
		RedisNode sentinel2 = new RedisNode("192.168.1.34", 6392);
		RedisNode sentinel3 = new RedisNode("192.168.1.34", 6393);

		redisSentinelConfiguration.addSentinel(sentinel1);
		redisSentinelConfiguration.addSentinel(sentinel2);
		redisSentinelConfiguration.addSentinel(sentinel3);
		this.redisConfiguration = redisSentinelConfiguration;
	}

	private Collection<RedisNode> getNodes(){
		Collection<RedisNode> nodes = new ArrayList<>();
		return nodes;
	}

	public void redisClusterConfiguration(){
		RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration()
				.clusterNode("192.168.1.5", 7000)
				.clusterNode("192.168.1.5", 7001)
				.clusterNode("192.168.1.5", 7002)
				.clusterNode("192.168.1.5", 7003)
				.clusterNode("192.168.1.5", 7004)
				.clusterNode("192.168.1.5", 7005)
				;
		this.redisConfiguration = redisClusterConfiguration;
	}

	@Bean
	public RedisConnectionFactory redisConnectionFactory2(){
		return new LettuceConnectionFactory(redisConfiguration, lettuceClientConfiguration);
	}

	private void setLettuceClientConfiguration(){
		ClusterTopologyRefreshOptions clusterTopologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
				.enablePeriodicRefresh()
				.enableAllAdaptiveRefreshTriggers()
				.refreshPeriod(Duration.ofSeconds(5))
				.build();

		ClusterClientOptions clusterClientOptions = ClusterClientOptions.builder()
				.topologyRefreshOptions(clusterTopologyRefreshOptions).build();

		LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder()
				.readFrom(ReadFrom.REPLICA_PREFERRED)
				.clientOptions(clusterClientOptions).build();
	}
}
