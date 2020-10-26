<<<<<<< HEAD
<<<<<<< HEAD
package com.wgs.extend.dubbo.tracelog.filter;

import com.wgs.extend.common.utils.DateUtils;
import com.wgs.extend.dubbo.tracelog.ThreadMdcUtil;
=======
=======
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
package com.eaju.extend.tracelog.filter;

import com.eaju.common.utils.DateUtils;
import com.eaju.extend.tracelog.ThreadMdcUtil;
<<<<<<< HEAD
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
=======
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @Description
 * @ClassName：DubboConsumerContextFilter
 * @Author wanggsh
 * @Date 2020年08月14日 12:06
 * @Version 1.0
 */
@Slf4j
@Activate
public class DubboConsumerContextFilter implements Filter {

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		Date startTime = new Date();
		String attachment = RpcContext.getContext().getAttachment(ThreadMdcUtil.LOG_TRACE_ID);
//		if (Objects.isNull(TraceLogThreadLocal.get())) {
//			log.info("未设置时间：{}", attachment);
//			TraceLogThreadLocal.setStartTime(new Date());
//		} else {
//			log.error("已设置时间，时间计算不准：{}-{}-{}", attachment, invoker.getInterface().getSimpleName(), invocation.getMethodName());
//		}
		if (StringUtils.isEmpty(attachment)) {
			ThreadMdcUtil.setTraceIdIfAbsent();
//			log.info("服务开始：{}-{}-{}-{}", ThreadMdcUtil.getTraceId(), invoker.getInterface().getSimpleName(), invocation.getMethodName(), DateUtils.formatDateTime(TraceLogThreadLocal.get()));
			RpcContext.getContext().setAttachment(ThreadMdcUtil.LOG_TRACE_ID, ThreadMdcUtil.getTraceId());
		} else {
			ThreadMdcUtil.setTraceId(attachment);
//			log.info("服务内部：{}-{}-{}-{}", ThreadMdcUtil.getTraceId(), invoker.getInterface().getSimpleName(), invocation.getMethodName(), DateUtils.formatDateTime(TraceLogThreadLocal.get()));
		}
		log.info("服务开始：{}-{}-{}", invoker.getInterface().getSimpleName(), invocation.getMethodName(), DateUtils.formatDateTime(startTime));
		Result invoke = invoker.invoke(invocation);
		log.info("服务结束：{}-{}-{}", invoker.getInterface().getSimpleName(), invocation.getMethodName(),  DateUtils.pastTime(startTime));
		return invoke;
	}
}
