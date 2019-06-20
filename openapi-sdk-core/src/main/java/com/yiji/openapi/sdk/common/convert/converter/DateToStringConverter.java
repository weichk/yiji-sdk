package com.yiji.openapi.sdk.common.convert.converter;

import com.yiji.openapi.sdk.common.ApiConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateToStringConverter implements Converter<Date, String> {

	private static Log logger = LogFactory.getLog(DateToStringConverter.class);

	@Override
	public String convert(Date source) {
		if (source == null) {
			return null;
		}
		try {
			return new SimpleDateFormat(ApiConstants.DATA_FORMAT).format(source);
		} catch (Exception e) {
			logger.warn("StringToDate转换失败,source:" + source,e);
			return null;
		}
	}
}
