/*
 * acooly.cn Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * create by zhike@yiji.com 
 * date:2016年4月29日
 *
 */
package com.yiji.openapi.sdk.support;

import com.yiji.openapi.sdk.ApiSdkConstants;
import com.yiji.openapi.sdk.common.ApiConstants;
import com.yiji.openapi.sdk.common.annotation.OpenApiMessage;
import com.yiji.openapi.sdk.common.convert.ApiServiceConversionService;
import com.yiji.openapi.sdk.common.message.ApiMessage;
import com.yiji.openapi.sdk.common.utils.Encodes;
import com.yiji.openapi.sdk.common.utils.Reflections;
import com.yiji.openapi.sdk.common.utils.Strings;
import com.yiji.openapi.sdk.common.utils.json.JsonMarshallor;
import com.yiji.openapi.sdk.exception.ApiClientException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.ConverterNotFoundException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * openApi 客户端 专用工具
 *
 * @author zhike@yiji.com
 */
public class ApiClientUtils {

    private static final Log logger = LogFactory.getLog(ApiClientUtils.class);

    private static JsonMarshallor jsonMarshallor = JsonMarshallor.INSTANCE;

    private static ApiServiceConversionService conversion = ApiServiceConversionService.INSTANCE;

    /**
     * 获取待签字符串
     *
     * @param apiMessage
     * @return
     */
    public static String getWaitForSignString(ApiMessage apiMessage) {
        return getMessage(convertMarshall(apiMessage, true), false);
    }

    /**
     * 获取发送报文
     *
     * @param apiMessage
     * @return
     */
    public static String getMessageForSend(ApiMessage apiMessage) {
        return getMessage(convertMarshall(apiMessage, false), true);
    }


    /**
     * 转换MAP为报文体
     *
     * @param map
     * @param encode
     * @return
     */
    public static String getMessage(Map<String, String> map, boolean encode) {
        String message = null;
        Map<String, String> sortedMap = new TreeMap<String, String>(map);
        StringBuilder messageBuilder = new StringBuilder();
        if (sortedMap.size() > 0) {
            for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
                if (Strings.isNotBlank(entry.getValue())) {
                    messageBuilder.append(entry.getKey()).append("=")
                            .append(encode ? Encodes.urlEncode(entry.getValue()) : entry.getValue())
                            .append("&");
                }
            }
            messageBuilder.deleteCharAt(messageBuilder.length() - 1);
            message = messageBuilder.toString();
        }
        return message;
    }


    /**
     * 转换请求报文为Map
     *
     * @param apiMessage
     * @return
     */
    public static Map<String, String> convertMarshall(ApiMessage apiMessage, boolean filterNoSign) {
        Map<String, String> map = new HashMap<String, String>();
        Set<Field> fields = Reflections.getFields(apiMessage.getClass());
        String key = null;
        String value = null;
        for (Field f : fields) {
            key = f.getName();
            value = getPropertyValue(apiMessage, f);
            if (Strings.isBlank(value) || (filterNoSign && Strings.equals(f.getName(), ApiConstants.SIGN))) {
                continue;
            }
            map.put(key, value);
        }
        return map;
    }


    /**
     * 解析报文为响应对象
     *
     * @param responseMap
     * @param response
     * @param <T>
     * @return
     */
    public static <T extends ApiMessage> T convertUnmarshall(Map<String, String> responseMap, T response) {
        Set<Field> fields = Reflections.getFields(response.getClass());
        Object object = null;
        for (Field f : fields) {
            object = responseMap.get(f.getName());
            setPropertyValue(response, f, object == null ? null : String.valueOf(object));
        }
        return response;
    }


    /**
     * 读取和序列化设置实体属性值
     *
     * @param apiMessage
     * @param field
     * @return
     */
    public static String getPropertyValue(ApiMessage apiMessage, Field field) {
        Object value = null;
        try {
            if(field.getType().equals(boolean.class)) {
                value = Reflections.invokeIsBoolean(apiMessage, field.getName());
            }else {
                value = Reflections.invokeGetter(apiMessage, field.getName());
            }
            if (value == null) {
                return null;
            }
            if(field.getType().isEnum()) {
                return getEnumCode(field);
            } else if (ApiTypes.isSimpleType(field.getType())) {
                // 简单类型,日期和Money
                return conversion.convert(value, String.class);
            } else {
                // 集合,MAP,对象
                return jsonMarshallor.marshall(value);
            }
        } catch (ConverterNotFoundException ex) {
            return jsonMarshallor.marshall(value);
        } catch (Exception ex) {
            logger.error("属性"+field.getType()+"读取失败", ex);
            throw new ApiClientException("property=" + field.getName() + "读取失败");
        }
    }

    public static String getEnumCode(Field field) {
        Method getMethodCode = null;
        Object[] objects = field.getType().getEnumConstants();
        //获取枚举名并引入
        String enumName = field.getType().getName();
        Class model = null;
        try {
            model = Class.forName(enumName);
        } catch (Exception e) {
            logger.error("找不到枚举名" + enumName);
        }
        //获得Model类的get方法，无参数
        try {
            getMethodCode = model.getMethod("getCode");
        } catch (NoSuchMethodException e) {
            logger.error("没有找到getCode方法" + enumName);
        } catch (Exception e) {
            logger.error("其他异常" + enumName);
        }
        //遍历message
        String enumCode = null;
        for (Object object : objects) {
            Object code = null;
            try {
                code = getMethodCode.invoke(object, null);
                enumCode = code.toString();
            } catch (Exception e) {
                logger.error("调用方法失败" + object + enumName);
            }
        }
        return enumCode;
    }

    public static void setPropertyValue(ApiMessage apiMessage, Field field, String newValue) {
        if (field == null || newValue == null) {
            return;
        }
        try {
            Object convertedValue = null;
            if (ApiTypes.isSimpleType(field.getType())) {
                // 简单类型,日期和Money
                convertedValue = conversion.convert(newValue, field.getType());
            } else {
                // 集合,MAP,对象
//                TypeToken typeToken = TypeToken.of(field.getGenericType());
//                convertedValue = jsonMarshallor.parse(newValue, typeToken.getType());
                convertedValue = jsonMarshallor.parse(newValue, field.getGenericType());
            }
            Reflections.makeAccessible(field);
            field.set(apiMessage, convertedValue);
        } catch (ConversionFailedException cfe) {
            String enumOptions = null;
            if (field.getType().isEnum()) {
                // 如果枚举值为空,忽略
                if (StringUtils.isBlank(newValue.toString())) {
                    return;
                }

                Object[] objects = field.getType().getEnumConstants();
                enumOptions = Arrays.toString(objects);
            } else {
                logger.error("属性"+field.getType()+"设置失败", cfe);
            }
            throw new ApiClientException("属性:" + field.getName() + ",值:" + newValue + "设置失败:" +
                    field.getType().getSimpleName() + (enumOptions != null ? enumOptions : ""));
        } catch (Exception ex) {
            logger.error("属性"+field.toString()+"设置失败,值="+newValue, ex);
            throw new ApiClientException("属性" + field.getName() + ",设置失败,值:" + newValue);
        }
    }


    /**
     * 获取报文对应的服务名
     *
     * @param apiMessage
     * @return
     */
    public static String getServiceName(ApiMessage apiMessage) {
        String serviceName = apiMessage.getService();
        if (Strings.isBlank(serviceName)) {
            OpenApiMessage openApiMessage = apiMessage.getClass().getAnnotation(OpenApiMessage.class);
            if (openApiMessage != null) {
                serviceName = openApiMessage.service();
            }
        }
        return serviceName;
    }

//    /**
//     * 获取集合对象的泛型参数类型
//     *
//     * @param field
//     * @return
//     */
//    public static Class<?> getFieldGenericType(Field field) {
//        TypeToken<?> t = TypeToken.of(field.getGenericType());
//        if (t.getType() instanceof ParameterizedType) {
//            Type type = ((ParameterizedType) t.getType()).getActualTypeArguments()[0];
//            if (ParameterizedType.class.isAssignableFrom(type.getClass())) {
//                return ((Class<?>) ((ParameterizedType) type).getRawType());
//            } else if (type instanceof Class) {
//                return (Class<?>) type;
//            } else {
//                // 获取基础POJO的泛型
//                Class<?> cc = field.getDeclaringClass();
//                Class<?> tt = null;
//                do {
//                    tt = GenericsUtils.getSuperClassGenricType(cc);
//                    if (tt != Object.class) {
//                        break;
//                    }
//                    cc = cc.getSuperclass();
//                } while (cc != Object.class);
//                return tt;
//            }
//        }
//        return null;
//    }

//    public static Class<?> getFieldGenericType(Class<?> clazz, String fieldName) {
//        Set<Field> fields = Reflections.getFields(clazz);
//        Field f = null;
//        for (Field field : fields) {
//            if (field.getName().equals(fieldName)) {
//                f = field;
//                break;
//            }
//        }
//        if (f == null) {
//            throw new RuntimeException("Class " + clazz + "没有属性：" + fieldName);
//        }
//        return getFieldGenericType(f);
//    }

    public static String getNotifyUrl(String service) {
        return getCanonicalUrl(ApiSdkConstants.SITE_DOMAIN, getCanonicalUrl(ApiSdkConstants.NOTIFY_URL, service));
    }

    public static String getReturnUrl(String url) {
        return getCanonicalUrl(ApiSdkConstants.SITE_DOMAIN, url);
    }

    public static String getCanonicalUrl(String prefix, String postfix) {
        if (Strings.endsWith(prefix, "/")) {
            prefix = Strings.removeEnd(prefix, "/");
        }
        if (Strings.startsWith(postfix, "/")) {
            return prefix + postfix;
        } else {
            return prefix + "/" + postfix;
        }
    }

}
