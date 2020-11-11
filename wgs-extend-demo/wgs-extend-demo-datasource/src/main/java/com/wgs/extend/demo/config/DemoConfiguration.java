package com.wgs.extend.demo.config;

import com.wgs.extend.datasource.config.DynamicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description
 * @ClassName：DemoConfiguration
 * @Author wanggsh
 * @Date 2020年11月11日 09:53
 * @Version 1.0
 */
@Configuration
@MapperScan(basePackages = "com.wgs.extend.demo.mapper")
public class DemoConfiguration {


	@Bean
	@DependsOn("dynamicDataSource")
	public SqlSessionFactoryBean initSqlSessionFactoryBean(DynamicDataSource dynamicDataSource) {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dynamicDataSource);
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
		return sqlSessionFactoryBean;
	}

}
