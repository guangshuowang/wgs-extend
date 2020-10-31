package com.wgs.extend.demo.controller;

import com.wgs.extend.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @ClassName：DemoController
 * @Author wanggsh
 * @Date 2020年10月31日 15:50
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {

	@Reference(version = "${dubbo.service.version}", retries = 0, timeout = 5000, check = false)
	private DemoService demoService;


	@GetMapping("/hello/dubbo")
	public String helloDubbo() {
		String result = demoService.hello("hell dubbo");
		log.info("返回结果：{}", result);
		return result;
	}
}
