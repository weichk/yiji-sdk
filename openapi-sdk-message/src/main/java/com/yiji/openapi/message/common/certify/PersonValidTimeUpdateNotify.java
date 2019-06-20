/*
 * www.yiji.com Inc.
 * Copyright (c) 2015 All Rights Reserved.
 */

/*
 * 修订记录：
 * zhike 2015年9月10日 上午11:20:37 创建
 */
package com.yiji.openapi.message.common.certify;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.yiji.openapi.message.common.certify.info.StatusEnum;
import com.yiji.openapi.sdk.common.annotation.OpenApiField;
import com.yiji.openapi.sdk.common.annotation.OpenApiMessage;
import com.yiji.openapi.sdk.common.enums.ApiMessageType;
import com.yiji.openapi.sdk.common.message.ApiNotify;

/**
 *
 *
 * @author zhike@yiji.com
 * @data   2015年9月10日
 */
@Deprecated
@OpenApiMessage(service = "personValidTimeUpdate", type = ApiMessageType.Notify)
public class PersonValidTimeUpdateNotify extends ApiNotify {
	@NotBlank
	@Length(min = 20, max = 20)
	@OpenApiField(desc = "用户会员ID",constraint="用户会员ID",demo="20150619010000072320")
	private String userId;
	

	@OpenApiField(desc = "认证状态",constraint="认证状态",demo="NOA")
	private StatusEnum certifyStatus;
	
	public String getUserId() {
		return this.userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public StatusEnum getCertifyStatus() {
		return this.certifyStatus;
	}


	public void setCertifyStatus(StatusEnum certifyStatus) {
		this.certifyStatus = certifyStatus;
	}
}

    