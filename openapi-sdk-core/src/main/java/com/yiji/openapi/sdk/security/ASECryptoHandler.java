/*
 * www.yiji.com Inc.
 * Copyright (c) 2016 All Rights Reserved
 */

/*
 * 修订记录:
 * kuli@yiji.com 2016-06-14 22:52 创建
 */
package com.yiji.openapi.sdk.security;

import com.yiji.openapi.sdk.ApiSdkConstants;
import com.yiji.openapi.sdk.common.utils.Cryptos;
import com.yiji.openapi.sdk.common.utils.Encodes;
import com.yiji.openapi.sdk.common.utils.Exceptions;
import com.yiji.openapi.sdk.exception.ApiClientException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;

/**
 * ASE 对称加密
 *
 * @author acooly
 */
public class ASECryptoHandler implements CryptoHandler {

    private static final Log logger = LogFactory.getLog(ASECryptoHandler.class);


    @Override
    public String encrypt(String plain) {
        try {
            byte[] securityKey = getSecurityKey();
            byte[] encrypt = Cryptos.aesEncrypt(plain.getBytes("UTF-8"), securityKey);
            return Encodes.encodeBase64(encrypt);
        } catch (Exception e) {
            logger.warn("数据项加密失败", e);
            throw new ApiClientException("数据项加密失败.");
        }
    }

    @Override
    public String decrypt(String crypt) {
        try {
            byte[] securityKey = getSecurityKey();
            byte[] data = Encodes.decodeBase64(crypt);
            return Cryptos.aesDecrypt(data, securityKey);
        } catch (Exception e) {
            logger.warn("数据项解密失败", e);
            throw new ApiClientException("数据项解密失败.");
        }
    }


    protected byte[] getSecurityKey() {
        String securityKey = ApiSdkConstants.SECRETKEY;
        try {
            return securityKey.substring(0, 16).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw Exceptions.unchecked(e);
        }
    }

}
