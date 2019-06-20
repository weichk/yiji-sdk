/*
 * www.yiji.com Inc.
 * Copyright (c) 2016 All Rights Reserved
 */

/*
 * 修订记录:
 * kuli@yiji.com 2016-06-14 00:59 创建
 */
package com.yiji.openapi.sdk;

import com.yiji.openapi.sdk.common.ApiConstants;
import com.yiji.openapi.sdk.common.message.ApiNotify;
import com.yiji.openapi.sdk.common.message.ApiRequest;
import com.yiji.openapi.sdk.common.message.ApiResponse;
import com.yiji.openapi.sdk.common.utils.Servlets;
import org.hibernate.validator.constraints.NotBlank;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * OpenApi SDK 客户端工具
 *(针对客户端业务场景一个系统可能会用到多个商户ID,故一下方法都重载了一个需要传入商户密钥，调用此方法的时候也需要手动
 * 将商户ID传入到入参中去，否者获取的将还会是配置文件配置的partnerId.针对一个商户系统用到多个partnerID的情况对手动
 * 传入商户ID和商户密钥的接口将不支持异步通知集成，因为验签无法通过，不过下面通过了异步实体转化的方法可以使用)
 * @author acooly
 */
public class YijiPayClient {

    private HttpApiServiceClient apiServiceClient;

    /**
     * 同步请求
     *
     * @param request 请求报文对象
     * @param <T>     对象的响应报文声明类型
     * @return 响应报文对象
     */
    public <T extends ApiResponse> T request(ApiRequest request) {
        return (T) apiServiceClient.execute(request);
    }

    /**
     * 支持商户手动给request的partnerId赋值并传入响应的商户密钥
     *(注：调用此方法必须手动set请求对象request中的partnerId)
     * @param request
     * @param secretKey
     * @param <T>
     * @return
     */
    public <T extends ApiResponse> T request(ApiRequest request, String secretKey) {
        return (T) apiServiceClient.execute(request, secretKey);
    }

    /**
     * 跳转接口URL
     * <p/>
     * 自动完成跳转接口请求前的参数组装和签名,直接返回请求的URL,调用者可请直接使用redirect方式请求到服务网关.
     * <b>GET模式: 所有请求的参数都采用queryString的方式传递,如果请求报文过长,请选择使用POST模式<b/>
     *
     * @param request 请求报文
     * @return
     */
    public String redirect(ApiRequest request) {
        return apiServiceClient.redirectGet(request);
    }

    /**
     * 跳转接口URL
     * 支持商户手动给request的partnerId赋值并传入响应的商户密钥
     *(注：调用此方法必须手动set请求对象request中的partnerId)
     * @param request
     * @param secretKey
     * @return
     */
    public String redirect(ApiRequest request, String secretKey) {
        return apiServiceClient.redirectGet(request, secretKey);
    }

    /**
     * 跳转接口直接跳转
     *
     * @param request
     * @param httpResponse
     */
    public void redirect(ApiRequest request, HttpServletResponse httpResponse) {
        Servlets.redirect(httpResponse, apiServiceClient.redirectGet(request));
    }

    /**
     * 支持商户手动给request的partnerId赋值并传入响应的商户密钥
     *(注：调用此方法必须手动set请求对象request中的partnerId)
     * @param request
     * @param httpResponse
     * @param secretKey
     */
    public void redirect(ApiRequest request, HttpServletResponse httpResponse, String secretKey) {
        Servlets.redirect(httpResponse, apiServiceClient.redirectGet(request, secretKey));
    }

    /**
     * 通知报文解析
     * <p/>
     * 注意: 该方法兼容异步通知(notifyUrl)和同步通知(returnUrl)
     *
     * @param notifyData 通知的所有参数(request.getParameters())
     * @param <T>        需要强制转型的类型声明, 如果不清楚这可声明为ApiNotify
     * @return 通知的对象
     */
    public <T extends ApiNotify> T notice(Map<String, String> notifyData) {
        String serviceName = notifyData.get(ApiConstants.SERVICE) + "_" + notifyData.get(ApiConstants.VERSION);
        return (T) apiServiceClient.notice(notifyData, serviceName);
    }

    /**
     * 通知报文解析
     * <p/>
     * 注意: 该方法兼容异步通知(notifyUrl)和同步通知(returnUrl),调用此方法需要手动传入当前partnerId对应的商户密钥
     *
     * @param notifyData 通知的所有参数(request.getParameters())
     * @param <T>        需要强制转型的类型声明, 如果不清楚这可声明为ApiNotify
     * @return 通知的对象
     */
    public <T extends ApiNotify> T notice(Map<String, String> notifyData,String secretKey) {
        String serviceName = notifyData.get(ApiConstants.SERVICE) + "_" + notifyData.get(ApiConstants.VERSION);
        return (T) apiServiceClient.notice(notifyData, serviceName,secretKey);
    }

    /**
     * 通知报文解析
     * <p/>
     * 直接从标准HttpServletRequest中获取通知参数,然后解析,验签,组装为ApiNotify对象
     * <p/>
     * 注意: 该方法兼容异步通知(notifyUrl)和同步通知(returnUrl)
     *
     * @param request 标准HttpServletReqeust
     * @param <T>
     * @return 通知的对象
     */
    public <T extends ApiNotify> T notify(HttpServletRequest request) {
        Map<String, String> noticeData = Servlets.getParameters(request);
        return notice(noticeData);
    }

    /**
     * 通知报文解析
     * <p/>
     * 直接从标准HttpServletRequest中获取通知参数,然后解析,验签,组装为ApiNotify对象
     * <p/>
     * 注意: 该方法兼容异步通知(notifyUrl)和同步通知(returnUrl),调用此方法需要手动传入当前partnerId对应的商户密钥
     *
     * @param request 标准HttpServletReqeust
     * @param <T>
     * @return 通知的对象
     */
    public <T extends ApiNotify> T notify(HttpServletRequest request,String secretKey) {
        Map<String, String> noticeData = Servlets.getParameters(request);
        return notice(noticeData,secretKey);
    }
    /**
     * post请求
     *
     * @param gateway
     * @param requestMap
     * @return
     */
    public String post(@NotBlank String gateway, Map<String, String> requestMap) {
        return apiServiceClient.post(gateway, requestMap);
    }

    /**
     * 签名
     *
     * @param object
     * @return
     */
    public String sign(Object object) {
        return apiServiceClient.sign(object);
    }

    /**
     * 签名(对object中的signType=MD5使用传入的商户密钥)
     * @param object
     * @param secretKey
     * @return
     */
    public String sign(Object object, String secretKey) {
        return apiServiceClient.sign(object,secretKey);
    }

    /**
     * 签名
     *
     * @param parameters
     * @return
     */
    public String sign(List<String> parameters) {
        return apiServiceClient.sign(parameters);
    }

    /**
     * 签名(对parameters中的signType=MD5使用传入的商户密钥)
     * @param parameters
     * @param secretKey
     * @return
     */
    public String sign(List<String> parameters, String secretKey) {
        return apiServiceClient.sign(parameters,secretKey);
    }

    /**
     * 签名
     *
     * @param waitSignMap
     * @return
     * @par
     */
    public String sign(Map<String, String> waitSignMap) {
        return apiServiceClient.sign(waitSignMap);
    }

    /**
     * 签名(对waitSignMap中的signType=MD5使用传入的商户密钥)
     * @param waitSignMap
     * @param secretKey
     * @return
     */
    public String sign(Map<String, String> waitSignMap, String secretKey) {
        return apiServiceClient.sign(waitSignMap,secretKey);
    }

    /**
     * 签名
     *
     * @param waitSignStr
     * @return
     */
    public String sign(String waitSignStr) {
        return apiServiceClient.sign(waitSignStr);
    }

    /**
     * 签名(对waitSignStr中的signType=MD5使用传入的商户密钥)
     * @param waitSignStr
     * @param secretKey
     * @return
     */
    public String sign(String waitSignStr, String secretKey) {
        return apiServiceClient.sign(waitSignStr,secretKey);
    }

    /**
     * 验签
     *
     * @param responseData
     * @return
     */
    public boolean verySign(String responseData) {
        return apiServiceClient.verySign(responseData);
    }

    /**
     * 验签(对responseData中的signType=MD5使用传入的商户密钥)
     * @param responseData
     * @param secretKey
     * @return
     */
    public boolean verySign(String responseData, String secretKey) {
        return apiServiceClient.verySign(responseData,secretKey);
    }

    /**
     * 加密数据(使用配置文件配置的商户密钥)
     *
     * @param plainText
     * @return
     * @throws UnsupportedEncodingException
     */
    public String encrypt(String plainText, String signType) {
        return apiServiceClient.encrypt(plainText, signType);
    }

    /**
     * 加密数据(对signType=MD5使用传入的商户密钥)
     * @param plainText
     * @param signType
     * @param secretKey
     * @return
     */
    public String encrypt(String plainText, String signType, String secretKey) {
        return apiServiceClient.encrypt(plainText, signType,secretKey);
    }
    /**
     * 解密数据(使用配置文件配置的商户密钥)
     *
     * @param plainText
     * @return
     * @throws UnsupportedEncodingException
     */
    public String decrypt(String plainText, String signType) {
        return apiServiceClient.decrypt(plainText, signType);
    }

    /**
     * 解密数据(对signType=MD5使用传入的商户密钥)
     * @param plainText
     * @param signType
     * @param secretKey
     * @return
     */
    public String decrypt(String plainText, String signType, String secretKey) {
        return apiServiceClient.decrypt(plainText, signType,secretKey);
    }

    public HttpApiServiceClient getApiServiceClient() {
        return this.apiServiceClient;
    }

    public void setApiServiceClient(HttpApiServiceClient apiServiceClient) {
        this.apiServiceClient = apiServiceClient;
    }

}
