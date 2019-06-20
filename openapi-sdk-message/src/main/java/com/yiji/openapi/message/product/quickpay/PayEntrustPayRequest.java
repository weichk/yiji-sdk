package com.yiji.openapi.message.product.quickpay;

import com.yiji.openapi.sdk.common.annotation.OpenApiField;
import com.yiji.openapi.sdk.common.annotation.OpenApiMessage;
import com.yiji.openapi.sdk.common.enums.ApiMessageType;
import com.yiji.openapi.sdk.common.message.ApiRequest;
import com.yiji.openapi.sdk.common.utils.Money;
import org.hibernate.validator.constraints.NotBlank;


@OpenApiMessage(
        service = "pay.entrustpay",
        type = ApiMessageType.Request
)
public class PayEntrustPayRequest extends ApiRequest {
    @NotBlank
    @OpenApiField(
            desc = "订单详情订单详情",
            demo = "牛奶 2包"
    )
    private String orderDesc;
    @NotBlank
    @OpenApiField(
            desc = "业务种类",
            demo = "100001",
            constraint = "附录"
    )
    private String bizTp;
    @OpenApiField(
            desc = "交易金额",
            demo = "50.00",
            constraint = "商品的交易总额"
    )
    private Money tradeAmount;
    @NotBlank
    @OpenApiField(
            desc = "收款人易极付ID",
            demo = "20160809223120000000",
            constraint = "易极付给收款人（商户）分配的ID"
    )
    private String payeeUserId;
    @NotBlank
    @OpenApiField(
            desc = "支付标记",
            constraint = "申请添加银行卡时，由易极付返回"
    )
    private String signNo;
    @OpenApiField(
            desc = "分账金额",
            demo = "10.00",
            constraint = "收款人向合作商支付的费用；合作商就是收款人时，必须为空"
    )
    private Money distributeAmount;

    public PayEntrustPayRequest() {
    }

    public String getOrderDesc() {
        return this.orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public String getBizTp() {
        return this.bizTp;
    }

    public void setBizTp(String bizTp) {
        this.bizTp = bizTp;
    }

    public Money getTradeAmount() {
        return this.tradeAmount;
    }

    public void setTradeAmount(Money tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public String getPayeeUserId() {
        return this.payeeUserId;
    }

    public void setPayeeUserId(String payeeUserId) {
        this.payeeUserId = payeeUserId;
    }

    public String getSignNo() {
        return this.signNo;
    }

    public void setSignNo(String signNo) {
        this.signNo = signNo;
    }

    public Money getDistributeAmount() {
        return this.distributeAmount;
    }

    public void setDistributeAmount(Money distributeAmount) {
        this.distributeAmount = distributeAmount;
    }
}
