/**
 * create by zhike@yiji.com
 * date:2015年3月20日
 */
package com.yiji.openapi.sdk.message;

import com.yiji.openapi.sdk.common.message.ApiMessage;

/**
 * @author zhike@yiji.com
 *
 */
public class MessageMeta {

	private Class<? extends ApiMessage> response;
	private Class<? extends ApiMessage> asyncNotify;
	private Class<? extends ApiMessage> syncNotify;
	
	private Class<? extends ApiMessage> request;

	public MessageMeta() {
		super();
	}

	public MessageMeta(Class<? extends ApiMessage> response) {
		super();
		this.response = response;
	}

	public MessageMeta(Class<? extends ApiMessage> response, Class<? extends ApiMessage> asyncNotify) {
		super();
		this.response = response;
		this.asyncNotify = asyncNotify;
	}

	public MessageMeta(Class<? extends ApiMessage> response, Class<? extends ApiMessage> asyncNotify,
			Class<? extends ApiMessage> syncNotify) {
		super();
		this.response = response;
		this.asyncNotify = asyncNotify;
		this.syncNotify = syncNotify;
	}

	public Class<? extends ApiMessage> getResponse() {
		return response;
	}

	public void setResponse(Class<? extends ApiMessage> response) {
		this.response = response;
	}

	public Class<? extends ApiMessage> getAsyncNotify() {
		return asyncNotify;
	}

	public void setAsyncNotify(Class<? extends ApiMessage> asyncNotify) {
		this.asyncNotify = asyncNotify;
	}

	public Class<? extends ApiMessage> getSyncNotify() {
		return syncNotify;
	}

	public void setSyncNotify(Class<? extends ApiMessage> syncNotify) {
		this.syncNotify = syncNotify;
	}

	public Class<? extends ApiMessage> getRequest() {
		return request;
	}

	public void setRequest(Class<? extends ApiMessage> request) {
		this.request = request;
	}

	
	
}
