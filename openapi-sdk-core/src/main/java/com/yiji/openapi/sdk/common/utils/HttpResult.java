/**
 * create by zhike@yiji.com
 * date:2015年3月14日
 */
package com.yiji.openapi.sdk.common.utils;

import java.util.Map;

/**
 * @author zhike@yiji.com
 *
 */
public class HttpResult {
	private int status;
	private Map<String, String> headers;
	private String body;
	private String redirectURL;
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public Map<String, String> getHeaders() {
		return headers;
	}
	
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getRedirectURL() {
		return this.redirectURL;
	}
	
	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}
	
	@Override
	public String toString() {
		return String.format("HttpResult: {status:%s, headers:%s, body:%s,redirectURL:%s}", status, headers, body,redirectURL);
	}
	
}
