package com.wgs.extend.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath*:application-fdfs.xml")
//@ComponentScans({@ComponentScan("com.wgs.extend.dubbo"), @ComponentScan("com.wgs.extend.fdfs")})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
