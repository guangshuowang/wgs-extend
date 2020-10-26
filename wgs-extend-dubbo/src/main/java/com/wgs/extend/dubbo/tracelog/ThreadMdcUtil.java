/**
 * @(#)ThreadMdcUtil.java, 7月 31, 2020.
 * <p>
 * Copyright 2020 bb.bj.cn. All rights reserved.
 * bb.bj.cn PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.eaju.extend.tracelog;

import com.eaju.common.utils.StringUtil;
import org.slf4j.MDC;


public class ThreadMdcUtil {

    public final static String LOG_TRACE_ID = "traceId";

    public static void setTraceIdIfAbsent() {
        if (MDC.get(LOG_TRACE_ID) == null) {
            MDC.put(LOG_TRACE_ID, StringUtil.get32UUID());
        }
    }

    public static String getTraceId() {
        return MDC.get(LOG_TRACE_ID);
    }

    public static void setTraceId(String traceId) {
        MDC.put(LOG_TRACE_ID, traceId);
    }

}