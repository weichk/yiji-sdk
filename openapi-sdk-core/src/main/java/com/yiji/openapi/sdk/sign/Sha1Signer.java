package com.yiji.openapi.sdk.sign;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Sha1Hex 签名和验签
 * <p/>
 * Created by zhike@yiji.com on 2015/1/23.
 */
public class Sha1Signer extends AbstractMapSourceSigner {

	@Override
	protected String doSign(String waitToSignStr, String key) {
		return DigestUtils.sha1Hex(waitToSignStr + key);
	}

	@Override
	public SignTypeEnum getSinType() {
		return SignTypeEnum.Sha1Hex;
	}
}
