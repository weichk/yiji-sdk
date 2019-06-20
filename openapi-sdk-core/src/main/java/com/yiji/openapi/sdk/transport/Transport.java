package com.yiji.openapi.sdk.transport;

import java.util.Map;

public interface Transport {
	
	/**
	 * 发送请求，接收响应
	 * @param request
	 * @return
	 */
	String exchange(Map<String, String> request);
	
	/**
	 * 发送跳转请求，接收响应
	 * @param request
	 * @return
	 */
	String redirectExchange(Map<String, String> request);
}
