package com.eaju.extend.tracelog.filter;

import com.eaju.extend.tracelog.ThreadMdcUtil;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;

/**
 * @Description
 * @ClassName：DubboProviderContextFilter
 * @Author wanggsh
 * @Date 2020年08月14日 10:41
 * @Version 1.0
 */
//@Slf4j
@Activate
public class DubboProviderContextFilter implements Filter {

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		String attachment = RpcContext.getContext().getAttachment(ThreadMdcUtil.LOG_TRACE_ID);
		ThreadMdcUtil.setTraceId(attachment);
		return invoker.invoke(invocation);
	}
}
