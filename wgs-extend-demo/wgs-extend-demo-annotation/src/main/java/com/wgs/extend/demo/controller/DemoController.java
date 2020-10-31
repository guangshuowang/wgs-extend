package com.wgs.extend.demo.controller;

import com.wgs.extend.fdfs.FastDFSClient;
import com.wgs.extend.lock.redis.RedisLock;
import com.wgs.extend.lock.zk.ZKLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description
 * @ClassName：DemoController
 * @Author wanggsh
 * @Date 2020年10月15日 11:13
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/demo")
public class DemoController {

	@Autowired
	private ZKLock zkLock;

	@Autowired
	private RedisLock redisLock;

	@GetMapping(value = "/lock", produces = {"application/json;charset=UTF-8"})
	public String lock() {
		zkLock.lock("test", null);
		log.info("业务处理。。。");
//		return JSON.toJSONString(sysUserService.getUserPlatform("eaju", "a123456."));
		zkLock.unlock("test");
		redisLock.lock("test");
		log.info("业务处理。。。");
		redisLock.unLock("test");
		return null;
	}
}
