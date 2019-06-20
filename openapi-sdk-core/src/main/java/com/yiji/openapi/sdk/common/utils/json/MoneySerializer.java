/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * qzhanbo@yiji.com 2014-07-27 13:53 创建
 *
 */
package com.yiji.openapi.sdk.common.utils.json;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.yiji.openapi.sdk.common.convert.ApiServiceConversionService;
import com.yiji.openapi.sdk.common.utils.Money;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author qzhanbo@yiji.com
 */
public class MoneySerializer implements ObjectSerializer {

	public static final MoneySerializer INSTANCE = new MoneySerializer();

	private MoneySerializer() {

	}

//	@Override
//	public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType) throws IOException {
//		SerializeWriter out = serializer.getWriter();
//		Money value = (Money) object;
//		if (value == null) {
//			out.writeNull();
//			return;
//		}
//		out.write(ApiServiceConversionService.INSTANCE.convert(value, String.class));
//	}

	@Override
	public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
		SerializeWriter out = serializer.getWriter();
		Money value = (Money) object;
		if (value == null) {
			out.writeNull();
			return;
		}
		out.write(ApiServiceConversionService.INSTANCE.convert(value, String.class));
	}
}