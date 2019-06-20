/**
 * coding by zhike@yiji.com
 */
package com.yiji.openapi.sdk.notify;

import com.yiji.openapi.sdk.common.message.ApiMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 默认通知处理接口
 * 
 * 只打印通知结果 商户系统可根据业务情况开发自己的通知处理器实现.
 * 
 * @author zhike@yiji.com
 * @date 2014年6月16日
 */
public class DefaultNotifyHandler extends NotifyHandler {
	private static final Log logger = LogFactory.getLog(DefaultNotifyHandler.class);

	@Override
	public void handleNotify(ApiMessage notify) {
		logger.info("异步通知 打印：" + notify);
	}

	@Override
	public String serviceKey() {
		return "default";
	}

}
