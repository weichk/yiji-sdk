package com.yiji.openapi.message.product.quickpay;

import com.yiji.openapi.sdk.common.annotation.OpenApiField;
import com.yiji.openapi.sdk.common.annotation.OpenApiMessage;
import com.yiji.openapi.sdk.common.enums.ApiMessageType;
import com.yiji.openapi.sdk.common.message.ApiRequest;
import org.hibernate.validator.constraints.NotBlank;

@OpenApiMessage(
        service = "order.query.entrustpay",
        type = ApiMessageType.Request
)
public class OrderQueryEntrustPayRequest extends ApiRequest {
    @NotBlank
    @OpenApiField(
            desc = "原请求号",
            demo = "2016089983",
            constraint = "如果调用委托扣款接口超时了，没有获得tradeNo，可以用请求号查询订单"
    )
    private String origOrderNo;

    public OrderQueryEntrustPayRequest() {
    }

    public String getOrigOrderNo() {
        return this.origOrderNo;
    }

    public void setOrigOrderNo(String origOrderNo) {
        this.origOrderNo = origOrderNo;
    }
}
