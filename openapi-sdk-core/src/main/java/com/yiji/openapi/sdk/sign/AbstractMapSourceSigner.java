package com.yiji.openapi.sdk.sign;

import com.yiji.openapi.sdk.common.utils.DigestUtil;
import com.yiji.openapi.sdk.common.utils.RSA;
import com.yiji.openapi.sdk.exception.ApiClientException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zhike@yiji.com on 2015/1/23.
 */
public abstract class AbstractMapSourceSigner implements Signer<Map<String, String>, String> {

	private static final Log logger = LogFactory.getLog(AbstractMapSourceSigner.class);

	@Override
	public String sign(Map<String, String> stringStringMap, String key) {
		return doSign(stringStringMap, key).getLeft();
	}

	@Override
	public void verify(String sign, String key, Map<String, String> stringStringMap) throws ApiClientException {
		String signType = stringStringMap.get("signType");
		boolean isPass = true;
		String waitSignString = DigestUtil.getWaitSignStr(stringStringMap, "UTF-8");
		if(StringUtils.equals(signType, SignTypeEnum.RSA.name())) {
			isPass = RSA.verifyBase64(waitSignString, sign);
		}else {
			Pair<String, String> signPair = doSign(stringStringMap, key);
//			waitSignString = signPair.getRight();
			if (!sign.equals(signPair.getLeft())) {
				isPass = false;
			}
		}
		if (!isPass) {
			throw new ApiClientException("验签失败");
		}
		logger.info("验签成功");
	}

	protected Pair<String, String> doSign(Map<String, String> stringStringMap, Object key) {
		String waitToSignStr = doStringToSign(stringStringMap);
		String signature = doSign(waitToSignStr, (String) key);
		Pair<String, String> pair = Pair.of(signature, waitToSignStr);
		logger.info("签名结果["+pair.getLeft()+"]");
		return pair;
	}

	protected String doStringToSign(Map<String, String> stringStringMap) {
		String waitToSignStr = null;
		Map<String, String> sortedMap = new TreeMap<String, String>(stringStringMap);
		if (sortedMap.containsKey("sign")) {
			sortedMap.remove("sign");
		}
		StringBuilder stringToSign = new StringBuilder();
		if (sortedMap.size() > 0) {
			for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
				if (entry.getValue() != null) {
					stringToSign.append(entry.getKey()).append("=").append(String.valueOf(entry.getValue())).append("&");
				}
			}
			stringToSign.deleteCharAt(stringToSign.length() - 1);
			waitToSignStr = stringToSign.toString();
			logger.debug("待签名字符串["+waitToSignStr+"]");
		}
		return waitToSignStr;
	}

	protected abstract String doSign(String waitToSignStr, String key);

}
