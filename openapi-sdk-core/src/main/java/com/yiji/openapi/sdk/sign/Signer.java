package com.yiji.openapi.sdk.sign;

/**
 * 签名接口
 *
 * @param <T>
 * @author zhike@yiji.com
 * @date 2014年6月3日
 */
public interface Signer<T, K> {

    /**
     * 签名
     *
     * @param t
     * @param key
     * @return
     */
    String sign(T t, K key);

    /**
     * 认证失败抛出异常
     *
     * @param sign
     * @param key
     * @param t
     */
    void verify(String sign, K key, T t);

    /**
     * 签名算法
     *
     * @return
     */
    SignTypeEnum getSinType();
}
