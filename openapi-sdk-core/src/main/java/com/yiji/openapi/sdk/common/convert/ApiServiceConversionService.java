/*
 * acooly.cn Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * create by zhike@yiji.com
 * date:2016年3月17日
 *
 */
package com.yiji.openapi.sdk.common.convert;

import com.yiji.openapi.sdk.common.convert.converter.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * 数据类型转换框架
 *
 * @author zhike@yiji.com
 */
@Component
public class ApiServiceConversionService extends DefaultConversionService implements InitializingBean {

    private static Log logger = LogFactory.getLog(ApiServiceConversionService.class);

    // 静态变量初始化为默认实现,方便测试用例编写,服务器端则通过@PostConstruct重新初始化.
    public static ApiServiceConversionService INSTANCE = new ApiServiceConversionService();

    @Autowired
    protected ApplicationContext applicationContext;

    public ApiServiceConversionService() {
        super();
        addCustomConverters();
    }

    private void addCustomConverters() {
		addConverter(new DateToStringConverter());
		addConverter(new MoneyToStringConverter());
		addConverter(new StringToDateConverter());
		addConverter(new StringToMoneyConverter());
    }


    @PostConstruct
    public void init() {
        INSTANCE = this;
    }


    @SuppressWarnings("rawtypes")
	@Override
    public void afterPropertiesSet() throws Exception {

        Map<String, ApiServiceConverter> apiServiceConverterMap = applicationContext.getBeansOfType(ApiServiceConverter.class);
        if (apiServiceConverterMap == null || apiServiceConverterMap.isEmpty()) {
            return;
        }

        for (ApiServiceConverter converter : apiServiceConverterMap.values()) {
            addConverter(converter);
            logger.info("注册扩展converter"+converter.getClass().getName());
        }


    }
}
