package com.yiji.openapi.sdk.common.convert.converter;

import com.yiji.openapi.sdk.common.utils.Money;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.convert.converter.Converter;

/**
 * money对象转换为字符串
 */
public class MoneyToStringConverter implements Converter<Money, String> {

	private static Log logger = LogFactory.getLog(MoneyToStringConverter.class);

	@Override
	public String convert(Money source) {
		if (source == null) {
			return null;
		}
		try {
			return source.getAmount().toString();
		} catch (Exception e) {
			logger.warn("MoneyToString转换失败,source:" + source);
			return null;
		}
	}
}
