package com.wgs.extend.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.ImportResource;


/**
 *
 * 注解启动，适合springboot或spring项目
 *
 *
 * @Author  wanggsh
 * @Date    2020-10-27 15:40
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScans({@ComponentScan("com.wgs.extend.lock.zk"), @ComponentScan("com.wgs.extend.lock.redis")})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
