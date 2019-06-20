/*
 * www.yiji.com Inc.
 * Copyright (c) 2015 All Rights Reserved.
 */

/*
 * 修订记录：
 * zhike 2016年7月11日 下午2:51:26 创建
 */
package com.yiji.openapi.sdk.sign;

import com.yiji.openapi.sdk.common.utils.RSA;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 *
 * @author zhike@yiji.com
 * @data 2016年7月11日
 */

public class RsaSigner extends AbstractMapSourceSigner {

	private static final Log logger = LogFactory.getLog(RsaSigner.class);
	
	/**
	 * @return
	 * @see com.yiji.openapi.sdk.sign.Signer#getSinType()
	 */
	@Override
	public SignTypeEnum getSinType() {
		return SignTypeEnum.RSA;
		
	}
	
	/**
	 * @param waitToSignStr
	 * @param key
	 * @return
	 * @see AbstractMapSourceSigner#doSign(String,
	 * String)
	 */
	@Override
	protected String doSign(String waitToSignStr, String key) {
		logger.info("RSA待签字符串:" + waitToSignStr);
		return RSA.signBase64(waitToSignStr);
	}
}
