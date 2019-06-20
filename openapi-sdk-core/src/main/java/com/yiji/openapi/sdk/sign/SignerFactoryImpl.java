package com.yiji.openapi.sdk.sign;

import com.yiji.openapi.sdk.exception.ApiClientException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * 签名工厂实现
 * 
 * @author zhike@yiji.com
 * @date 2014年6月3日
 * @param <T>
 */
public class SignerFactoryImpl<T, K> implements SignerFactory<T, K>, ApplicationContextAware, InitializingBean {

	private static final Log logger = LogFactory.getLog(SignerFactoryImpl.class);

	private ApplicationContext applicationContext;

	private Map<String, Signer<T, K>> signerMap;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void afterPropertiesSet() throws Exception {
		signerMap = new HashMap<String, Signer<T, K>>();
		Map<String, Signer> signers = applicationContext.getBeansOfType(Signer.class);
		for (Map.Entry<String, Signer> entry : signers.entrySet()) {
			String signName = entry.getValue().getSinType().name();
			signerMap.put(signName, entry.getValue());
			logger.debug("加载"+signName+"签名处理器:"+entry.getValue().getClass().getName());
		}
	}

	@Override
	public Signer<T, K> getSigner(String signType) {
		Signer<T, K> signer = signerMap.get(signType);
		if (signer == null) {
			throw new ApiClientException("不支持的signType[" + signType + "]");
		}
		return signer;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
