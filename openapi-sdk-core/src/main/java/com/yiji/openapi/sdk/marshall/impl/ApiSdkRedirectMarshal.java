/**
 * create by zhike@yiji.com
 * date:2015年3月12日
 */
package com.yiji.openapi.sdk.marshall.impl;

import com.yiji.openapi.sdk.ApiSdkConstants;
import com.yiji.openapi.sdk.common.annotation.OpenApiMessage;
import com.yiji.openapi.sdk.common.message.ApiRequest;
import com.yiji.openapi.sdk.common.utils.Encodes;
import com.yiji.openapi.sdk.common.utils.Ids;
import com.yiji.openapi.sdk.common.utils.Strings;
import com.yiji.openapi.sdk.exception.ApiClientException;
import com.yiji.openapi.sdk.marshall.ApiSdkMarshal;
import com.yiji.openapi.sdk.marshall.ApiSdkMarshalSupport;
import com.yiji.openapi.sdk.sign.SignTypeEnum;
import com.yiji.openapi.sdk.sign.Signer;
import com.yiji.openapi.sdk.support.ApiClientUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * @author zhike@yiji.com
 */
public class ApiSdkRedirectMarshal extends ApiSdkMarshalSupport implements ApiSdkMarshal<String, ApiRequest> {
	private static final Log logger = LogFactory.getLog(ApiSdkRedirectMarshal.class);
	
	@Override
	public String marshal(ApiRequest request) {
		try {
			supplementDefaultAndConfig(request);
			encrypt(request);
			Map<String, String> requestData = ApiClientUtils.convertMarshall(request, true);
			Signer signer = getSigner(SignTypeEnum.valueOf(request.getSignType()));
			String signature = Encodes.urlEncode(signer.sign(requestData, ApiSdkConstants.SECRETKEY));
			request.setSign(signature);
			Map<String, String> message = ApiClientUtils.convertMarshall(request, false);
			StringBuffer stringBuffer = new StringBuffer();
			for (Map.Entry<String, String> entry:message.entrySet()) {
				stringBuffer.append(entry.getKey() + "=" + Encodes.urlEncode(entry.getValue()) + "&");
			}
			return stringBuffer.substring(0, stringBuffer.length()-1);
		} catch (Exception e) {
			logger.warn("请求报文组装失败", e);
			throw new ApiClientException("组装请求报文错误:" + e.getMessage());
		}
		
	}

	@Override
	public String marshal(ApiRequest request, String secretKey) {
		try {
			supplementDefaultAndConfig(request);
			encrypt(request);
			Map<String, String> requestData = ApiClientUtils.convertMarshall(request, true);
			Signer signer = getSigner(SignTypeEnum.valueOf(request.getSignType()));
			String signature = Encodes.urlEncode(signer.sign(requestData, secretKey));
			request.setSign(signature);
			Map<String, String> message = ApiClientUtils.convertMarshall(request, false);
			StringBuffer stringBuffer = new StringBuffer();
			for (Map.Entry<String, String> entry:message.entrySet()) {
				stringBuffer.append(entry.getKey() + "=" + Encodes.urlEncode(entry.getValue()) + "&");
			}
			return stringBuffer.substring(0, stringBuffer.length()-1);
		} catch (Exception e) {
			logger.warn("请求报文组装失败", e);
			throw new ApiClientException("组装请求报文错误:" + e.getMessage());
		}
	}

	protected void supplementDefaultAndConfig(ApiRequest request) {
		if (Strings.isBlank(request.getOrderNo())) {
			request.setOrderNo(Ids.oid());
		}
		
		if (Strings.isBlank(request.getPartnerId())) {
			request.setPartnerId(ApiSdkConstants.PARTNERID);
		}
		
		if (Strings.isBlank(request.getService())) {
			request.setService(getServiceNameByAnnotation(request));
		}
		
		// 如果存在异步报文,则请求报文自动设置notifyUrl
		if (Strings.isBlank(request.getNotifyUrl()) && messageFactory.getNotify(request.getService()+"_"+request.getVersion()) != null) {
			request.setNotifyUrl(ApiSdkConstants.NOTIFY_URL);
		}
		
	}

	
	protected String getServiceNameByAnnotation(ApiRequest request) {
		OpenApiMessage openApiMessage = request.getClass().getAnnotation(OpenApiMessage.class);
		return (openApiMessage != null && Strings.isNotBlank(openApiMessage.service())) ? openApiMessage.service()
			: null;
		
	}
}
