/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */
package com.yiji.openapi.sdk.sign;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * MD5签名实现
 *
 * @author zhike@yiji.com
 * @date 2014年8月3日
 */
public class Md5Signer extends AbstractMapSourceSigner {

    private static final Log logger = LogFactory.getLog(Md5Signer.class);

    @Override
    protected String doSign(String waitToSignStr, String key) {
        logger.info("MD5待签字符串["+waitToSignStr+"]");
        return DigestUtils.md5Hex(waitToSignStr + key);
    }

    @Override
    public SignTypeEnum getSinType() {
        return SignTypeEnum.MD5;
    }

}
