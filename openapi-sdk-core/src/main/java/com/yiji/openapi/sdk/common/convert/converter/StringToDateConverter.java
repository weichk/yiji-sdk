package com.yiji.openapi.sdk.common.convert.converter;

import com.yiji.openapi.sdk.common.ApiConstants;
import com.yiji.openapi.sdk.common.utils.Dates;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {

    private static Log logger = LogFactory.getLog(StringToDateConverter.class);

    @Override
    public Date convert(String source) {
        if (source == null || source.equals("")) {
            return null;
        }
        try {
            return Dates.parse(source);
        } catch (Exception e) {
            logger.warn("StringToDate转换失败,source:" + source, e);
            throw new RuntimeException("日期格式错误,需要的格式为:" + ApiConstants.DATA_FORMAT);
        }

    }

}
