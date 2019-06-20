/*
 * www.yiji.com Inc.
 * Copyright (c) 2016 All Rights Reserved
 */

/*
 * 修订记录:
 * zhike@yiji.com 2016-08-03 15:02 创建
 *
 */
package com.yiji.openapi.sdk.common.convert.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.convert.support.GenericConversionService;

import java.util.Locale;

/**
 * @author zhike@yiji.com
 */
public class DefaultConversionService extends GenericConversionService {
    /**
     * Create a new {@code DefaultConversionService} with the set of
     * {@linkplain DefaultConversionService#addDefaultConverters(ConverterRegistry) default converters}.
     */
    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    // static utility methods

    /**
     * Add converters appropriate for most environments.
     * @param converterRegistry the registry of converters to add to (must also be castable to ConversionService)
     * @throws ClassCastException if the converterRegistry could not be cast to a ConversionService
     */
    public static void addDefaultConverters(ConverterRegistry converterRegistry) {
        addScalarConverters(converterRegistry);
        addCollectionConverters(converterRegistry);
        addFallbackConverters(converterRegistry);
    }

    // internal helpers

    private static void addScalarConverters(ConverterRegistry converterRegistry) {
        converterRegistry.addConverter(new StringToBooleanConverter());

        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());

        converterRegistry.addConverterFactory(new NumberToNumberConverterFactory());

        converterRegistry.addConverter(new StringToCharacterConverter());

        converterRegistry.addConverter(new NumberToCharacterConverter());
        converterRegistry.addConverterFactory(new CharacterToNumberFactory());

        converterRegistry.addConverterFactory(new StringToEnumConverterFactory());

        converterRegistry.addConverter(new StringToLocaleConverter());

        converterRegistry.addConverter(new PropertiesToStringConverter());
        converterRegistry.addConverter(new StringToPropertiesConverter());
    }

    private static void addCollectionConverters(ConverterRegistry converterRegistry) {

    }

    private static void addFallbackConverters(ConverterRegistry converterRegistry) {
        ConversionService conversionService = (ConversionService) converterRegistry;
        converterRegistry.addConverter(new ObjectToObjectConverter());
    }
}
