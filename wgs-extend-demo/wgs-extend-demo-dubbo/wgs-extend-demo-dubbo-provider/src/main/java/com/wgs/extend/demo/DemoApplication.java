package com.wgs.extend.demo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 *
 * api配置启动，适合springboot
 *
 *
 * @Author  wanggsh
 * @Date    2020-10-27 15:40
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
//@ComponentScans({
//		@ComponentScan("com.wgs.extend.dubbo.tracelog.aop"),@ComponentScan("com.wgs.extend.dubbo.tracelog.filter")
//})
@ComponentScan(basePackages = {"com.wgs.extend.dubbo.tracelog","com.wgs.extend.demo"})
public class DemoApplication {

	public static void main(String[] args) throws UnknownHostException {
		ConfigurableApplicationContext application = new SpringApplicationBuilder(DemoApplication.class)
				.listeners((ApplicationListener<ApplicationEnvironmentPreparedEvent>) event -> {
					Environment environment = event.getEnvironment();
					int port = environment.getProperty("embedded.zookeeper.port", int.class);
					new EmbeddedZooKeeper(port, false).start();
				})
				.run(args);
		Environment env = application.getEnvironment();
		String ip = InetAddress.getLocalHost().getHostAddress();
		String port = env.getProperty("server.port");
		log.info("\n----------------------------------------------------------\n\t" +
				"Application is running! Access URLs:\n\t" +
				"Local: \t\thttp://localhost:" + port + "\n\t" +
				"External: \thttp://" + ip + ":" + port + "\n\t" +
				"----------------------------------------------------------");
	}

}
