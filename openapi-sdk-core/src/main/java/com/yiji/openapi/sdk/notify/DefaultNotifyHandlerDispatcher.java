/*
 * acooly.cn Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * create by zhike@yiji.com 
 * date:2016年4月1日
 *
 */
package com.yiji.openapi.sdk.notify;

import com.yiji.openapi.sdk.common.ApiConstants;

import java.util.Map;

/**
 * @author zhike@yiji.com
 */
public class DefaultNotifyHandlerDispatcher extends AbstractSpringNotifyHandlerDispatcher {

    @Override
    protected String getServiceKey(String notifyUrl, Map<String, String> notifyData) {
        return notifyData.get(ApiConstants.SERVICE)+"_"+notifyData.get(ApiConstants.VERSION);
    }

}
