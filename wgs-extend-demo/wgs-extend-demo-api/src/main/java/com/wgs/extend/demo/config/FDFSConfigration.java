package com.wgs.extend.demo.config;

import com.wgs.extend.fdfs.FastDFSClient;
import com.wgs.extend.fdfs.FdfsInit;
import com.wgs.extend.fdfs.TrackerServerFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @Description
 * @ClassName：FDFSConfigration
 * @Author wanggsh
 * @Date 2020年10月26日 18:12
 * @Version 1.0
 */
@Slf4j
@Configuration
public class FDFSConfigration {

	@Value("${wgs.extend.fdfs.charSet}")
	private String charSet;
	@Value("${wgs.extend.fdfs.trackerServers}")
	private String trackerServers;
	@Value("${wgs.extend.fdfs.trackerHttpPort}")
	private Integer trackerHttpPort;
	@Value("${wgs.extend.fdfs.connectTimeout}")
	private Integer connectTimeout;
	@Value("${wgs.extend.fdfs.networkTimeout}")
	private Integer networkTimeout;
	@Value("${wgs.extend.fdfs.antiStealToken}")
	private boolean antiStealToken;
	@Value("${wgs.extend.fdfs.httpSecretKey}")
	private String httpSecretKey;


	@Value("${wgs.extend.object_pool.minIdle}")
	private int minIdle;
	@Value("${wgs.extend.object_pool.maxIdle}")
	private int maxIdle;
	@Value("${wgs.extend.object_pool.maxTotal}")
	private int maxTotal;
	@Value("${wgs.extend.object_pool.testOnCreate}")
	private boolean testOnCreate;
	@Value("${wgs.extend.object_pool.testOnBorrow}")
	private boolean testOnBorrow;
	@Value("${wgs.extend.object_pool.testOnReturn}")
	private boolean testOnReturn;
	@Value("${wgs.extend.object_pool.testWhileIdle}")
	private boolean testWhileIdle;
	@Value("${wgs.extend.object_pool.blockWhenExhausted}")
	private boolean blockWhenExhausted;
	@Value("${wgs.extend.object_pool.maxWaitMillis}")
	private long maxWaitMillis;
	@Value("${wgs.extend.object_pool.minEvicTime}")
	private long minEvicTime;
	@Value("${wgs.extend.object_pool.timeBetEvicRun}")
	private long timeBetEvicRun;


	@Bean("fdfsInit")
	public FdfsInit init() {
		log.info("配置ClientGlobal信息");
		FdfsInit fdfsInit = new FdfsInit();
		fdfsInit.setCharSet(charSet);
		fdfsInit.setTrackerServers(trackerServers);
		fdfsInit.setTrackerHttpPort(trackerHttpPort);
		fdfsInit.setConnectTimeout(connectTimeout);
		fdfsInit.setNetworkTimeout(networkTimeout);
		fdfsInit.setAntiStealToken(antiStealToken);
		fdfsInit.setHttpSecretKey(httpSecretKey);
		fdfsInit.init();
		return fdfsInit;
	}

	@Bean
	@DependsOn("fdfsInit")
	public TrackerClient initTrackerClient () {
		log.info("初始化TrackerClient");
		return new TrackerClient();
	}

	@Bean
	public GenericObjectPoolConfig initObjectPoolConfig() {
		log.info("初始化对象池配置");
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMinIdle(minIdle);
		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMaxTotal(maxTotal);
		poolConfig.setTestOnCreate(testOnCreate);
		poolConfig.setTestOnBorrow(testOnBorrow);
		poolConfig.setTestOnReturn(testOnReturn);
		poolConfig.setTestWhileIdle(testWhileIdle);
		poolConfig.setBlockWhenExhausted(blockWhenExhausted);
		poolConfig.setMaxWaitMillis(maxWaitMillis);
		poolConfig.setMinEvictableIdleTimeMillis(minEvicTime);
		poolConfig.setTimeBetweenEvictionRunsMillis(timeBetEvicRun);
		return poolConfig;
	}

	@Bean
	public TrackerServerFactory initTrackerServerFactory(TrackerClient trackerClient) {
		log.info("初始化TrackerServer工厂");
		return new TrackerServerFactory(trackerClient);
	}

	@Bean
	public GenericObjectPool<TrackerServer> initTrackerServerPool(TrackerServerFactory trackerServerFactory, GenericObjectPoolConfig<TrackerServer> poolConfig) {
		log.info("初始化TrackerServer连接池");
		return new GenericObjectPool<>(trackerServerFactory, poolConfig);
	}

	@Bean
	public FastDFSClient initFdfsClient(GenericObjectPool<TrackerServer> trackerServerPool) {
		log.info("初始化fdfs连接工具");
		return new FastDFSClient(trackerServerPool);
	}
}
