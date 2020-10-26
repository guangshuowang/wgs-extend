package com.wgs.extend.demo.config;

import com.wgs.extend.lock.redis.RedisLock;
import com.wgs.extend.lock.zk.ZKLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @ClassName：LockConfigration
 * @Author wanggsh
 * @Date 2020年10月20日 09:48
 * @Version 1.0
 */
@Configuration
@Slf4j
public class LockConfigration {

//	@Autowired
//	private Environment environment;

	@Value("${wgs.extend.lock.zk.url}")
	private String url; // zk地址
	@Value("${wgs.extend.lock.zk.lockPath}")
	private String lockPath; // zk上锁的根目录
	@Value("${wgs.extend.lock.redis.url}")
	private String redisUrl; // redis地址

	@Bean
	public ZKLock initZKLock() {
//		String url = environment.getProperty("spring.dubbo.registry.address");
		log.info("初始化lock，zk地址：{}", url);
		return new ZKLock(url, lockPath);
	}
	@Bean
	public RedisLock initRedisLock() {
//		String url = environment.getProperty("spring.dubbo.registry.address");
		log.info("初始化lock，redis地址：{}", redisUrl);
		return new RedisLock(redisUrl);
	}
}
