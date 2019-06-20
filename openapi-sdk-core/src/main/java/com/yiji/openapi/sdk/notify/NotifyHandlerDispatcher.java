/**
 * create by zhike@yiji.com
 * date:2015年3月20日
 */
package com.yiji.openapi.sdk.notify;

import java.util.Map;

/**
 * @author zhike@yiji.com
 *
 */
public interface NotifyHandlerDispatcher {

	void dispatch(String notifyUrl, Map<String, String> notifyData);

}
