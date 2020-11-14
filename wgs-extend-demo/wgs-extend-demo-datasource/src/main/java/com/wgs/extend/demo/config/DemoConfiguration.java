package com.wgs.extend.demo.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.wgs.extend.datasource.config.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;

/**
 * @Description
 * @ClassName：DemoConfiguration
 * @Author wanggsh
 * @Date 2020年11月11日 09:53
 * @Version 1.0
 */
@Slf4j
@MapperScan(basePackages = "com.wgs.extend.demo.mapper")
@Configuration
public class DemoConfiguration {


//	@Override
//	public void afterPropertiesSet() throws Exception {
//		SqlSessionFactoryBean sqlSessionFactoryBean = applicationContext.getBean(SqlSessionFactoryBean.class);
//		DynamicDataSource dynamicDataSource = applicationContext.getBean(DynamicDataSource.class);
//		sqlSessionFactoryBean.setDataSource(dynamicDataSource);
//		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
//	}
//
//	private ApplicationContext applicationContext;
//
//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//		this.applicationContext = applicationContext;
//
//	}

	@Bean
	@DependsOn("dynamicDataSource")
	public MybatisSqlSessionFactoryBean initSqlSessionFactoryBean(DynamicDataSource dynamicDataSource) {
		log.info("初始化sqlSessionFactory");
		MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dynamicDataSource);
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
		return sqlSessionFactoryBean;
	}

}
