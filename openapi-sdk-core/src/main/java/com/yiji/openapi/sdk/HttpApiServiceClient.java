/*
 * www.yiji.com Inc.
 * Copyright (c) 2016 All Rights Reserved
 */

/*
 * 修订记录:
 * kuli@yiji.com 2016-06-12 17:21 创建
 */
package com.yiji.openapi.sdk;

import com.yiji.openapi.sdk.common.message.ApiNotify;
import com.yiji.openapi.sdk.common.message.ApiRequest;
import com.yiji.openapi.sdk.common.message.ApiResponse;
import com.yiji.openapi.sdk.marshall.ApiSdkMarshal;
import com.yiji.openapi.sdk.marshall.ApiSdkUnmarshal;
import com.yiji.openapi.sdk.marshall.impl.ApiSdkSignMarshal;
import com.yiji.openapi.sdk.transport.HttpTransport;
import com.yiji.openapi.sdk.transport.Transport;

import java.util.Map;

/**
 * @author acooly
 */
public class HttpApiServiceClient extends AbstractApiServiceClient<ApiRequest, ApiResponse, ApiNotify, ApiNotify> {
	
	private Transport transport = new HttpTransport();
	
	private ApiSdkMarshal<Map<String, String>, ApiRequest> requestMarshal;
	
	private ApiSdkMarshal<String, ApiRequest> redirectMarshal;
	
	private ApiSdkUnmarshal<ApiResponse, String> responseUnmarshal;
	
	private ApiSdkUnmarshal<ApiNotify, Map<String, String>> notifyUnmarshal;
	
	private ApiSdkSignMarshal sdkSignMarshal;
	
	@Override
	protected ApiSdkMarshal<Map<String, String>, ApiRequest> getRequestMarshal() {
		return requestMarshal;
	}
	
	@Override
	protected ApiSdkUnmarshal<ApiResponse, String> getResponseUnmarshal() {
		return responseUnmarshal;
	}
	
	@Override
	protected ApiSdkMarshal<String, ApiRequest> getRedirectMarshal() {
		return this.redirectMarshal;
	}
	
	@Override
	protected ApiSdkUnmarshal<ApiNotify, Map<String, String>> getNoticeUnmarshal() {
		return this.notifyUnmarshal;
	}
	
	@Override
	protected ApiSdkUnmarshal<ApiNotify, Map<String, String>> getReturnUnmarshal() {
		return this.notifyUnmarshal;
	}
	
	@Override
	protected Transport getTransport() {
		return transport;
	}
	
	@Override
	public ApiSdkSignMarshal getSdkSignMarshal() {
		return this.sdkSignMarshal;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}
	
	public void setRequestMarshal(ApiSdkMarshal<Map<String, String>, ApiRequest> requestMarshal) {
		this.requestMarshal = requestMarshal;
	}
	
	public void setResponseUnmarshal(ApiSdkUnmarshal<ApiResponse, String> responseUnmarshal) {
		this.responseUnmarshal = responseUnmarshal;
	}
	
	public void setRedirectMarshal(ApiSdkMarshal<String, ApiRequest> redirectMarshal) {
		this.redirectMarshal = redirectMarshal;
	}
	
	public void setNotifyUnmarshal(ApiSdkUnmarshal<ApiNotify, Map<String, String>> notifyUnmarshal) {
		this.notifyUnmarshal = notifyUnmarshal;
	}
	
	public void setSdkSignMarshal(ApiSdkSignMarshal sdkSignMarshal) {
		this.sdkSignMarshal = sdkSignMarshal;
	}

}
