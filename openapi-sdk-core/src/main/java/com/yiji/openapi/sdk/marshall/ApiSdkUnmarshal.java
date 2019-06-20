/**
 * create by zhike@yiji.com
 * date:2015年3月20日
 */
package com.yiji.openapi.sdk.marshall;

/**
 * @author zhike@yiji.com
 *
 */
public interface ApiSdkUnmarshal<T, R> {

	T unmarshal(R message, String serviceKey);

	T unmarshal(R message, String serviceKey,String secretKey);

}
