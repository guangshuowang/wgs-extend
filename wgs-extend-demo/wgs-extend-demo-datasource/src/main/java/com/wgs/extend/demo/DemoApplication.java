package com.wgs.extend.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;


/**
 *
 * api配置启动，适合springboot
 *
 *
 * @Author  wanggsh
 * @Date    2020-10-27 15:40
 * @Version 1.0
 */
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
