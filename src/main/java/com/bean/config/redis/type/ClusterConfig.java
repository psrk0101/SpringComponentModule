package com.bean.config.redis.type;

import io.lettuce.core.ReadFrom;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;
import java.util.Properties;

public class ClusterConfig extends RedisConfigAB{
	public ClusterConfig(Properties properties){
		super(properties);
	}

	@Override
	public RedisConfiguration getConfiguration() {
		RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
		setClusters(redisClusterConfiguration);
		return redisClusterConfiguration;
	}

	@Override
	public RedisConnectionFactory redisConnectionFactory(RedisConfiguration redisConfiguration) {
		return new LettuceConnectionFactory(redisConfiguration, getLettuceClientConfiguration());
	}

	private void setClusters(RedisClusterConfiguration redisClusterConfiguration){
		String[] clusters = properties.getProperty("mvc.redis.clusters").split(",");
		for(String cluster : clusters) {
			redisClusterConfiguration.addClusterNode(getRedisNode(cluster));
		}
	}

	private LettuceClientConfiguration getLettuceClientConfiguration(){
		return LettuceClientConfiguration.builder()
				.readFrom(ReadFrom.REPLICA_PREFERRED)
				.clientOptions(getClusterClientOptions())
				.build();
	}

	private ClusterClientOptions getClusterClientOptions(){
		return ClusterClientOptions.builder()
				.topologyRefreshOptions(getRefreshOption())
				.build();
	}

	private ClusterTopologyRefreshOptions getRefreshOption(){
		return ClusterTopologyRefreshOptions.builder()
				.enablePeriodicRefresh()
				.enableAllAdaptiveRefreshTriggers()
				.refreshPeriod(Duration.ofSeconds(5))
				.build();
	}
}
