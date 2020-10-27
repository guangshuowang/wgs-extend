package com.wgs.extend.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.ImportResource;


/**
 * 配置文件加载方式
 *
 * @Author  wanggsh
 * @Date    2020-10-27 15:10
 * @Version 1.0
 */
@SpringBootApplication
@ImportResource("classpath*:application-fdfs.xml")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
