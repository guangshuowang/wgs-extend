package com.wgs.extend.lock.redis;

<<<<<<< HEAD
<<<<<<< HEAD
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.concurrent.TimeUnit;

=======
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
=======
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
/**
 * @Description
 * @ClassName：RedisLock
 * @Author wanggsh
 * @Date 2020年10月22日 21:08
 * @Version 1.0
 */
<<<<<<< HEAD
<<<<<<< HEAD
@Slf4j
@Component
@ConfigurationProperties(prefix = "wgs.extend.lock.redis")
public class RedisLock {


//	@Value("${url}")
	private String url;
	private Config config;

	private final InheritableThreadLocal<RLock> threadLocal = new InheritableThreadLocal<>();

	public RedisLock() {
	}

	public RedisLock(String url) {
		this.url = url;
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
=======
public class RedisLock {
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
=======
public class RedisLock {
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
}
