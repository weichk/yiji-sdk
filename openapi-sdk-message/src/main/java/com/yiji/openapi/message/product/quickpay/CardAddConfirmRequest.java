package com.yiji.openapi.message.product.quickpay;

import com.yiji.openapi.sdk.common.annotation.OpenApiField;
import com.yiji.openapi.sdk.common.annotation.OpenApiMessage;
import com.yiji.openapi.sdk.common.enums.ApiMessageType;
import com.yiji.openapi.sdk.common.message.ApiRequest;
import org.hibernate.validator.constraints.NotBlank;

@OpenApiMessage(service = "card.add.confirm", type = ApiMessageType.Request)
public class CardAddConfirmRequest extends ApiRequest {
    @NotBlank(
            message = "签约号不能为空"
    )
    @OpenApiField(
            desc = "签约号",
            demo = "52196812654"
    )
    private String signNo;
    @NotBlank(
            message = "动态验证码不能为空"
    )
    @OpenApiField(
            desc = "动态验证码",
            demo = "314354"
    )
    private String authMsg;

    public CardAddConfirmRequest() {
    }

    public String getSignNo() {
        return this.signNo;
    }

    public void setSignNo(String signNo) {
        this.signNo = signNo;
    }

    public String getAuthMsg() {
        return this.authMsg;
    }

    public void setAuthMsg(String authMsg) {
        this.authMsg = authMsg;
    }
}
