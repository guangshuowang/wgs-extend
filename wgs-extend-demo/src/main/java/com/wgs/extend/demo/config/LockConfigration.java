package com.wgs.extend.demo.config;

import com.wgs.extend.lock.zk.ZKLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

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

	@Value("${spring.dubbo.registry.address}")
	private String url;

	@Bean
	public ZKLock initLock() {
//		String url = environment.getProperty("spring.dubbo.registry.address");
		log.info("初始化lock，zk地址：{}", url);
		return new ZKLock(url);
	}
}
