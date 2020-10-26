/**
 * Copyright(c) 2004-2018 eaju.com, All Rights Reserved. Project: eaju-marketing-service Author: admin Createdate:
 * 下午5:47:49 Version: 1.0
 */
package com.eaju.extend.tracelog.aop;

import com.alibaba.fastjson.JSON;
import com.eaju.common.entity.result.ModelListResult;
import com.eaju.common.entity.result.ModelResult;
import com.eaju.common.entity.result.ResponseResult;
import com.eaju.common.entity.result.Result;
import com.eaju.common.entity.result.ResultSet;
import com.eaju.common.utils.AESUtil;
import com.eaju.common.utils.DateUtils;
import com.eaju.extend.tracelog.ThreadMdcUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.RpcContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Objects;

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

	@Pointcut("execution(* com.eaju.*.controller..*.*(..))")
	public void controllerPointcut() {}

	@Pointcut("execution(* com.eaju.*.service..*.*(..))")
	public void servicePointcut() {}

	/**
	 * 加密接口切面
	 */
	@Pointcut("@annotation(com.eaju.common.annotation.AesApi)")
	public void aesAspect() {}


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




    /**
     *
     * 接口返回数据加密处理
     * @Author  wanggsh
     * @Date    2020-08-14 10:26
     * @Version 1.0
     */
	@AfterReturning(returning = "result", pointcut = "aesAspect()")
	public void afterReturning(JoinPoint joinPoint, Object result) throws Exception {
		if (result instanceof ModelListResult) {
			ModelListResult modelListResult = (ModelListResult) result;
			if (ResultSet.SUCCESS.equals(modelListResult.getCode()) && !Objects.isNull(modelListResult.getModel())) {
				log.info("ModelListResult加密处理");
				modelListResult.setModel(AESUtil.encrypt(JSON.toJSONString(modelListResult.getModel()).getBytes(), AESUtil.key.getBytes(), AESUtil.generateIV(AESUtil.iv.getBytes())));
			}
		} else if (result instanceof ModelResult) {
			ModelResult model = (ModelResult) result;
			if (ResultSet.SUCCESS.equals(model.getCode()) && !Objects.isNull(model.getModel())) {
				log.info("ModelResult加密处理");
				model.setModel(AESUtil.encrypt(JSON.toJSONString(model.getModel()).getBytes(), AESUtil.key.getBytes(), AESUtil.generateIV(AESUtil.iv.getBytes())));
			}
		} else if (result instanceof Result) {
			Result res = (Result) result;
			if (Objects.equals(HttpStatus.OK.value(), res.getCode()) && !Objects.isNull(res.getResult())) {
				log.info("Result加密处理");
				res.setResult(AESUtil.encrypt(JSON.toJSONString(res.getResult()).getBytes(), AESUtil.key.getBytes(), AESUtil.generateIV(AESUtil.iv.getBytes())));
			}
		} else if (result instanceof ResponseResult) {
			ResponseResult response = (ResponseResult) result;
			if (Objects.equals(HttpStatus.OK.value(), response.getReturnCode()) && !Objects.isNull(response.getData())) {
				log.info("ResponseResult加密处理");
				response.setData(AESUtil.encrypt(JSON.toJSONString(response.getData()).getBytes(), AESUtil.key.getBytes(), AESUtil.generateIV(AESUtil.iv.getBytes())));
			}
		} else {
			log.error("无对应加密结果类型");
		}
	}
}
