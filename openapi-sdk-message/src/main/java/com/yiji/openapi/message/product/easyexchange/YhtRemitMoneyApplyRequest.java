/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 
 * mdaheng@yiji.com 2016-06-30 17:27 创建
 *
 */
package com.yiji.openapi.message.product.easyexchange;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.yiji.openapi.sdk.common.annotation.OpenApiField;
import com.yiji.openapi.sdk.common.annotation.OpenApiMessage;
import com.yiji.openapi.sdk.common.enums.ApiMessageType;
import com.yiji.openapi.sdk.common.message.ApiRequest;
import com.yiji.openapi.sdk.common.utils.Money;

/**
 * @author xzhengyu
 * @email mdaheng@yiji.com
 * @since 2016-06-30
 */
@OpenApiMessage(service = "yhtRemitMoneyApply", type = ApiMessageType.Request)
public class YhtRemitMoneyApplyRequest extends ApiRequest {

    @NotBlank
    @OpenApiField(desc = "付款号" , demo = "2015013011112141" , constraint = "")
    private String crossPayNo;

    @OpenApiField(desc = "付款人id" , demo = "2015013011112141214" , constraint = "")
    private String payerUserId;

    @NotNull
    @OpenApiField(desc = "传入的汇款金额" , demo = "258888" , constraint = "")
    private Money amount;

    @NotNull
    @OpenApiField(desc = "传入的汇款币种" , demo = "CNY" , constraint = "")
    private String currency;

    @NotNull
    @OpenApiField(desc = "收款币种" , demo = "CNY" , constraint = "")
    private String toCurrency;

    @NotBlank
    @OpenApiField(desc = "收款人名字" , demo = "熊本熊" , constraint = "")
    private String toName;

    @NotBlank
    @OpenApiField(desc = "收款人地址" , demo = "重庆市江北嘴金融城" , constraint = "")
    private String toNameAddr;

    @NotBlank
    @OpenApiField(desc = "收款人常驻国家(地区)代码" , demo = "" , constraint = "")
    private String couCde;

    @NotBlank
    @OpenApiField(desc = "收款银行名称" , demo = "中国银行" , constraint = "")
    private String toBankName;

    @NotBlank
    @OpenApiField(desc = "收款银行地址" , demo = "重庆市江北嘴金融城" , constraint = "")
    private String toBankAddr;

    @NotBlank
    @OpenApiField(desc = "交易用途编码" , demo = "105161616112515" , constraint = "")
    private String tradeUseCode;

    @NotBlank
    @OpenApiField(desc = "银行国际代码" , demo = "105161616112515" , constraint = "")
    private String toBankCode;

    @NotBlank
    @OpenApiField(desc = "收款人卡号" , demo = "6221414101510540" , constraint = "")
    private String bankAccountNo;

    @OpenApiField(desc = "代理银行名称" , demo = "重庆银行" , constraint = "")
    private String agentBankName;

    @OpenApiField(desc = "代理银行地址" , demo = "重庆市江北嘴金融城" , constraint = "")
    private String agentAddress;

    public String getCrossPayNo() {
        return crossPayNo;
    }

    public void setCrossPayNo(String crossPayNo) {
        this.crossPayNo = crossPayNo;
    }

    public String getPayerUserId() {
        return payerUserId;
    }

    public void setPayerUserId(String payerUserId) {
        this.payerUserId = payerUserId;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToNameAddr() {
        return toNameAddr;
    }

    public void setToNameAddr(String toNameAddr) {
        this.toNameAddr = toNameAddr;
    }

    public String getCouCde() {
        return couCde;
    }

    public void setCouCde(String couCde) {
        this.couCde = couCde;
    }

    public String getToBankName() {
        return toBankName;
    }

    public void setToBankName(String toBankName) {
        this.toBankName = toBankName;
    }

    public String getToBankAddr() {
        return toBankAddr;
    }

    public void setToBankAddr(String toBankAddr) {
        this.toBankAddr = toBankAddr;
    }

    public String getTradeUseCode() {
        return tradeUseCode;
    }

    public void setTradeUseCode(String tradeUseCode) {
        this.tradeUseCode = tradeUseCode;
    }

    public String getToBankCode() {
        return toBankCode;
    }

    public void setToBankCode(String toBankCode) {
        this.toBankCode = toBankCode;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getAgentBankName() {
        return agentBankName;
    }

    public void setAgentBankName(String agentBankName) {
        this.agentBankName = agentBankName;
    }

    public String getAgentAddress() {
        return agentAddress;
    }

    public void setAgentAddress(String agentAddress) {
        this.agentAddress = agentAddress;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }
}
