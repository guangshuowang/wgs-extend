package com.wgs.extend.lock.redis;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @Description
 * @ClassName：RedisLock
 * @Author wanggsh
 * @Date 2020年10月22日 21:08
 * @Version 1.0
 */
@Slf4j
@Component
@ConfigurationProperties(prefix = "wgs.extend.lock.redis")
public class RedisLock {


//	@Value("${url}")
//	private String url;
	private Config config;

	private final InheritableThreadLocal<RLock> threadLocal = new InheritableThreadLocal<>();

	public RedisLock() {
	}

	public RedisLock(@Value("${wgs.extend.lock.redis.url}") String url) {
//		this.url = url;
		log.info("初始化redisLock：{}", url);
		config = new Config();
		config.useSingleServer().setAddress(url);
	}

	public RedissonClient getClient() {
		return Redisson.create(config);
	}

	public boolean lock(String lockName) {
		RedissonClient client = getClient();
		RLock lock = client.getLock(lockName);
		try {
			boolean b = lock.tryLock();
			if (b) {
				threadLocal.set(lock);
				log.info(Thread.currentThread().getName() + "lock成功");
				return true;
			} else {
				log.info(Thread.currentThread().getName() + "等待lock");
				Thread.sleep(1000);
				return lock(lockName);
			}
		} catch (InterruptedException e) {
			log.error(Thread.currentThread().getName() + "lock失败", e.getMessage());
		}
		return false;
	}

	public void unLock(String lockName) {
		threadLocal.get().unlock();
		log.info(Thread.currentThread().getName() + "lock释放");
	}
}
