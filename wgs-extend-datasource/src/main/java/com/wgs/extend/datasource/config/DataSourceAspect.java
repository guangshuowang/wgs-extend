/**
 * Copyright(c) 2004-2018 eaju.com, All Rights Reserved. Project: eaju-marketing-service Author: admin Createdate:
 * 下午5:47:49 Version: 1.0
 */
package com.wgs.extend.datasource.config;

import com.wgs.extend.datasource.DataSourceThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Description:
 * @project eaju-marketing-service
 * @class DataSourceAspect.java
 * @author xiongxianze
 * @version 1.0
 * @date 2019年1月8日 下午5:47:49
 * @modified by wanggsh/2020-05-19 15:12
 */
@Order(value = 1)
@Aspect
@Slf4j
@Component
public class DataSourceAspect {

	/*满足包路径*/
	@Pointcut("execution(* *..service.impl..*.*(..))")
	public void servicePackageAspect() {}
	/*满足注解*/
	@Pointcut("!@annotation(com.wgs.extend.common.annotation.NoStatus))")
	public void serviceAnnotationAspect() {}


//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//		try {
//			setActiveMqService((ActiveMqService) applicationContext.getBean("activeMqService"));
//			log.info("完成切面属性设置：activeMqService");
//		} catch (BeansException e) {
//			log.warn("缺少【activeMqService】配置，不支持账户支付结果通知");
//		}
//	}
//	private ActiveMqService activeMqService;
//	public void setActiveMqService(ActiveMqService activeMqService) {
//		this.activeMqService = activeMqService;
//	}


	/**
	 *
	 * 前置方法，方法调用之前就要确定数据源
	 * @Author  wanggsh
	 * @Date    2020-11-06 10:18
	 * @Version 1.0
	 */
	@Before("servicePackageAspect() && serviceAnnotationAspect()")
    public void before(JoinPoint point) {
		String[] parameterNames = ((MethodSignature) point.getSignature()).getParameterNames();
		Object[] args = point.getArgs();
		String appId = null;
		for (int i = 0; i < parameterNames.length; i++) {
			if ("dataSourceId".equals(parameterNames[i])) {
				appId = String.valueOf(args[i]);
			}
		}
		if (Objects.isNull(appId)) {
			try {
				Method method = getMethod(args[0].getClass(), "getDataSourceId");
				method.setAccessible(true);
				appId = (String) method.invoke(args[0]);
			} catch (Exception e) {
				log.error("获取appId出错：{}", e.getMessage());
			}
		}
		DataSourceThreadLocal.setDataSource(appId);
    }

    /**
     *
     * 后置方法，方法调用完成要清空数据源
     * @Author  wanggsh
     * @Date    2020-11-06 10:19
     * @Version 1.0
     */
    @After("servicePackageAspect() && !serviceAnnotationAspect()")
    public void after(JoinPoint point) {
		DataSourceThreadLocal.clear();
    }


    /**
     *
     * 支付成功之后的业务处理异步化，不包含微信支付
	 * 此切面方法只有一个参数就是TbOrder类型，且一定存在两个参数：orderId,appId
     * @Author  wanggsh
     * @Date    2020-11-02 17:09
     * @Version 1.0
     */
//    public void afterPay(ProceedingJoinPoint point) {
//		try {
//			point.proceed();
//			Object param = point.getArgs()[0];
//			String appId = null;
//			String orderId = null;
//			try {
//				Method method1 = getMethod(param.getClass(), "getAppId");
//				method1.setAccessible(true);
//				appId = (String) method1.invoke(param);
//
//				Method method2 = getMethod(param.getClass(), "getOrderId");
//				method2.setAccessible(true);
//				orderId = (String) method2.invoke(param);
//			} catch (Exception ignored) {
//			}
//			log.info("异步通知订单：{}，{}， {}", point.getSignature().getName(), appId, orderId);
//			// 支持积分商城平台，积分支付入账
//			if (!StringUtil.isEmpty(orderId) && EajuConstant.DATASOURCE_DEFAULT.equals(appId)) {
//				if (activeMqService != null) {
//					MqMessage mqMessage = new MqMessage();
//					mqMessage.setAppId(appId);
//					mqMessage.setBusinessType(MsgBussType.ORDER_PAID.type);
//					mqMessage.setName(MsgBussType.ORDER_PAID.name);
//					mqMessage.setData(orderId);
//					// 支付通知消息
//					activeMqService.send(mqMessage);
//				}
//			} else {
//				log.error("支付通知未获取订单号：{}，{}", point.getSignature().getName());
//			}
//		} catch (Throwable throwable) {
//			log.error("支付异常：{}", throwable.getMessage());
//		} finally {
//			DataSourceThreadLocal.clear();
//		}
//    }

    /**
     *
     * 订单退款成功之后业务处理
     * @Author  wanggsh
     * @Date    2020-11-05 10:37
     * @Version 1.0
     */
//    public void afterRefund(ProceedingJoinPoint point) {
//		try {
//			point.proceed();
//			Object param = point.getArgs()[0];
//			String appId = null;
//			String orderId = null;
//			try {
//				Method method1 = getMethod(param.getClass(), "getAppId");
//				method1.setAccessible(true);
//				appId = (String) method1.invoke(param);
//
//				Method method2 = getMethod(param.getClass(), "getOrderId");
//				method2.setAccessible(true);
//				orderId = (String) method2.invoke(param);
//			} catch (Exception ignored) {
//			}
//			log.info("退款订单处理：{}，{}，{}", point.getSignature().getName(), appId, orderId);
//			// 支持积分商城平台，积分支付入账
//			if (!StringUtil.isEmpty(orderId) && EajuConstant.DATASOURCE_DEFAULT.equals(appId)) {
//				if (activeMqService != null) {
//					MqMessage mqMessage = new MqMessage();
//					mqMessage.setAppId(appId);
//					mqMessage.setBusinessType(MsgBussType.ORDER_REFUND.type);
//					mqMessage.setName(MsgBussType.ORDER_REFUND.name);
//					mqMessage.setData(orderId);
//					// 支付通知消息
//					activeMqService.send(mqMessage);
//				}
//			} else {
//				log.error("退款通知未获取订单号：{}，{}", point.getSignature().getName());
//			}
//		} catch (Throwable throwable) {
//			log.error("支付异常：{}", throwable.getMessage());
//		} finally {
//			DataSourceThreadLocal.clear();
//		}
//    }

	private Method getMethod(Class clazz, String methodName) {
		try {
			return clazz.getDeclaredMethod(methodName);
		} catch (Exception e) {
			try {
				return clazz.getMethod(methodName);
			} catch (NoSuchMethodException e1) {
				return getMethod(clazz.getSuperclass(), methodName);
			}
		}
	}
}
