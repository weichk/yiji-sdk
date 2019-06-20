package com.yiji.openapi.sdk.sign;

import com.yiji.openapi.sdk.exception.ApiClientException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by zhike@yiji.com on 2015/1/23.
 */
public abstract class AbstractSigner<T, K> implements Signer<T, K> {

	private static final Log logger = LogFactory.getLog(AbstractSigner.class);

	@Override
	public String sign(T t, K key) {
		return doSign(getWaitToSigin(t), key);
	}

	@Override
	public void verify(String sign, K key, T t) {
		String calcSign = doSign(getWaitToSigin(t), key);
		if (!sign.equals(calcSign)) {
			logger.error("签名验证未通过. signType:"+getSinType()+",requestSign:"+sign+",calcPlain:"+t);
			throw new ApiClientException("签名验证未通过");
		}
	}

	protected abstract String getWaitToSigin(T t);

	protected abstract String doSign(String waitToSignStr, K key);

}
