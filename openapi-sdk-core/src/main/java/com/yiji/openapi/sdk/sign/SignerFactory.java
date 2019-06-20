package com.yiji.openapi.sdk.sign;

/**
 * 签名对象工厂
 * 
 * @author zhike@yiji.com
 * @date 2014年6月3日
 * @param <T>
 */
public interface SignerFactory<T, K> {

	Signer<T, K> getSigner(String signType);

}
