/**
 * create by zhike@yiji.com
 * date:2015年4月11日
 */
package com.yiji.openapi.sdk;

import com.yiji.openapi.sdk.common.message.ApiMessage;
import com.yiji.openapi.sdk.common.utils.HttpResult;
import com.yiji.openapi.sdk.common.utils.Https;
import com.yiji.openapi.sdk.exception.ApiClientException;
import com.yiji.openapi.sdk.exception.ApiClientNetworkException;
import com.yiji.openapi.sdk.exception.ApiServerException;
import com.yiji.openapi.sdk.marshall.ApiSdkMarshal;
import com.yiji.openapi.sdk.marshall.ApiSdkUnmarshal;
import com.yiji.openapi.sdk.marshall.impl.ApiSdkSignMarshal;
import com.yiji.openapi.sdk.transport.Transport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * API SDK 执行器 抽象实现
 *
 * @author zhike@yiji.com
 */
public abstract class AbstractApiServiceClient<R extends ApiMessage, T extends ApiMessage, N extends ApiMessage, X extends ApiMessage>
        implements
        ApiServiceClient<R, T, N, X> {

    private static final Log logger = LogFactory.getLog(AbstractApiServiceClient.class);

    public T execute(R request) {
        try {
            beforeExecute(request);
            Map<String, String> requestMessage = getRequestMarshal().marshal(request);
            logger.info("请求:" + requestMessage);
            String responseMessage = getTransport().exchange(requestMessage);
            logger.info("响应:" + responseMessage);
            T t = getResponseUnmarshal().unmarshal(responseMessage, request.getService()+"_"+request.getVersion());
            afterExecute(t);
            return t;
        } catch (ApiServerException ose) {
            throw ose;
        } catch (ApiClientException oce) {
            throw oce;
        } catch (Exception e) {
            logger.error("内部错误:" + e.getMessage(), e);
            throw new ApiClientException("内部错误:" + e.getMessage());
        }
    }

    public T execute(R request,String secretKey) {
        try {
            beforeExecute(request);
            Map<String, String> requestMessage = getRequestMarshal().marshal(request,secretKey);
            logger.info("请求:" + requestMessage);
            String responseMessage = getTransport().exchange(requestMessage);
            logger.info("响应:" + responseMessage);
            T t = getResponseUnmarshal().unmarshal(responseMessage, request.getService()+"_"+request.getVersion(),secretKey);
            afterExecute(t);
            return t;
        } catch (ApiServerException ose) {
            throw ose;
        } catch (ApiClientException oce) {
            throw oce;
        } catch (Exception e) {
            logger.error("内部错误:" + e.getMessage(), e);
            throw new ApiClientException("内部错误:" + e.getMessage());
        }
    }

    public N notice(Map<String, String> notifyData, String serviceKey) {
        try {
            N notify = getNoticeUnmarshal().unmarshal(notifyData, serviceKey);
            afterNotice(notify);
            return notify;
        } catch (ApiClientException oce) {
            logger.warn("客户端:{}"+oce.getMessage());
            throw oce;
        } catch (Exception e) {
            logger.warn("内部错误:" + e.getMessage(), e);
            throw new ApiClientException("内部错误:" + e.getMessage());
        }
    }

    public N notice(Map<String, String> notifyData, String serviceKey,String secretKey) {
        try {
            N notify = getNoticeUnmarshal().unmarshal(notifyData, serviceKey,secretKey);
            afterNotice(notify);
            return notify;
        } catch (ApiClientException oce) {
            logger.warn("客户端:{}"+oce.getMessage());
            throw oce;
        } catch (Exception e) {
            logger.warn("内部错误:" + e.getMessage(), e);
            throw new ApiClientException("内部错误:" + e.getMessage());
        }
    }

    /**
     * @param notify
     */
    protected void afterNotice(N notify) {

    }

    /**
     * 默认实现为：跳转通知结构与异步通知相同。
     */
    public X result(Map<String, String> notifyData, String secretKey) {
        try {
            return getReturnUnmarshal().unmarshal(notifyData, secretKey);
        } catch (ApiClientException oce) {
            logger.warn("客户端:"+oce.getMessage());
            throw oce;
        } catch (Exception e) {
            logger.warn("内部错误:" + e.getMessage(), e);
            throw new ApiClientException("内部错误:" + e.getMessage());
        }
    }

    public String redirectGet(R request) {
        try {
            String requestMessage = getRedirectMarshal().marshal(request);
            logger.info("跳转请求url["+getRedirectGateway() + "?" + requestMessage+"]");
            return getRedirectGateway() + "?" + requestMessage;
        } catch (ApiServerException ose) {
            logger.warn("服务器错误:" + ose.getMessage(), ose);
            throw ose;
        } catch (ApiClientException oce) {
            logger.warn("客户端异常:" + oce.getMessage(), oce);
            throw oce;
        } catch (Exception e) {
            logger.warn("内部错误:" + e.getMessage(), e);
            throw new ApiClientException("内部错误:" + e.getMessage());
        }
    }

    public String redirectGet(R request,String secretKey) {
        try {
            String requestMessage = getRedirectMarshal().marshal(request,secretKey);
            logger.info("跳转请求url["+getRedirectGateway() + "?" + requestMessage+"]");
            return getRedirectGateway() + "?" + requestMessage;
        } catch (ApiServerException ose) {
            logger.warn("服务器错误:" + ose.getMessage(), ose);
            throw ose;
        } catch (ApiClientException oce) {
            logger.warn("客户端异常:" + oce.getMessage(), oce);
            throw oce;
        } catch (Exception e) {
            logger.warn("内部错误:" + e.getMessage(), e);
            throw new ApiClientException("内部错误:" + e.getMessage());
        }
    }

    public String post(String gateway, Map<String, String> requestMap) {
        try {
            HttpResult result = Https.getInstance().post(gateway, requestMap);
            if (!String.valueOf(result.getStatus()).startsWith("2")) {
                throw new ApiServerException("status:" + result.getStatus() + ", body:" + result.getBody());
            }
            return result.getBody();
        } catch (ApiServerException ase) {
            throw ase;
        } catch (Exception e) {
            throw new ApiClientNetworkException("通讯错误：" + e.getMessage());
        }
    }

    /**
     * 签名
     *
     * @param object
     * @return
     */
    public String sign(Object object) {
        return getSdkSignMarshal().sign(object);
    }

    public String sign(Object object,String secretKey) {
        return getSdkSignMarshal().sign(object,secretKey);
    }
    /**
     * 签名
     *
     * @param parameters
     * @return
     */
    public String sign(List<String> parameters) {
        return getSdkSignMarshal().sign(parameters);
    }

    public String sign(List<String> parameters,String secretKey) {
        return getSdkSignMarshal().sign(parameters,secretKey);
    }

    /**
     * 签名
     *
     * @param waitSignMap
     * @return
     */
    public String sign(Map<String, String> waitSignMap) {
        return getSdkSignMarshal().sign(waitSignMap);
    }

    public String sign(Map<String, String> waitSignMap,String secretKey) {
        return getSdkSignMarshal().sign(waitSignMap,secretKey);
    }

    /**
     * 签名
     *
     * @param waitSignStr
     * @return
     */
    public String sign(String waitSignStr) {

        return getSdkSignMarshal().sign(waitSignStr);
    }

    public String sign(String waitSignStr,String secretKey) {
        return getSdkSignMarshal().sign(waitSignStr,secretKey);
    }

    /**
     * 验签
     *
     * @param responseData
     * @return
     */
    public boolean verySign(String responseData) {
        return getSdkSignMarshal().verySign(responseData);
    }

    public boolean verySign(String responseData,String secretKey) {
        return getSdkSignMarshal().verySign(responseData,secretKey);
    }
    /**
     * 加密数据
     *
     * @param plainText
     * @return
     * @throws UnsupportedEncodingException
     */
    public String encrypt(String plainText, String signType) {
        getSdkSignMarshal();
        return ApiSdkSignMarshal.encrypt(plainText, signType);
    }

    public String encrypt(String plainText, String signType,String secretKey) {
        getSdkSignMarshal();
        return ApiSdkSignMarshal.encrypt(plainText, signType,secretKey);
    }

    /**
     * 解密数据
     *
     * @param plainText
     * @return
     * @throws UnsupportedEncodingException
     */
    public String decrypt(String plainText, String signType) {
        getSdkSignMarshal();
        return ApiSdkSignMarshal.decrypt(plainText, signType);
    }

    public String decrypt(String plainText, String signType,String secretKey) {
        getSdkSignMarshal();
        return ApiSdkSignMarshal.decrypt(plainText, signType,secretKey);
    }
    /**
     * 同步请求前置处理
     *
     * @param request
     */
    protected void beforeExecute(R request) {

    }

    /**
     * 同步请求后置处理
     *
     * @param response
     */
    protected void afterExecute(T response) {

    }

    /**
     * 请求报文marshal
     *
     * @return
     */
    protected abstract ApiSdkMarshal<Map<String, String>, R> getRequestMarshal();

    /**
     * 响应报文unmarshal
     *
     * @return
     */
    protected abstract ApiSdkUnmarshal<T, String> getResponseUnmarshal();

    /**
     * 跳转请求marshal
     *
     * @return
     */
    protected abstract ApiSdkMarshal<String, R> getRedirectMarshal();

    protected abstract ApiSdkUnmarshal<N, Map<String, String>> getNoticeUnmarshal();

    protected abstract ApiSdkUnmarshal<X, Map<String, String>> getReturnUnmarshal();

    protected abstract ApiSdkSignMarshal getSdkSignMarshal();

    protected String getRedirectGateway() {
        return ApiSdkConstants.SERVICE_GATEWAY;
    }

    /**
     * 通讯组件
     *
     * @return
     */
    protected abstract Transport getTransport();
}
