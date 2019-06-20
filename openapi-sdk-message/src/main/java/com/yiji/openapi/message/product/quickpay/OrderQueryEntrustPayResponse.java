package com.yiji.openapi.message.product.quickpay;

import com.yiji.openapi.sdk.common.annotation.OpenApiField;
import com.yiji.openapi.sdk.common.annotation.OpenApiMessage;
import com.yiji.openapi.sdk.common.enums.ApiMessageType;
import com.yiji.openapi.sdk.common.message.ApiResponse;
import com.yiji.openapi.sdk.common.utils.Money;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

@OpenApiMessage(
        service = "order.query.entrustpay",
        type = ApiMessageType.Response
)
public class OrderQueryEntrustPayResponse extends ApiResponse {
    @NotBlank
    @OpenApiField(
            desc = "原请求号",
            demo = "2016089983",
            constraint = "如果调用委托扣款接口超时了，没有获得tradeNo，可以用请求号查询订单"
    )
    private String origOrderNo;
    @OpenApiField(
            desc = "易极付订单号",
            demo = "201500010000005150a",
            constraint = "返回原数据"
    )
    private String tradeNo;
    @OpenApiField(
            desc = "订单详情",
            demo = "牛奶 2包",
            constraint = "订单的详细描述"
    )
    private String orderDesc;
    @OpenApiField(
            desc = "业务种类",
            demo = "100001",
            constraint = "附录"
    )
    private String bizTp;
    @OpenApiField(
            desc = "付款人易极付ID",
            demo = "20160809223120000000",
            constraint = "易极付给付款人分配的ID"
    )
    private String payerUserId;
    @OpenApiField(
            desc = "收款人易极付ID",
            demo = "20160809223120000000",
            constraint = "易极付给收款人（商户）分配的ID"
    )
    private String payeeUserId;
    @OpenApiField(
            desc = "交易金额",
            demo = "50.00",
            constraint = "商品的交易总额"
    )
    private Money tradeAmount;
    @OpenApiField(
            desc = "分账金额",
            demo = "50.00",
            constraint = "收款人向合作商支付的费用；合作商就是收款人时，必须为空"
    )
    private Money distributeAmount;
    @OpenApiField(
            desc = "优惠金额",
            demo = "50.00",
            constraint = "合作商向付款人补贴的费用；合作商就是收款人时，必须为空"
    )
    private Money discountAmount;
    @OpenApiField(
            desc = "付款金额",
            demo = "50.00",
            constraint = "付款人实际支付的金额"
    )
    private Money payerAmount;
    @OpenApiField(
            desc = "合作商应收金额",
            demo = "50.00",
            constraint = "易极付合作商应收到的金额；负数表示应付金额"
    )
    private Money partnerAmount;
    @OpenApiField(
            desc = "收款人应收金额",
            demo = "50.00",
            constraint = "收款人应收到的金额；负数表示应付金额"
    )
    private Money payeeAmount;
    @OpenApiField(
            desc = "坐扣手续费",
            demo = "50.00",
            constraint = "易极付在交易时坐扣的手续费金额"
    )
    private Money fee;
    @OpenApiField(
            desc = "创建时间",
            demo = "2015-05-06"
    )
    private Date createTime;
    @OpenApiField(
            desc = "完成时间",
            demo = "2015-05-06"
    )
    private Date finishTime;
    @OpenApiField(
            desc = "交易状态",
            demo = "FINISHED",
            constraint = "WAIT_PAY:等待付款;PAY_PROCESSING:付款处理中;FINISHED:成功;CLOSED:关闭"
    )
    private String tradeStatus;

    public OrderQueryEntrustPayResponse() {
    }

    public String getOrigOrderNo() {
        return this.origOrderNo;
    }

    public void setOrigOrderNo(String origOrderNo) {
        this.origOrderNo = origOrderNo;
    }

    public String getTradeNo() {
        return this.tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
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

    public String getPayerUserId() {
        return this.payerUserId;
    }

    public void setPayerUserId(String payerUserId) {
        this.payerUserId = payerUserId;
    }

    public String getPayeeUserId() {
        return this.payeeUserId;
    }

    public void setPayeeUserId(String payeeUserId) {
        this.payeeUserId = payeeUserId;
    }

    public Money getTradeAmount() {
        return this.tradeAmount;
    }

    public void setTradeAmount(Money tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public Money getDistributeAmount() {
        return this.distributeAmount;
    }

    public void setDistributeAmount(Money distributeAmount) {
        this.distributeAmount = distributeAmount;
    }

    public Money getDiscountAmount() {
        return this.discountAmount;
    }

    public void setDiscountAmount(Money discountAmount) {
        this.discountAmount = discountAmount;
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

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinishTime() {
        return this.finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getTradeStatus() {
        return this.tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }
}
