package com.yiji.openapi.message.product.yzz;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.yiji.openapi.sdk.common.annotation.OpenApiField;
import com.yiji.openapi.sdk.common.annotation.OpenApiMessage;
import com.yiji.openapi.sdk.common.enums.ApiMessageType;
import com.yiji.openapi.sdk.common.message.ApiRequest;

@OpenApiMessage(service = "yzzTenderPaymentQuery", type = ApiMessageType.Request)
public class YzzTenderPaymentQueryRequest extends ApiRequest {
	
	@OpenApiField(desc = "交易号")
	private String paymentTradeNo;
	
	@OpenApiField(desc = "外部订单号")
	private String outOrderNo;
	
	@NotEmpty
	@Size(min = 20, max = 20)
	@OpenApiField(desc = "借款项目主交易号")
	private String tradeNo;
	
	@NotEmpty
	@Size(min = 20, max = 20)
	@OpenApiField(desc = "还款用户Id")
	private String userId;
	
	public String getPaymentTradeNo() {
		return paymentTradeNo;
	}
	
	public void setPaymentTradeNo(String paymentTradeNo) {
		this.paymentTradeNo = paymentTradeNo;
	}
	
	public String getTradeNo() {
		return tradeNo;
	}
	
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getOutOrderNo() {
		return outOrderNo;
	}
	
	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}
}
