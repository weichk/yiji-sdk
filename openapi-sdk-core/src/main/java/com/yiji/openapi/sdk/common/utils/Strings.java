package com.yiji.openapi.sdk.common.utils;

import com.yiji.openapi.sdk.exception.OpenApiClientException;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class Strings extends StringUtils {
	
	public static <T> T isBlankDefault(T text, T defaultValue) {
		if (text == null) {
			return defaultValue;
		}
		if (text.getClass().isAssignableFrom(String.class)) {
			return Strings.isBlank((String) text) ? defaultValue : text;
		} else {
			return text;
		}
		
	}
	
	public static boolean matcher(String regex, String value) {
		Pattern p = Pattern.compile(regex);
		return p.matcher(value).matches();
	}
	
	/**
	 * 前置和后置mask
	 * 
	 * @param text
	 * @param preSize
	 * @param postSize
	 * @return
	 */
	public static String mask(String text, int preSize, int postSize) {
		return mask(text, preSize, postSize, '*');
	}
	
	/**
	 * 前置和后置mask
	 * 
	 * @param text
	 * @param preSize
	 * @param postSize
	 * @param replaceChar
	 * @return
	 */
	public static String mask(String text, int preSize, int postSize, Character replaceChar) {
		String source = trimToEmpty(text);
		if (isBlank(source)) {
			return text;
		}
		if (replaceChar == null) {
			replaceChar = '*';
		}
		if (preSize + postSize >= text.length()) {
			return leftPad("", text.length(), replaceChar);
		}
		StringBuilder sb = new StringBuilder();
		sb.append(leftPad("", preSize, replaceChar));
		sb.append(substring(text, preSize, text.length() - postSize));
		sb.append(leftPad("", postSize, replaceChar));
		return sb.toString();
	}
	
	public static String maskUserName(String text) {
		return maskReverse(text, 2, 1);
	}
	
	public static String maskBankCardNo(String text) {
		return maskReverse(text, 4, 3);
	}
	
	public static String maskIdCardNo(String text) {
		return maskReverse(text, 3, 4);
	}
	
	public static String maskMobileNo(String text) {
		return maskReverse(text, 3, 3);
	}
	
	public static String maskReverse(String text, int start, int end) {
		return maskReverse(text, start, end, '*');
	}
	
	public static String maskReverse(String text, int start, int end, Character replaceChar) {
		String source = trimToEmpty(text);
		if (isBlank(source)) {
			return text;
		}
		if (replaceChar == null) {
			replaceChar = '*';
		}
		if (start >= text.length() || end >= text.length() || text.length() - start - end < 0) {
			return leftPad("", text.length(), replaceChar);
		}
		
		return left(text, start) + leftPad("", text.length() - start - end, replaceChar) + right(text, end);
	}
	
	/**
	 * 获取代签字符串中的签名类型
	 * @param signStr
	 * @return
	 */
	public static String getSignTypeFromWaitSignStr(String signStr) {
		String signType = "";
		if (StringUtils.isBlank(signStr)) {
			throw new OpenApiClientException("代签字符串不能为空");
		}
		
		String[] keyValueStrs = signStr.split("&");
		if (keyValueStrs.length == 0) {
			throw new OpenApiClientException("代签字符串格式");
		}
		
		for (String keyValueStr : keyValueStrs) {
			if(keyValueStr.indexOf("=")==-1) {
				throw new OpenApiClientException("代签字符串格式");
			}
			String[] str = keyValueStr.split("=");
			if(StringUtils.equals("signType", str[0])) {
				signType = str[1];
				break;
			}
		}
		if(StringUtils.isBlank(signType)) {
			throw new OpenApiClientException("代签字符串中缺少参数signType");
		}
		return signType;
	}
	
	public static void main(String[] args) {
		String text = "zhike@yiji.com";
		System.out.println("用户名: " + maskReverse(text, 2, 1, '*'));
		text = "510221198209476371";
		System.out.println("身份证: " + maskReverse(text, 3, 4, '*'));
		text = "13896177630";
		System.out.println("手机号: " + maskReverse(text, 3, 3, '*'));
		text = "6221880231092323876";
		System.out.println("银行卡: " + maskReverse(text, 4, 3, '*'));
	}
}
