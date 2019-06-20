/*
 * acooly.cn Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * create by zhike@yiji.com 
 * date:2016年4月30日
 *
 */
package com.yiji.openapi.sdk.exception;

/**
 * @author zhike@yiji.com
 */
public class ApiMessageCheckException extends ApiClientException {

	/** serialVersionUID */
	private static final long serialVersionUID = 2231458792189656760L;

	/**
	 * @param message
	 */
	public ApiMessageCheckException(String message) {
		super(message);
	}

	/**
	 * @param message
	 */
	public ApiMessageCheckException(String propertyName, String message) {
		super("参数" + propertyName + "校验未通过：" + message);
	}

}
