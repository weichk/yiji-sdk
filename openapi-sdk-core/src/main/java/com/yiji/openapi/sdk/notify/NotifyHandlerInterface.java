/*
 * www.yiji.com Inc.
 * Copyright (c) 2016 All Rights Reserved
 */

/*
 * 修订记录:
 * zhike@yiji.com 2016-07-29 18:04 创建
 *
 */
package com.yiji.openapi.sdk.notify;

import com.yiji.openapi.sdk.common.message.ApiMessage;

/**
 * @author zhike@yiji.com
 */
public interface NotifyHandlerInterface {

    void handleNotify(ApiMessage notify);

    String serviceKey();
}
