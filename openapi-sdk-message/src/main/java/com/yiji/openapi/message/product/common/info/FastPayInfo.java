package com.yiji.openapi.message.product.common.info;

import com.yiji.openapi.sdk.common.annotation.OpenApiField;
import com.yiji.openapi.sdk.common.utils.Money;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by hgeshu@yiji.com on 2016/8/22.
 */
public class FastPayInfo implements Serializable {
    /**
     * 单笔交易订单号
     */
    @NotNull
    @Length(min = 1, max = 64)
    @OpenApiField(desc = "单笔交易外部订单号",demo = "234531545")
    private String merchOrderNo;

    /**
     * 交易名称
     */
    @Length(max = 64)
    @OpenApiField(desc = "交易名称",demo = "及时到账")
    private String tradeName;

    /**
     * 卖家会员号
     */
    @Length(max = 20)
    @OpenApiField(desc = "卖家的易极付会员id",demo = "20165148968752486415",constraint = "pactNo与sellerUserId不能同时传入，也不能同时为空")
    private String sellerUserId;

    /**
     * 绑卡流水号
     */
    @Length(max = 32)
    @OpenApiField(desc = "绑卡编号",demo = "6891654",constraint = "会员在易极付绑定银行卡完成后易极付返回的绑卡编号，pactNo与sellerUserId不能同时传入，也不能同时为空")
    private String pactNo;

    /**
     * 交易金额
     */
    @NotNull
    @OpenApiField(desc = "交易金额",demo = "84.51")
    private Money tradeAmount;

    /**
     * 交易币种
     */
    @NotNull
    @OpenApiField(desc = "交易币种",demo = "CNY",constraint = "")
    private String currency;

    /**
     * 商品类型码
     */
    @Size(max = 64)
    @OpenApiField(desc = "商品类型码",demo = "5146854")
    private String goodsTypeCode;

    /**
     * 商品类型名称
     */
    @Size(max = 64)
    @OpenApiField(desc = "商品类型名称",demo = "计算机")
    private String goodsTypeName;

    /**
     * 商品名称
     */
    @Size(max = 64)
    @NotBlank
    @OpenApiField(desc = "商品名称",demo = "笔记本电脑")
    private String goodsName;

    /**
     * 备注
     */
    @Length(max = 128)
    @OpenApiField(desc = "备注",demo = "备注")
    private String memo;

    /**
     * 分润信息
     */
    @OpenApiField(desc = "分润信息",constraint = "分润最多支持10笔,单笔分润格式:userId1~amount1~memo1^userId2~amount2~memo2",demo = "2012658947952258175214~88.66~分润^45631541323241~65.21~fenrun")
    private String shareProfits;

    public String getMerchOrderNo() {
        return merchOrderNo;
    }

    public void setMerchOrderNo(String merchOrderNo) {
        this.merchOrderNo = merchOrderNo;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getSellerUserId() {
        return sellerUserId;
    }

    public void setSellerUserId(String sellerUserId) {
        this.sellerUserId = sellerUserId;
    }

    public String getPactNo() {
        return pactNo;
    }

    public void setPactNo(String pactNo) {
        this.pactNo = pactNo;
    }

    public Money getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(Money tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getGoodsTypeCode() {
        return goodsTypeCode;
    }

    public void setGoodsTypeCode(String goodsTypeCode) {
        this.goodsTypeCode = goodsTypeCode;
    }

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getShareProfits() {
        return shareProfits;
    }

    public void setShareProfits(String shareProfits) {
        this.shareProfits = shareProfits;
    }
}
