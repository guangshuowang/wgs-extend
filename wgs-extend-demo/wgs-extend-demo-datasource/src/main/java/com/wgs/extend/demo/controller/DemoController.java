package com.wgs.extend.demo.controller;

import com.wgs.extend.demo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @ClassName：DemoController
 * @Author wanggsh
 * @Date 2020年11月06日 16:42
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {

	@Autowired
	private DemoService demoService;

	@GetMapping("/get/{id}")
	public String getDemo(@PathVariable String id) {
		return demoService.get(id);
	}
}
