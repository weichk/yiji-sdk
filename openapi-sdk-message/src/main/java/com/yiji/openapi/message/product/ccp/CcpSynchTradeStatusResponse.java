/*
 * www.yiji.com Inc.
 * Copyright (c) 2015 All Rights Reserved.
 */

/*
 * 修订记录：
 * zhike 2016年3月23日 下午3:36:51 创建
 */
package com.yiji.openapi.message.product.ccp;

import javax.validation.constraints.NotNull;

import com.yiji.openapi.sdk.common.annotation.OpenApiField;
import com.yiji.openapi.sdk.common.annotation.OpenApiMessage;
import com.yiji.openapi.sdk.common.enums.ApiMessageType;
import com.yiji.openapi.sdk.common.message.ApiResponse;

/**
 *
 *
 * @author zhike@yiji.com
 * @data 2016年3月23日
 */
@OpenApiMessage(service = "ccpSynchTradeStatus", type = ApiMessageType.Response)
public class CcpSynchTradeStatusResponse extends ApiResponse {
	
	@NotNull
	@OpenApiField(desc = "结果状态", constraint = "结果状态", demo = "success")
	private String status;
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
