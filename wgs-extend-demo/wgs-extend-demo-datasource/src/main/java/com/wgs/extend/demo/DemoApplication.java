package com.wgs.extend.demo;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;


/**
 *
 * api配置启动，适合springboot
 *
 *
 * @Author  wanggsh
 * @Date    2020-10-27 15:40
 * @Version 1.0
 */
@ComponentScan(basePackages = {"com.wgs.extend.datasource.config","com.wgs.extend.demo"})
@ImportResource({"classpath:datasource/application-datasource-all.xml"})
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
