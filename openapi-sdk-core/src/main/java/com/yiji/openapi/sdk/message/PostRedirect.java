/**
 * create by zhike@yiji.com
 * date:2015年3月20日
 */
package com.yiji.openapi.sdk.message;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhike@yiji.com
 *
 */
public class PostRedirect {
	private String redirectUrl;
	private Map<String, String> formDatas = new HashMap<String, String>();

	public PostRedirect() {
		super();
	}

	public PostRedirect(String redirectUrl) {
		super();
		this.redirectUrl = redirectUrl;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public Map<String, String> getFormDatas() {
		return formDatas;
	}

	public void setFormDatas(Map<String, String> formDatas) {
		this.formDatas = formDatas;
	}

	public void addData(String key, String value) {
		formDatas.put(key, value);
	}

}
