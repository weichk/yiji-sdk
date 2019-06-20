/**
 * create by zhike@yiji.com
 * date:2015年3月21日
 */
package com.yiji.openapi.sdk;

import org.hibernate.validator.constraints.NotBlank;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Api SDK 客户端执行接口
 * 
 * @author zhike@yiji.com
 *
 */
public interface ApiServiceClient<R, T, N, X> {
	
	/**
	 * 同步接口调用
	 * 
	 * @param request
	 * @return
	 */
	T execute(R request);
	
	/**
	 * 跳转接口生成跳转报文和URL(GET)
	 * 
	 * @param request
	 * @return
	 */
	String redirectGet(R request);
	
	/**
	 * post请求引擎
	 * @param gateway
	 * @param requestMap
	 * @return
	 */
	String post(@NotBlank String gateway, Map<String, String> requestMap);
	
	/**
	 * 解析异步通知
	 * 
	 * @param notifyData
	 * @param serviceKey
	 * @return
	 */
	N notice(Map<String, String> notifyData, String serviceKey);
	
	/**
	 * 解析同步通知
	 * 
	 * @param notifyData
	 * @param serviceKey
	 * @return
	 */
	X result(Map<String, String> notifyData, String serviceKey);
	
	/**
	 * 签名
	 * @param object
	 * @return
	 */
	String sign(Object object);
	
	/**
	 * 签名
	 * @param parameters
	 * @return
	 */
	String sign(List<String> parameters);

	/**
	 * 签名
	 * @param waitSignMap
	 * @return
     */
	String sign(Map<String, String> waitSignMap);
	
	/**
	 * 签名
	 * @param waitSignStr
	 * @return
	 */
	String sign(String waitSignStr);
	
	/**
	 * 验签
	 * @param responseData
	 * @return
	 */
	boolean verySign(String responseData);
	
	/**
	 * 加密数据
	 * @param plainText
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	String encrypt(String plainText, String signType);
	
	/**
	 * 解密数据
	 * @param plainText
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	String decrypt(String plainText, String signType);
	
}
