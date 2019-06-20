/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 
 * mdaheng@yiji.com 2016-04-22 14:59 创建
 *
 */
package com.yiji.openapi.message.common.pact;

import org.hibernate.validator.constraints.NotBlank;

import com.yiji.openapi.sdk.common.annotation.OpenApiField;
import com.yiji.openapi.sdk.common.annotation.OpenApiMessage;
import com.yiji.openapi.sdk.common.enums.ApiMessageType;
import com.yiji.openapi.sdk.common.message.ApiRequest;

/**
 * @author xzhengyu
 * @email mdaheng@yiji.com
 * @since 2016-04-22
 */
@OpenApiMessage(service = "asynVerifyCardThree", type = ApiMessageType.Request)
public class AsynVerifyCardThreeRequest extends ApiRequest {

    /** 卡号 */
    @OpenApiField(desc = "银行卡号",demo = "52310115654751",constraint = "卡号不能为空")
    @NotBlank
    private String cardNo;

    /** 证件号 */
    @OpenApiField(desc = "证件号" , demo = "512121155502065565" , constraint = "证件号不能为空")
    @NotBlank
    private String certNo;

    /** 持卡人姓名 */
    @OpenApiField(desc = "持卡人姓名" , demo = "大猫" , constraint = "持卡人姓名不能为空")
    @NotBlank
    private String cardName;

    /** 证件类型 */
    @OpenApiField(desc = "证件类型" , demo = "Identity_Card")
    private String certType;

    /** openApi调用标识，判断是否需要异步通知，默认是false */
    @OpenApiField(desc = "openApi调用标识" , demo = "true",constraint ="判断是否需要异步通知，默认是false,如果选择false,可以选择:查询验证" )
    private boolean needNotify;




    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public boolean isNeedNotify() {
        return needNotify;
    }

    public void setNeedNotify(boolean needNotify) {
        this.needNotify = needNotify;
    }
}
