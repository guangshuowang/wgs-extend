package com.wgs.extend.demo.service.impl;

import com.wgs.extend.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * @Description
 * @ClassName：DemoServiceImpl
 * @Author wanggsh
 * @Date 2020年10月31日 15:13
 * @Version 1.0
 */
@Slf4j
@Service(version = "${dubbo.service.version}", retries = 0, timeout = 5000)
public class DemoServiceImpl implements DemoService {


	@Override
	public String hello(String param) {
		log.info("调用参数：{}", param);
		return "ok";
	}
}
