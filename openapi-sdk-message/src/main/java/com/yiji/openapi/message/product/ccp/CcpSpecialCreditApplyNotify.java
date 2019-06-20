/*
 * www.yiji.com Inc.
 * Copyright (c) 2015 All Rights Reserved.
 */

/*
 * 修订记录：
 * zhike 2016年3月31日 上午10:35:49 创建
 */
package com.yiji.openapi.message.product.ccp;

import org.hibernate.validator.constraints.NotBlank;

import com.yiji.openapi.sdk.common.annotation.OpenApiField;
import com.yiji.openapi.sdk.common.annotation.OpenApiMessage;
import com.yiji.openapi.sdk.common.enums.ApiMessageType;
import com.yiji.openapi.sdk.common.message.ApiNotify;

/**
 *
 *
 * @author zhike@yiji.com
 * @data 2016年3月31日
 */
@OpenApiMessage(service = "ccpSpecialCreditApply", type = ApiMessageType.Notify)
public class CcpSpecialCreditApplyNotify extends ApiNotify {
	@NotBlank
	@OpenApiField(desc = "融资订单号", constraint = "融资订单号", demo = "20160323022055094794")
	private String bizNo;
	
	@NotBlank
	@OpenApiField(desc = "审核结果", constraint = "{\"data\":[\"pass:通过\",\"reject:拒绝\",\"back:打回\"],\"name\":\"审核结果\"}",
			demo = "pass")
	private String auditResult;
	
	@OpenApiField(desc = "备注", constraint = "备注", demo = "照片不清晰")
	private String remark;
	
	@OpenApiField(desc = "扩展字段", constraint = "扩展字段")
	private String reserve;
	
	public String getBizNo() {
		return this.bizNo;
	}
	
	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}
	
	public String getAuditResult() {
		return this.auditResult;
	}
	
	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}
	
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getReserve() {
		return this.reserve;
	}
	
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	
}
