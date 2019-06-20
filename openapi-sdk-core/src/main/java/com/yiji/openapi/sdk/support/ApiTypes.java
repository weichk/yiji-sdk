/*
 * acooly.cn Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * create by zhike@yiji.com 
 * date:2016年4月30日
 *
 */
package com.yiji.openapi.sdk.support;

import com.yiji.openapi.sdk.common.utils.Money;
import com.yiji.openapi.sdk.common.utils.Reflections;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Java 数据类型判断处理相关工具类
 *
 * @author zhike@yiji.com
 */
public class ApiTypes {

    public static boolean isCollection(Field field) {
        return Collection.class.isAssignableFrom(field.getType()) || field.getType().isArray();
    }

    public static boolean isCollection(Class<?> clazz) {
        return Collection.class.isAssignableFrom(clazz) || clazz.isArray();
    }

    public static boolean isMap(Field field) {
        return Map.class.isAssignableFrom(field.getType());
    }

    public static boolean isMoney(Field field) {
        return Money.class.isAssignableFrom(field.getType());
    }

    public static boolean isMoney(Class<?> clazz) {
        return Money.class.isAssignableFrom(clazz);
    }

    public static boolean isNumber(Field field) {
        return isNumber(field.getType());
    }

    public static boolean isNumber(Class<?> clazz) {
        return Number.class.isAssignableFrom(clazz)
                || (clazz.isPrimitive() && "long,int,byte,short".contains(clazz.toString()));
    }

    public static boolean isBasicType(Field field) {
        return isBasicType(field.getType());
    }

    public static boolean isSimpleType(Class<?> clazz) {
        return isBasicType(clazz) || isMoney(clazz) || clazz.isEnum();
    }

    public static boolean isBasicType(Class<?> clazz) {
        return String.class.isAssignableFrom(clazz) || Date.class.isAssignableFrom(clazz) || clazz.isPrimitive() || Reflections.isWrapClass(clazz);
    }
}
