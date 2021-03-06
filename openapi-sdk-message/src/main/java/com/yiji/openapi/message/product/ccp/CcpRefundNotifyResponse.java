package com.yiji.openapi.message.product.ccp;

import com.yiji.openapi.sdk.common.annotation.OpenApiMessage;
import com.yiji.openapi.sdk.common.enums.ApiMessageType;
import com.yiji.openapi.sdk.common.message.ApiResponse;

/**
 * @author hgeshu@yiji.com
 * @since 2016-03-25 11:25.
 */
@OpenApiMessage(service = "ccpRefundNotify", type = ApiMessageType.Response)
public class CcpRefundNotifyResponse extends ApiResponse {
}
