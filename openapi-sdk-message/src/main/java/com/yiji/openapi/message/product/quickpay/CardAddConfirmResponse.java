package com.yiji.openapi.message.product.quickpay;

import com.yiji.openapi.sdk.common.annotation.OpenApiField;
import com.yiji.openapi.sdk.common.annotation.OpenApiMessage;
import com.yiji.openapi.sdk.common.enums.ApiMessageType;
import com.yiji.openapi.sdk.common.message.ApiResponse;

@OpenApiMessage(service = "card.add.confirm", type = ApiMessageType.Response)
public class CardAddConfirmResponse extends ApiResponse {
    @OpenApiField(
            desc = "签约号",
            demo = "235832186132"
    )
    private String signNo;
    @OpenApiField(
            desc = "交易状态",
            demo = "PACT_APPLY"
    )
    private String status;
    @OpenApiField(
            desc = "签约账户所属机构标识"
    )
    private String signAcctIssrId;
    @OpenApiField(
            desc = "签约账户类型"
    )
    private String signAcctType;

    public CardAddConfirmResponse() {
    }

    public String getSignNo() {
        return this.signNo;
    }

    public void setSignNo(String signNo) {
        this.signNo = signNo;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSignAcctIssrId() {
        return this.signAcctIssrId;
    }

    public void setSignAcctIssrId(String signAcctIssrId) {
        this.signAcctIssrId = signAcctIssrId;
    }

    public String getSignAcctType() {
        return this.signAcctType;
    }

    public void setSignAcctType(String signAcctType) {
        this.signAcctType = signAcctType;
    }
}
