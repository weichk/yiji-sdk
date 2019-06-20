package com.yiji.openapi.message.product.quickpay;

import com.yiji.openapi.sdk.common.annotation.OpenApiField;
import com.yiji.openapi.sdk.common.annotation.OpenApiMessage;
import com.yiji.openapi.sdk.common.enums.ApiMessageType;
import com.yiji.openapi.sdk.common.message.ApiRequest;
import org.hibernate.validator.constraints.NotBlank;

@OpenApiMessage(service = "card.add.apply.unionpay", type = ApiMessageType.Request)
public class CardAddApplyUnionpayRequest extends ApiRequest {
    @NotBlank(
            message = "签约人姓名不能为空"
    )
    @OpenApiField(
            desc = "签约人姓名",
            demo = "张三"
    )
    private String signName;
    @NotBlank(
            message = "签约人身份证号不能为空"
    )
    @OpenApiField(
            desc = "签约人身份证号",
            demo = "2541596858485859623"
    )
    private String signID;
    @NotBlank(
            message = "签约账号不能为空"
    )
    @OpenApiField(
            desc = "签约账号",
            demo = "625845859658475854"
    )
    private String signAccId;
    @NotBlank(
            message = "银行预留手机号不能为空"
    )
    @OpenApiField(
            desc = "银行预留手机号",
            demo = "10086100101"
    )
    private String signMobile;

    public CardAddApplyUnionpayRequest() {
    }

    public String getSignName() {
        return this.signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getSignID() {
        return this.signID;
    }

    public void setSignID(String signID) {
        this.signID = signID;
    }

    public String getSignAccId() {
        return this.signAccId;
    }

    public void setSignAccId(String signAccId) {
        this.signAccId = signAccId;
    }

    public String getSignMobile() {
        return this.signMobile;
    }

    public void setSignMobile(String signMobile) {
        this.signMobile = signMobile;
    }
}
