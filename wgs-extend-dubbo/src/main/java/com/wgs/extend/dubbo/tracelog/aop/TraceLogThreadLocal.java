package com.wgs.extend.dubbo.tracelog.aop;

import java.util.Date;

/**
 * @Description
 * @ClassName：TraceLogThreadLocal
 * @Author wanggsh
 * @Date 2020年08月12日 14:26
 * @Version 1.0
 */
public class TraceLogThreadLocal {

	private static final ThreadLocal<Date> THREAD_LOCAL_DURATION = new InheritableThreadLocal<>();

	public static void setStartTime(Date date) {
		THREAD_LOCAL_DURATION.set(date);
	}

	public static Date get() {
		return THREAD_LOCAL_DURATION.get();
	}
}
