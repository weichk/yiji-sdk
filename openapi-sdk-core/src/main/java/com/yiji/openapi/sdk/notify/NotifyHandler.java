/**
 * coding by zhike@yiji.com
 */
package com.yiji.openapi.sdk.notify;


import com.yiji.openapi.sdk.common.ApiConstants;

/**
 * @author zhike@yiji.com
 * @date 2014年6月16日
 */
public abstract class NotifyHandler implements NotifyHandlerInterface {

    public String serviceVersion() {
        return ApiConstants.VERSION_DEFAULT;
    }
}
