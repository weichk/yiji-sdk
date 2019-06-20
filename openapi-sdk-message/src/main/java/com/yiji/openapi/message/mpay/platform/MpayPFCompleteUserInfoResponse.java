/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * xiyang@yiji.com 2015-09-10 14:22 创建
 *
 */
package com.yiji.openapi.message.mpay.platform;

import com.yiji.openapi.sdk.common.annotation.OpenApiMessage;
import com.yiji.openapi.sdk.common.enums.ApiMessageType;
import com.yiji.openapi.sdk.common.message.ApiResponse;

/**
 * 完善个人信息接口(支付台)
 * @author dengwei@yiji.com
 */
@OpenApiMessage(service = "mpayPFCompleteUserInfo", type = ApiMessageType.Response)
public class MpayPFCompleteUserInfoResponse extends ApiResponse {
}
