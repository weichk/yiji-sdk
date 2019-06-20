/*
 * acooly.cn Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * create by zhike@yiji.com 
 * date:2016年4月22日
 *
 */
package com.yiji.openapi.sdk.security;

import com.yiji.openapi.sdk.common.utils.RSA;
import com.yiji.openapi.sdk.security.support.KeyPair;

/**
 * RSA 非对称加/解密处理实现
 * <p/>
 * <p>
 * 需要2对秘钥对:
 * <li>我方持有: 我方私钥和对方公钥
 * <li>对方持有: 对法私钥和我方公钥
 * </p>
 *
 * @author zhike@yiji.com
 */
public class RSACryptoHandler implements CryptoHandler {

    protected KeyPair cryptoKeyPair;

    public RSACryptoHandler() {
        super();
    }

    /**
     * @param cryptoKeyPair
     */
    public RSACryptoHandler(KeyPair cryptoKeyPair) {
        super();
        this.cryptoKeyPair = cryptoKeyPair;
    }

    /**
     * 发送前使用对方公钥加密
     */
    @Override
    public String encrypt(String plain) {
        cryptoKeyPair.loadKeys();
        return RSA.encryptByPublicKeyBase64(plain, cryptoKeyPair.getPublicKey(), RSA.DEFAULT_CHARSET);
    }

    /**
     * 接收后，使用自己私钥解密
     */
    @Override
    public String decrypt(String crypt) {
        cryptoKeyPair.loadKeys();
        return RSA.decryptByPrivateKeyBase64(crypt, cryptoKeyPair.getPrivateKey(), RSA.DEFAULT_CHARSET);

    }

    public void setCryptoKeyPair(KeyPair cryptoKeyPair) {
        this.cryptoKeyPair = cryptoKeyPair;
    }

}
