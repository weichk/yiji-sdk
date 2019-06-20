/*
 * acooly.cn Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * create by zhike@yiji.com 
 * date:2016年4月24日
 *
 */
package com.yiji.openapi.sdk.message.impl;

import com.yiji.openapi.sdk.ApiSdkConstants;
import com.yiji.openapi.sdk.common.annotation.OpenApiMessage;
import com.yiji.openapi.sdk.common.enums.ApiMessageType;
import com.yiji.openapi.sdk.common.message.ApiMessage;
import com.yiji.openapi.sdk.common.utils.Strings;
import com.yiji.openapi.sdk.message.MessageFactory;
import com.yiji.openapi.sdk.message.MessageMeta;
import com.yiji.openapi.sdk.support.ClassScaner;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author zhike@yiji.com
 */
public class AnnotaionScanMessageFactory implements MessageFactory, InitializingBean {

    private static final Log logger = LogFactory.getLog(AnnotaionScanMessageFactory.class);

    public Map<String, MessageMeta> metas = new HashMap<String, MessageMeta>();

    private String messagePackage = ApiSdkConstants.SERVICE_MESSAGES_PACKAGE;

    @Override
    public ApiMessage getRequest(String serviceName) {
        return newInstance(metas.get(serviceName).getRequest());
    }

    @Override
    public ApiMessage getResponse(String serviceName) {
        return newInstance(metas.get(serviceName).getResponse());
    }

    @Override
    public ApiMessage getNotify(String serviceName) {
        try {
            Class<? extends ApiMessage> notify = metas.get(serviceName).getAsyncNotify();
            if (notify == null) {
                return null;
            }
            return newInstance(notify);
        } catch (Exception e) {
            logger.info("服务" + serviceName + "不为异步服务！");
            return null;
        }
    }

    @Override
    public ApiMessage getReturn(String serviceName) {
        return newInstance(metas.get(serviceName).getSyncNotify());
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }


    @SuppressWarnings("unchecked")
    public void init() {
        Set<Class> classes = ClassScaner.scan(getScanPackages(), OpenApiMessage.class);
        OpenApiMessage openApiMessage = null;
        for (Class clazz : classes) {
            openApiMessage = getAnotation(clazz);
            if (openApiMessage != null) {
                register(openApiMessage, (Class<? extends ApiMessage>) clazz);
            }
        }
    }


    protected void register(OpenApiMessage openApiMessage, Class<? extends ApiMessage> clazz) {
        String service = openApiMessage.service();
        String version = openApiMessage.version();
        String serviceKey = service + "_" + version;
        if (metas.get(serviceKey) == null) {
            metas.put(serviceKey, new MessageMeta());
        }
        if (openApiMessage.type() == ApiMessageType.Request) {
            metas.get(serviceKey).setRequest(clazz);
        } else if (openApiMessage.type() == ApiMessageType.Response) {
            metas.get(serviceKey).setResponse(clazz);
        } else if (openApiMessage.type() == ApiMessageType.Return) {
            metas.get(serviceKey).setSyncNotify(clazz);
        } else if (openApiMessage.type() == ApiMessageType.Notify) {
            metas.get(serviceKey).setAsyncNotify(clazz);
        }
    }


    protected OpenApiMessage getAnotation(Class<?> clazz) {
        return clazz.getAnnotation(OpenApiMessage.class);
    }

    protected String getCanonicalClassPath(Resource resource) {
        try {
            URL classFileUrl = resource.getURL();
            String path = classFileUrl.getPath();
            if (Strings.contains(path, "classes/")) {
                path = StringUtils.substringAfter(path, "classes/");
            }
            if (Strings.contains(path, "jar!/")) {
                path = StringUtils.substringAfter(path, "jar!/");
            }
            path = path.replaceAll("/", ".").replace(".class", "");
            return path;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String[] getScanPackages() {
        String packages[] = Strings.split(ApiSdkConstants.SERVICE_MESSAGES_PACKAGE, ",");
        return packages;
    }

    private <T> T newInstance(Class<T> clazz) {
        try {
            return (T) clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("InstantiationException:" + clazz);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("IllegalAccessException:" + clazz);
        }
    }

    public void setMessagePackage(String messagePackage) {
        this.messagePackage = messagePackage;
    }

}
