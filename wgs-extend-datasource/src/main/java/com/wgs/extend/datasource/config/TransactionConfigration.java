package com.wgs.extend.datasource.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description
 * @ClassName：TransactionConfigration
 * @Author wanggsh
 * @Date 2020年11月06日 10:44
 * @Version 1.0
 */
@Slf4j
@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
public class TransactionConfigration {

	@Bean
	public PlatformTransactionManager transactionManager(DynamicDataSource dynamicDataSource) {
		return new DataSourceTransactionManager(dynamicDataSource);
	}

}
