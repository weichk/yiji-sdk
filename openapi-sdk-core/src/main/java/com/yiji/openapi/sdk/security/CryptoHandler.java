/*
 * acooly.cn Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * create by zhike@yiji.com 
 * date:2016年4月22日
 *
 */
package com.yiji.openapi.sdk.security;

/**
 * 加解密处理 接口
 * 
 * @author zhike@yiji.com
 */
public interface CryptoHandler {

	/**
	 * 加密
	 * 
	 * @param plain
	 */
	String encrypt(String plain);

	/**
	 * 解密
	 * 
	 * @param crypt
	 */
	String decrypt(String crypt);

}
