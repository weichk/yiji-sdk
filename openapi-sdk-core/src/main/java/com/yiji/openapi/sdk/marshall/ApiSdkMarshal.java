/**
 * create by zhike@yiji.com
 * date:2015年3月20日
 */
package com.yiji.openapi.sdk.marshall;

/**
 * @author zhike@yiji.com
 *
 */
public interface ApiSdkMarshal<T, S> {

	/**
	 * 支持自动分配partnerId和serceKey
	 * @param source
	 * @return
     */
	T marshal(S source);

	/**
	 * 支持手动分配partnerId和serceKey
	 * @param source
	 * @param secretKey
     * @return
     */
	T marshal(S source,String secretKey);
}
