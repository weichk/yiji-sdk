/**
 * create by zhike@yiji.com
 * date:2015年4月12日
 */
package com.yiji.openapi.sdk.notify;

/**
 * @author zhike@yiji.com
 *
 */
public interface NotifyHandlerFactory {

	NotifyHandler getNotifyHandler(String serviceKey);

}
