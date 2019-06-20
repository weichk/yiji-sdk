/**
 * create by zhike@yiji.com
 * date:2015年3月12日
 */
package com.yiji.openapi.sdk.marshall.impl;

import com.yiji.openapi.sdk.ApiSdkConstants;
import com.yiji.openapi.sdk.common.ApiConstants;
import com.yiji.openapi.sdk.common.message.ApiResponse;
import com.yiji.openapi.sdk.common.utils.json.JsonMarshallor;
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
public class ApiSdkResponseUnmarshal extends ApiSdkMarshalSupport implements ApiSdkUnmarshal<ApiResponse, String> {

    private static final Log logger = LogFactory.getLog(ApiSdkResponseUnmarshal.class);

    @Override
    public ApiResponse unmarshal(String message, String serviceKey) {
        try {
            logger.info("响应报文["+message+"]");
            Map<String, String> responseMap = JsonMarshallor.INSTANCE.parse(message, Map.class);
            // 验签
            String sign = responseMap.get(ApiConstants.SIGN);
            String signType = responseMap.get(ApiConstants.SIGN_TYPE);
            Signer signer = getSigner(SignTypeEnum.valueOf(signType));
            signer.verify(sign, ApiSdkConstants.SECRETKEY, responseMap);
            // 解析
            ApiResponse response = (ApiResponse) messageFactory.getResponse(serviceKey);
            ApiClientUtils.convertUnmarshall(responseMap, response);
            // 解密
            decrypt(response);
            return response;
        } catch (Exception e) {
            logger.error("解析报文失败",e);
            throw new ApiClientException("解析响应报文错误:" + e.getMessage());
        }
    }

    @Override
    public ApiResponse unmarshal(String message, String serviceKey, String secretKey) {
        try {
            logger.info("响应报文["+message+"]");
            Map<String, String> responseMap = JsonMarshallor.INSTANCE.parse(message, Map.class);
            // 验签
            String sign = responseMap.get(ApiConstants.SIGN);
            String signType = responseMap.get(ApiConstants.SIGN_TYPE);
            Signer signer = getSigner(SignTypeEnum.valueOf(signType));
            signer.verify(sign, secretKey, responseMap);
            // 解析
            ApiResponse response = (ApiResponse) messageFactory.getResponse(serviceKey);
            ApiClientUtils.convertUnmarshall(responseMap, response);
            // 解密
            decrypt(response);
            return response;
        } catch (Exception e) {
            logger.error("解析报文失败",e);
            throw new ApiClientException("解析响应报文错误:" + e.getMessage());
        }
    }
}
