/**
 * coding by zhike@yiji.com
 */
package com.yiji.openapi.sdk.message;

import com.yiji.openapi.sdk.common.message.ApiMessage;

/**
 * 报文实例工厂
 * 
 * @author zhike@yiji.com
 * @date 2014年6月13日
 */
public interface MessageFactory {
	
	ApiMessage getRequest(String serviceName);
	
	ApiMessage getResponse(String serviceName);

	ApiMessage getNotify(String serviceName);

	ApiMessage getReturn(String serviceName);
}
