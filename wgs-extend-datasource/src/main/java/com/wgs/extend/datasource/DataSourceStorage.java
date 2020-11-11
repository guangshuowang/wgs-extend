package com.wgs.extend.datasource;

import java.util.Map;

/**
 * 数据源存储变量，转移数据源配置的时候从这里获取，然后设置的DynamicDataSource中
 * @ClassName：DataSourceStorage
 * @Author wanggsh
 * @Date 2020年04月30日 10:35
 * @Version 1.0
 */
public class DataSourceStorage {

	/*
	用户配置的全部数据源都放在这里
	 */
	private Map<Object, Object> dataSourceMap;

	public Map<Object, Object> getDataSourceMap() {
		return dataSourceMap;
	}

	public void setDataSourceMap(Map<Object, Object> dataSourceMap) {
		this.dataSourceMap = dataSourceMap;
	}
}
