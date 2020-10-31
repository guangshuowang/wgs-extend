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
	private FastDFSClient fastDFSClient;

	/**
	 *
	 * 文件上传
	 * @Author  wanggsh
	 * @Date    2020-10-31 14:00
	 * @Version 1.0
	 */
	@PostMapping("/upload")
	public String upload(@RequestBody MultipartFile multipartFile) {
		String upload = fastDFSClient.upload(multipartFile, null);
		log.info("上传成功，文件路径：{}", upload);
		return upload;
	}
}
