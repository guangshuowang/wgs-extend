/**
 * @(#)ThreadMdcUtil.java, 7月 31, 2020.
 * <p>
 * Copyright 2020 bb.bj.cn. All rights reserved.
 * bb.bj.cn PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wgs.extend.dubbo.tracelog;

import com.wgs.extend.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

@Slf4j
public class ThreadMdcUtil {

    public final static String LOG_TRACE_ID = "traceId";

    public static void setTraceIdIfAbsent() {
        if (MDC.get(LOG_TRACE_ID) == null) {
            MDC.put(LOG_TRACE_ID, StringUtils.get32UUID());
        }
    }

    public static String getTraceId() {
    	setTraceIdIfAbsent();
    	log.info("查询traceId：{}", Thread.currentThread().getName());
        return MDC.get(LOG_TRACE_ID);
    }

    public static void setTraceId(String traceId) {
        MDC.put(LOG_TRACE_ID, traceId);
    }

}