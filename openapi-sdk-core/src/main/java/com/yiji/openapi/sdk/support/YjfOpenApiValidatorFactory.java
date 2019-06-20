/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * lvchen@yiji.com 2014年7月7日 下午4:16:06 创建
 */
package com.yiji.openapi.sdk.support;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * JSR303校验工厂单例
 * 
 * @author lvchen@yiji.com
 * 
 */
public enum YjfOpenApiValidatorFactory {
	INSTANCE {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		
		@Override
		public Validator getValidator() {
			return factory.getValidator();
		}
	};
	
	public abstract Validator getValidator();
}
