/**
 * Copyright(c) 2004-2018 eaju.com, All Rights Reserved. Project: eaju-marketing-service Author: admin Createdate:
 * 下午5:47:49 Version: 1.0
 */
package com.wgs.extend.dubbo.tracelog.aop;

import com.wgs.extend.common.utils.DateUtils;
import com.wgs.extend.dubbo.tracelog.ThreadMdcUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.RpcContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 链路日志切面
 * @Param
 *
 * @Author  wanggsh
 * @Date    2020-08-12 11:34
 * @Version 1.0
 */
@Order(value = 1)
@Aspect
@Component
@Slf4j
public abstract class TraceLogAspect {

	@Pointcut("execution(* com.wgs.*.controller..*.*(..))")
	public void controllerPointcut() {}

	@Pointcut("execution(* com.wgs.*.service..*.*(..))")
	public void servicePointcut() {}

	@Before("controllerPointcut()")
    public void controllerBefore(JoinPoint point) {
		String attachment = RpcContext.getContext().getAttachment(ThreadMdcUtil.LOG_TRACE_ID);
		if (StringUtils.isEmpty(attachment)) {
			ThreadMdcUtil.setTraceIdIfAbsent();
			log.debug("controller入口");
			RpcContext.getContext().setAttachment(ThreadMdcUtil.LOG_TRACE_ID, ThreadMdcUtil.getTraceId());
		} else {
			ThreadMdcUtil.setTraceId(attachment);
			log.debug("controller内部");
		}
		log.info("调用开始：{}-{}", point.getTarget().getClass().getSimpleName(), point.getSignature().getName());
		TraceLogThreadLocal.setStartTime(new Date());
    }


	@Before("servicePointcut()")
    public void serviceBefore(JoinPoint point) {
		log.info("服务aop：{}-{}", point.getTarget().getClass().getSimpleName(), point.getSignature().getName());
		String traceId = ThreadMdcUtil.getTraceId();
		if (StringUtils.isEmpty(traceId)) {
			log.error("这个情况不应该发生：{}-{}", point.getTarget().getClass().getSimpleName(), point.getSignature().getName());
			ThreadMdcUtil.setTraceIdIfAbsent();
			RpcContext.getContext().setAttachment(ThreadMdcUtil.LOG_TRACE_ID, ThreadMdcUtil.getTraceId());
		} else {
			ThreadMdcUtil.setTraceId(traceId);
		}
		TraceLogThreadLocal.setStartTime(new Date());
		log.info("调用开始：{}-{}-{}", point.getTarget().getClass().getSimpleName(),
				point.getSignature().getName(), DateUtils.formatDateTime(TraceLogThreadLocal.get()));
    }




    @After("controllerPointcut()")
    public void controllerAfter(JoinPoint point) {
		saveControllerTraceLog(point.getTarget().getClass().getSimpleName(), point.getSignature().getName(), DateUtils.period(TraceLogThreadLocal.get()));
		log.info("调用结束：{}-{}-{}", point.getTarget().getClass().getSimpleName(), point.getSignature().getName(), DateUtils.pastTime(TraceLogThreadLocal.get()));
    }

    @After("servicePointcut()")
    public void serviceAfter(JoinPoint point) {
		log.info("调用结束：{}-{}-{}", point.getTarget().getClass().getSimpleName(), point.getSignature().getName(), DateUtils.pastTime(TraceLogThreadLocal.get()));
    }

	public abstract void saveControllerTraceLog(String className, String methodName, long time);

}
