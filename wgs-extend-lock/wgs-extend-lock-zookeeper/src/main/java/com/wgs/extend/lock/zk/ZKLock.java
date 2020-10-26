package com.wgs.extend.lock.zk;

import com.wgs.extend.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 分布式锁
 *
 * @ClassName：ZKLock
 * @Author wanggsh
 * @Date 2020年09月07日 14:13
 * @Version 1.0
 */
@Slf4j
@Component
@ConfigurationProperties(prefix = "wgs.extend.lock.zk")
public class ZKLock {


	/**
	 * zookeeper上锁的根目录路径，要以/结尾
	 */
//	@Value("${wgs.extend.lock.path:/distributed-lock/}")
	private String lockPath;
	private CuratorFramework client;

	/**
	 * 系统使用过的锁记录，如果有，不再重复创建
	 * 这里lock和lockMulti方法都没有同步，不能保证每个path的InterProcessMutex是同一个对象，但是在zk那里是唯一的路径，所以这里应该不会影响锁的结果
	 */
	private final Map<String, InterProcessMutex> mutexMap = new HashMap<>();

	/**
	 * 线程单种锁
	 */
	private final InheritableThreadLocal<InterProcessMutex> threadLocal = new InheritableThreadLocal<>();

	/**
	 * 线程多种锁，一种锁释放之前可再次占用其它类型的锁
	 * 锁的释放和占用顺序相反，不按顺序释放理论上不影响，逻辑上不合适
	 */
	private final InheritableThreadLocal<Map<String, InterProcessMutex>> threadLocalMulti = new InheritableThreadLocal<>();

	public ZKLock() {
	}

	public ZKLock(String url, String lockPath) {
		this.lockPath = lockPath;
		client= CuratorFrameworkFactory.builder().connectString(url)
				.sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
		client.start();
	}

	/**
	 * 单种锁加锁
	 * 分布式锁使用注意：
	 * 		同一个lockType和target，调用n次lock，就要保证跟着调用n次unlock，不然锁不能释放
	 * 		不同的lockType和target，只能成对调用一次lock和unlock之后，才能继续调用lock和unlock，不能连续调用lock
	 *
	 * @param lockType 当前锁的类型，比如系统订单，系统积分账户
	 * @param target 当前锁类型下的单个具体实例，可以是id，要保证整个系统唯一，比如类型是系统积分账户，这里就是具体某个人的账户id
	 * @Author  wanggsh
	 * @Date    2020-10-08 14:51
	 * @Version 1.0
	 */
	public void lock(String lockType, String target) {
		InterProcessMutex interProcessMutex;
		if (StringUtils.isEmpty(target)) {
			interProcessMutex = mutexMap.get(lockType);
		} else {
			interProcessMutex = mutexMap.get(lockType.concat(target));
		}
		InterProcessMutex zkMutex;
		if (interProcessMutex == null) {
			if (StringUtils.isEmpty(target)) {
				zkMutex = new InterProcessMutex(client, lockPath + lockType);
				mutexMap.put(lockType, zkMutex);
			} else {
				zkMutex = new InterProcessMutex(client, lockPath + lockType + "/" + target);
				mutexMap.put(lockType.concat(target), zkMutex);
			}
		} else {
			zkMutex = interProcessMutex;
		}
		threadLocal.set(zkMutex);
		try {
			zkMutex.acquire();
		} catch (Exception e) {
			log.error("【{}】加锁失败：{}", lockType, e.getMessage());
			return;
		}
		log.info("【{}】加锁成功", lockType);
	}

	/**
	 *
	 * 单种锁释放
	 * @param lockType 这个lockType要和上一次lock的适合使用一样的
	 * @Author  wanggsh
	 * @Date    2020-10-16 11:38
	 * @Version 1.0
	 */
	public void unlock(String lockType) {
		try {
			threadLocal.get().release();
			log.info("【{}】解锁成功，等待处理{}个", lockType, threadLocal.get().getParticipantNodes().size());
		} catch (Exception e) {
			log.error("【{}】解锁失败：{}", lockType, e.getMessage());
			try {
				Thread.sleep(1000);
				unlock(lockType);
			} catch (InterruptedException e1) {
			}
		}
	}



	/**
	 * 多种锁加锁
	 *
	 * @Author  wanggsh
	 * @Date    2020-10-16 11:39
	 * @Version 1.0
	 */
	public void lockMulti(String lockType, String target) {
		Map<String, InterProcessMutex> mutexMapMulti = threadLocalMulti.get();
		if (mutexMapMulti == null) {
			mutexMapMulti = new HashMap<>();
		}

		InterProcessMutex interProcessMutex;
		if (StringUtils.isEmpty(target)) {
			interProcessMutex = mutexMapMulti.get(lockType);
			if (interProcessMutex == null) {
				interProcessMutex = mutexMap.get(lockType);
			}
		} else {
			interProcessMutex = mutexMapMulti.get(lockType.concat(target));
			if (interProcessMutex == null) {
				interProcessMutex = mutexMap.get(lockType.concat(target));
			}
		}
		InterProcessMutex zkMutex;
		if (interProcessMutex == null) {
			if (StringUtils.isEmpty(target)) {
				zkMutex = new InterProcessMutex(client, lockPath + lockType);
				mutexMap.put(lockType, zkMutex);
				mutexMapMulti.put(lockType, zkMutex);
			} else {
				zkMutex = new InterProcessMutex(client, lockPath + lockType + "/" + target);
				mutexMap.put(lockType.concat(target), zkMutex);
				mutexMapMulti.put(lockType.concat(target), zkMutex);
			}
		}
		threadLocalMulti.set(mutexMapMulti);
		try {
			interProcessMutex.acquire();
		} catch (Exception e) {
			log.error("【{}】加锁失败：{}", lockType, e.getMessage());
			return;
		}
		log.info("【{}】加锁成功", lockType);
	}


	/**
	 * 多种锁释放锁
	 *
	 * @Author  wanggsh
	 * @Date    2020-10-16 12:13
	 * @Version 1.0
	 */
	public void unlockMulti(String lockType, String target) {
		try {
			threadLocalMulti.get().get(lockType.concat(target)).release();
			log.info("【{}】解锁成功，等待处理{}个", lockType, threadLocal.get().getParticipantNodes().size());
		} catch (Exception e) {
			log.error("【{}】解锁失败：{}", lockType, e.getMessage());
			try {
				Thread.sleep(1000);
				unlock(lockType);
			} catch (InterruptedException e1) {
			}
		}
	}

}
