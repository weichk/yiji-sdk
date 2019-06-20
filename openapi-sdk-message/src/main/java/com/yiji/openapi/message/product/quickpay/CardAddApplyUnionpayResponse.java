package com.yiji.openapi.message.product.quickpay;

import com.yiji.openapi.sdk.common.annotation.OpenApiField;
import com.yiji.openapi.sdk.common.annotation.OpenApiMessage;
import com.yiji.openapi.sdk.common.enums.ApiMessageType;
import com.yiji.openapi.sdk.common.message.ApiResponse;

@OpenApiMessage(service = "card.add.apply.unionpay", type = ApiMessageType.Response)
public class CardAddApplyUnionpayResponse extends ApiResponse {

    @OpenApiField(
            desc = "签约号",
            demo = "235832186132"
    )
    private String signNo;
    @OpenApiField(
            desc = "签约状态",
            demo = "PACT_APPLY"
    )
    private String tradeStatus;
    @OpenApiField(
            desc = "动态验证码序号",
            demo = "60"
    )
    private String smsKey;

    public CardAddApplyUnionpayResponse() {
    }

    public String getSignNo() {
        return this.signNo;
    }

    public void setSignNo(String signNo) {
        this.signNo = signNo;
    }

    public String getTradeStatus() {
        return this.tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getSmsKey() {
        return this.smsKey;
    }

    public void setSmsKey(String smsKey) {
        this.smsKey = smsKey;
    }
}
