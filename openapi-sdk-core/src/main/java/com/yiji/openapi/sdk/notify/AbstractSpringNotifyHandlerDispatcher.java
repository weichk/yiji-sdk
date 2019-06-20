/**
 * create by zhike@yiji.com
 * date:2015年4月12日
 */
package com.yiji.openapi.sdk.notify;

import com.yiji.openapi.sdk.ApiServiceClient;
import com.yiji.openapi.sdk.common.message.ApiMessage;
import com.yiji.openapi.sdk.common.utils.Exceptions;
import com.yiji.openapi.sdk.exception.ApiClientException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * 异步通知 分发器 默认实现
 *
 * @author zhike@yiji.com
 */
public abstract class AbstractSpringNotifyHandlerDispatcher
        implements NotifyHandlerDispatcher, ApplicationContextAware, InitializingBean, NotifyHandlerFactory {

    private static final Log logger = LogFactory.getLog(AbstractSpringNotifyHandlerDispatcher.class);
    private Map<String, NotifyHandler> handlers = new HashMap<String, NotifyHandler>();

    private NotifyHandler defaultNotifyHandler = new DefaultNotifyHandler();

    private ApplicationContext applicationContext;

    private ApiServiceClient<ApiMessage, ApiMessage, ApiMessage, ApiMessage> apiServiceClient;

    @Override
    public void dispatch(String notifyUrl, Map<String, String> notifyData) {
        logger.info("异步通知 入参:url:"+notifyUrl+",data:"+notifyData);
        try {
            beforeDispatch(notifyUrl, notifyData);
            String serviceKey = getServiceKey(notifyUrl, notifyData);
            ApiMessage notify = apiServiceClient.notice(notifyData, serviceKey);
            doDispatch(serviceKey, notify);
            logger.info("异步通知 出参:处理成功");
        } catch (Exception e) {
            logger.error("异步通知 出参:处理失败:"+e.getMessage());
            throw new ApiClientException("异步通知 出参:处理失败: " + e.getMessage());
        } finally {
            afterDispatch(notifyUrl, notifyData);
        }
    }

    @Override
    public NotifyHandler getNotifyHandler(String serviceKey) {
        return handlers.get(serviceKey);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, NotifyHandler> handlerBeanMaps = applicationContext.getBeansOfType(NotifyHandler.class);
        for (NotifyHandler handler : handlerBeanMaps.values()) {
            handlers.put(handler.serviceKey()+"_"+handler.serviceVersion(), handler);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    protected abstract String getServiceKey(String notifyUrl, Map<String, String> notifyData);

    protected void doDispatch(String serviceKey, ApiMessage notify) {
        try {
            NotifyHandler handler = getNotifyHandler(serviceKey);
            if (handler == null) {
                handler = defaultNotifyHandler;
                logger.info("没有在spring容器找到["+serviceKey+"]的异步通知处理器(NotifyHandler),采用默认的处理器处理。");
            }
            handler.handleNotify(notify);
        } catch (Exception e) {
            throw Exceptions.unchecked(e);
        }
    }

    protected void beforeDispatch(String notifyUrl, Map<String, String> notifyData) {

    }

    protected void afterDispatch(String notifyUrl, Map<String, String> notifyData) {

    }

    public void setApiServiceClient(ApiServiceClient<ApiMessage, ApiMessage, ApiMessage, ApiMessage> apiServiceClient) {
        this.apiServiceClient = apiServiceClient;
    }
}
