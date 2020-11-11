/**
 * Copyright(c) 2004-2018 eaju.com, All Rights Reserved. Project: eaju-marketing-service Author: admin Createdate:
 * 下午6:07:16 Version: 1.0
 */
package com.wgs.extend.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * 数据源设置，
 * 引用项目需要配置：dataSourceStorage，它的属性dataSourceMap是用户配置的全部数据源存储
 * spring初始化用户配置的dataSourceStorage之后，把dataSourceStorage中的dataSourceMap设置到DynamicDataSource中，完成初始化
 * @Author  wanggsh
 * @Date    2020-04-30 10:06
 * @Version 1.0
 */
// AbstractRoutingDataSource是模板方法模式, 由子类DynamicDataSource实现determineCurrentLookupKey方法, 返回xml文件中定义的字符串
@Slf4j
//@DependsOn("dataSourceStorage")
@Component
public class DynamicDataSource extends AbstractRoutingDataSource implements ApplicationContextAware {

	private Map<Object, Object> dataSourceMap = null;

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceThreadLocal.getDataSource();
    }

	@Override
	public void afterPropertiesSet() {
		this.setTargetDataSources(dataSourceMap);
		Collection<Object> values = dataSourceMap.values();
		if (values.isEmpty()) {
			log.error("数据源为空");
		} else {
			log.info("初始化数据源数量：{}", values.size());
			this.setDefaultTargetDataSource(values.toArray()[0]);
			super.afterPropertiesSet();
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext){
		Object dataSourceObj = null;
		try {
			dataSourceObj = applicationContext.getBean("dataSourceStorage");
		} catch (BeansException e) {
			logger.error("缺少数据源【dataSourceStorage】配置");
			return;
		}
		if (!(dataSourceObj instanceof DataSourceStorage)) {
			logger.error("dataSourceStorage的类型不是DataSourceStorage");
			return;
		}
		DataSourceStorage dataSourceStorage = (DataSourceStorage) dataSourceObj;
		dataSourceMap = dataSourceStorage.getDataSourceMap();
		if (Objects.isNull(dataSourceMap)) {
			logger.error("缺少数据源【dataSourceMap】配置");
		}
	}
}
