package com.yiji.openapi.message.product.quickpay;

import com.yiji.openapi.sdk.common.annotation.OpenApiField;
import com.yiji.openapi.sdk.common.annotation.OpenApiMessage;
import com.yiji.openapi.sdk.common.enums.ApiMessageType;
import com.yiji.openapi.sdk.common.message.ApiResponse;
import com.yiji.openapi.sdk.common.utils.Money;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;


@OpenApiMessage(
        service = "pay.entrustpay",
        type = ApiMessageType.Response
)
public class PayEntrustPayResponse extends ApiResponse {

    @NotBlank
    @OpenApiField(
            desc = "订单号",
            constraint = "易极付的订单号"
    )
    private String tradeNo;
    @OpenApiField(
            desc = "交易金额",
            demo = "50.00",
            constraint = "订单的金额"
    )
    private Money tradeAmount;
    @OpenApiField(
            desc = "付款金额",
            demo = "50.00",
            constraint = "付款人实际支付的金额"
    )
    private Money payerAmount;
    @OpenApiField(
            desc = "合作商应收金额",
            demo = "50.00",
            constraint = "合作商应收到的金额；负数表示应付金额"
    )
    private Money partnerAmount;
    @OpenApiField(
            desc = "收款人应收金额",
            demo = "50.00",
            constraint = "收款人应收到的金额；负数表示应付金额"
    )
    private Money payeeAmount;
    @OpenApiField(
            desc = "手续费",
            demo = "50.00",
            constraint = "易极付坐扣的结算手续费金额"
    )
    private Money fee;
    @OpenApiField(
            desc = "交易完成时间"
    )
    private Date tradeFinishTime;
    @OpenApiField(
            desc = "交易状态",
            demo = "PAY_PROCESSING",
            constraint = "PAY_PROCESSING:付款处理中;FINISHED:成功;CLOSED:失败"
    )
    private String tradeStatus;

    public PayEntrustPayResponse() {
    }

    public String getTradeNo() {
        return this.tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Money getTradeAmount() {
        return this.tradeAmount;
    }

    public void setTradeAmount(Money tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public Money getPayerAmount() {
        return this.payerAmount;
    }

    public void setPayerAmount(Money payerAmount) {
        this.payerAmount = payerAmount;
    }

    public Money getPartnerAmount() {
        return this.partnerAmount;
    }

    public void setPartnerAmount(Money partnerAmount) {
        this.partnerAmount = partnerAmount;
    }

    public Money getPayeeAmount() {
        return this.payeeAmount;
    }

    public void setPayeeAmount(Money payeeAmount) {
        this.payeeAmount = payeeAmount;
    }

    public Money getFee() {
        return this.fee;
    }

    public void setFee(Money fee) {
        this.fee = fee;
    }

    public Date getTradeFinishTime() {
        return this.tradeFinishTime;
    }

    public void setTradeFinishTime(Date tradeFinishTime) {
        this.tradeFinishTime = tradeFinishTime;
    }

    public String getTradeStatus() {
        return this.tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }
}
