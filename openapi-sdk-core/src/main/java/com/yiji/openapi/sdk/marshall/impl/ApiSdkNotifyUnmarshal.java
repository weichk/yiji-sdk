/**
 * create by zhike@yiji.com
 * date:2015年3月12日
 */
package com.yiji.openapi.sdk.marshall.impl;

import com.yiji.openapi.sdk.ApiSdkConstants;
import com.yiji.openapi.sdk.common.ApiConstants;
import com.yiji.openapi.sdk.common.message.ApiNotify;
import com.yiji.openapi.sdk.exception.ApiClientException;
import com.yiji.openapi.sdk.marshall.ApiSdkMarshalSupport;
import com.yiji.openapi.sdk.marshall.ApiSdkUnmarshal;
import com.yiji.openapi.sdk.sign.SignTypeEnum;
import com.yiji.openapi.sdk.sign.Signer;
import com.yiji.openapi.sdk.support.ApiClientUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * @author zhike@yiji.com
 */
public class ApiSdkNotifyUnmarshal extends ApiSdkMarshalSupport implements ApiSdkUnmarshal<ApiNotify, Map<String, String>> {

    private static final Log logger = LogFactory.getLog(ApiSdkNotifyUnmarshal.class);

    @Override
    public ApiNotify unmarshal(Map<String, String> notifyData, String serviceKey) {
        try {
            logger.debug("通知报文:"+notifyData);
            // 验签
            String sign = notifyData.get(ApiConstants.SIGN);
            String signType = notifyData.get(ApiConstants.SIGN_TYPE);
            Signer signer = getSigner(SignTypeEnum.valueOf(signType));
            signer.verify(sign, ApiSdkConstants.SECRETKEY, notifyData);
            // 解析
            ApiNotify notify = (ApiNotify) messageFactory.getNotify(serviceKey);
            ApiClientUtils.convertUnmarshall(notifyData, notify);
            // 解密
            decrypt(notify);
            return notify;
        } catch (Exception e) {
            logger.error("解析报文失败", e);
            throw new ApiClientException("解析响应报文错误:" + e.getMessage());
        }
    }

    @Override
    public ApiNotify unmarshal(Map<String, String> notifyData, String serviceKey, String secretKey) {
        try {
            logger.debug("通知报文:"+notifyData);
            // 验签
            String sign = notifyData.get(ApiConstants.SIGN);
            String signType = notifyData.get(ApiConstants.SIGN_TYPE);
            Signer signer = getSigner(SignTypeEnum.valueOf(signType));
            signer.verify(sign, secretKey, notifyData);
            // 解析
            ApiNotify notify = (ApiNotify) messageFactory.getNotify(serviceKey);
            ApiClientUtils.convertUnmarshall(notifyData, notify);
            // 解密
            decrypt(notify);
            return notify;
        } catch (Exception e) {
            logger.error("解析报文失败", e);
            throw new ApiClientException("解析响应报文错误:" + e.getMessage());
        }
    }
}
