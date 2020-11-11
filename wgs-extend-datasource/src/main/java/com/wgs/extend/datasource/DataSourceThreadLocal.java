/**
 * Copyright(c) 2004-2018 eaju.com, All Rights Reserved. Project: eaju-marketing-service Author: admin Createdate:
 * 下午5:57:36 Version: 1.0
 */
package com.wgs.extend.datasource;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 *
 *	线程本地变量传递数据源选择key
 *
 * @Author  wanggsh
 * @Date    2020-04-15 16:10
 * @Version 1.0
 */
@Slf4j
public class DataSourceThreadLocal {

	/*线程传递数据源，保存数据源的key*/
    private static final ThreadLocal<String> THREAD_LOCAL = new InheritableThreadLocal<>();

    public static void setDataSource(String dataSource) {
		log.info("已有数据源：{}-{}", Thread.currentThread().getName(), THREAD_LOCAL.get());
		log.info("设置数据源：{}-{}", Thread.currentThread().getName(), dataSource);
		if (Objects.isNull(dataSource)) {
			throw new RuntimeException("未能确定数据源");
		}
		THREAD_LOCAL.set(dataSource);
    }

    public static String getDataSource() {
		log.info("获取数据源：{}-{}", Thread.currentThread().getName(), THREAD_LOCAL.get());
		return THREAD_LOCAL.get();
    }

	public static void clear() {
    	log.info("清空数据源：{}", Thread.currentThread().getName());
    	THREAD_LOCAL.remove();
	}

}
