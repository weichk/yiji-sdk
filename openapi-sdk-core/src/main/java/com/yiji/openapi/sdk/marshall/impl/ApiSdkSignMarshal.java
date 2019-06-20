/*
 * www.yiji.com Inc.
 * Copyright (c) 2015 All Rights Reserved.
 */

/*
 * 修订记录：
 * zhike 2016年7月7日 下午6:17:33 创建
 */
package com.yiji.openapi.sdk.marshall.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.yiji.openapi.sdk.ApiSdkConstants;
import com.yiji.openapi.sdk.common.ApiConstants;
import com.yiji.openapi.sdk.common.utils.*;
import com.yiji.openapi.sdk.exception.ApiClientException;
import com.yiji.openapi.sdk.marshall.ApiSdkMarshalSupport;
import com.yiji.openapi.sdk.sign.SignTypeEnum;
import com.yiji.openapi.sdk.sign.Signer;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 *
 *
 * @author zhike@yiji.com
 * @data 2016年7月7日
 */

public class ApiSdkSignMarshal extends ApiSdkMarshalSupport {
	private static final Log logger = LogFactory.getLog(ApiSdkSignMarshal.class);
	
	/**
	 * 签名
	 * @param object
	 * @return
	 */
	public String sign(Object object) {
		Set<String> simplePropertyNames = Reflections.getSimpleFieldNames(object.getClass());
		String[] propertyNames = simplePropertyNames.toArray(new String[] {});
		
		List<String> pairNameValues = new ArrayList<String>(propertyNames.length);
		String value;
		for (String name : propertyNames) {
			value = (String) Reflections.invokeGetter(object, name);
			if (StringUtils.isNotBlank(value) && !StringUtils.equals(name, "sign")) {
				pairNameValues.add(name + "=" + value);
			}
		}
		Collections.sort(pairNameValues);
		return sign(pairNameValues);
	}

	public String sign(Object object,String sercetKey) {
		Set<String> simplePropertyNames = Reflections.getSimpleFieldNames(object.getClass());
		String[] propertyNames = simplePropertyNames.toArray(new String[] {});

		List<String> pairNameValues = new ArrayList<String>(propertyNames.length);
		String value;
		for (String name : propertyNames) {
			value = (String) Reflections.invokeGetter(object, name);
			if (StringUtils.isNotBlank(value) && !StringUtils.equals(name, "sign")) {
				pairNameValues.add(name + "=" + value);
			}
		}
		Collections.sort(pairNameValues);
		return sign(pairNameValues,sercetKey);
	}
	
	/**
	 * 签名
	 * @param parameters
	 * @return
	 */
	public String sign(List<String> parameters) {
		String stringToSign = StringUtils.join(parameters.iterator(), "&");
		return sign(stringToSign);
	}

	public String sign(List<String> parameters,String sercetKey) {
		String stringToSign = StringUtils.join(parameters.iterator(), "&");
		return sign(stringToSign,sercetKey);
	}
	/**
	 * 签名
	 * @return
	 */
	public String sign(Map<String, String> waitSignMap) {
		Signer signer = getSigner(SignTypeEnum.valueOf(waitSignMap.get("signType")));
		String signature = signer.sign(waitSignMap, ApiSdkConstants.SECRETKEY);
		return signature;
	}

	public String sign(Map<String, String> waitSignMap,String sercetKey) {
		Signer signer = getSigner(SignTypeEnum.valueOf(waitSignMap.get("signType")));
		String signature = signer.sign(waitSignMap, sercetKey);
		return signature;
	}
	
	/**
	 * 签名
	 * @param waitSignStr
	 * @return
	 */
	public String sign(String waitSignStr) {
		String signType = Strings.getSignTypeFromWaitSignStr(waitSignStr);
		String signature = "";
		if (StringUtils.equals("RSA", signType)) {
			signature = RSA.signBase64(waitSignStr);
		} else {
			signature = DigestUtils.md5Hex(waitSignStr + ApiSdkConstants.SECRETKEY);
		}
		logger.info(signType + "待签字符串["+waitSignStr+"]");
		return signature;
	}

	public String sign(String waitSignStr,String sercetKey) {
		String signType = Strings.getSignTypeFromWaitSignStr(waitSignStr);
		String signature = "";
		if (StringUtils.equals("RSA", signType)) {
			signature = RSA.signBase64(waitSignStr);
		} else {
			signature = DigestUtils.md5Hex(waitSignStr + sercetKey);
		}
		logger.info(signType + "待签字符串["+waitSignStr+"]");
		return signature;
	}
	
	/**
	 * 验签
	 * @param responseData
	 * @return
	 */
	public boolean verySign(String responseData) {
		boolean isPass = true;
		try {
			Map<String, String> dataMap = JSON.parseObject(responseData,Map.class, Feature.OrderedField);
			String signType = dataMap.get(ApiConstants.SIGN_TYPE);
			Signer signer = getSigner(SignTypeEnum.valueOf(signType));
			String sign = dataMap.get(ApiConstants.SIGN);
			signer.verify(sign, ApiSdkConstants.SECRETKEY, dataMap);
		} catch (ApiClientException ae) {
			isPass = false;
		} catch (Exception e) {
			isPass = false;
			logger.info("验签失败["+e.getMessage()+"]");
		}
		return isPass;
	}

	public boolean verySign(String responseData,String sercetKey) {
		boolean isPass = true;
		try {
			Map<String, String> dataMap = JSON.parseObject(responseData,Map.class, Feature.OrderedField);
			String signType = dataMap.get(ApiConstants.SIGN_TYPE);
			Signer signer = getSigner(SignTypeEnum.valueOf(signType));
			String sign = dataMap.get(ApiConstants.SIGN);
			signer.verify(sign, sercetKey, dataMap);
		} catch (ApiClientException ae) {
			isPass = false;
		} catch (Exception e) {
			isPass = false;
			logger.info("验签失败["+e.getMessage()+"]");
		}
		return isPass;
	}

	/**
	 * 加密数据
	 * @param plainText
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encrypt(String plainText, String signType) {
		if (StringUtils.equals("RSA", signType)) {
			return RSA.encryptByPublicKeyBase64(plainText, "UTF-8");
		} else {
			return Cryptos.encodeBase64(plainText.getBytes(), ApiSdkConstants.SECRETKEY.substring(0, 16).getBytes());
		}
	}

	public static String encrypt(String plainText, String signType,String sercetKey) {
		if (StringUtils.equals("RSA", signType)) {
			return RSA.encryptByPublicKeyBase64(plainText, "UTF-8");
		} else {
			return Cryptos.encodeBase64(plainText.getBytes(), sercetKey.substring(0, 16).getBytes());
		}
	}
	
	/**
	 * 解密数据
	 * @param plainText
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decrypt(String plainText, String signType) {
		try {
			if (StringUtils.equals("RSA", signType)) {
				return RSA.decryptByPrivateKeyBase64(plainText, "UTF-8");
			} else {
				return Cryptos.aesDecrypt(Encodes.decodeBase64(plainText), ApiSdkConstants.SECRETKEY.substring(0, 16)
					.getBytes(Encodes.DEFAULT_URL_ENCODING));
			}
		} catch (UnsupportedEncodingException e) {
			throw new ApiClientException("数据解密错误:" + e.getMessage());
		}
	}

	public static String decrypt(String plainText, String signType,String sercetKey) {
		try {
			if (StringUtils.equals("RSA", signType)) {
				return RSA.decryptByPrivateKeyBase64(plainText, "UTF-8");
			} else {
				return Cryptos.aesDecrypt(Encodes.decodeBase64(plainText), sercetKey.substring(0, 16)
						.getBytes(Encodes.DEFAULT_URL_ENCODING));
			}
		} catch (UnsupportedEncodingException e) {
			throw new ApiClientException("数据解密错误:" + e.getMessage());
		}
	}
}
