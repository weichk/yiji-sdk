package com.yiji.openapi.sdk;

import com.yiji.openapi.sdk.common.utils.ConfigurableConstants;
import com.yiji.openapi.sdk.support.ApiClientUtils;

/**
 * 客户端配置参数
 *
 * @author zhike@yiji.com
 * @date 2014年6月17日
 */
public class ApiSdkConstants extends ConfigurableConstants {
    static {
        initWithProfile("openapi-sdk.properties");
    }
    //商户id
    public static final String PARTNERID = getProperty("yijipay.partnerId", "");
    //商户密钥
    public static final String SECRETKEY = getProperty("yijipay.secretKey", "");
    //服务请求地址
    public static final String SERVICE_GATEWAY = getProperty("yijipay.service.gateway", "http://stest.yiji:8630/gateway.html");
    //message包扫描地址
    public static final String SERVICE_MESSAGES_PACKAGE = getProperty("service.message.package", "com.yiji.openapi.message");
    //服务请求超时配置
    public static final int HTTP_CONNECT_TIMEOUT = Integer.parseInt(getProperty("yijipay.http.connect.timeout", "60"));
    //服务响应超时配置
    public static final int HTTP_READ_TIMEOUT = Integer.parseInt(getProperty("yijipay.http.read.timeout", "60"));
    //证书私钥读取路径
	public static final String RSA_PFX_URL = getProperty("yijipay.rsa.pfx.url", "");
	//证书私钥密码
	public static final String RSA_PFX_PASSWORD = getProperty("yijipay.pfx.password", "");
	//证书公钥路径
	public static final String RSA_CER_URL = getProperty("yijipay.rsa.cer.url", "classpath:keystore/yiji.snet.cer");
	//商户系统访问域名配置
    public static final String SITE_DOMAIN = getProperty("yijipay.site.domain", "");
    //异步通知notify_url配置
    public static final String NOTIFY_URL = ApiClientUtils.getCanonicalUrl(SITE_DOMAIN, getProperty("yijipay.notify.url", "/openapi/sdk/notify/"));

    //支付宝APP请求网关地址
    public static final String ALIPAY_PARTNERID_APP = getProperty("alipay.partnerId_app", "2015112400864012");
    //支付宝APP请求网关地址
    public static final String ALIPAY_URL_APP = getProperty("alipay.url_app", "https://openapi.alipay.com/gateway.do");
    //支付宝APP公钥
    public static final String ALIPAY_PUBLIC_KEY_APP = getProperty("alipay.public.key_app", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB");
    //支付宝APP私钥
    public static final String ALIPAY_PRIVATE_KEY_APP = getProperty("alipay.private.key_app", "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANRj2iWngamGpMVGjAcOBxP9KPwJECpqwgffHnyFB/sxYlApzy/EOjWD8CpdvArUgKEYcEUGAT9BcfcF/RFaOSBzvNk6BTpFOT+fSizN8C3svUeLHWOIRbPIkkt3PXy2y6S0J1Fn62tAoMghgNH+CwH8dzkhR6xos7jAVC1X4YcpAgMBAAECgYBKSb7Eyuk8bZ72ba2OziZFpJgQR+9CluLVNAKK1VEsWGKl3WndsEoX69U2TGD4fMmdD1o9mlMXWAHPwGV7C07NWkH7VuUyDutumJ3n0CQO5TRQzY9PjKnUQaZxNvuF6E6qpu+gb/X6wvhkyznc2WI0n8hBkByTakrZB8/4RtwgMQJBAP9pY+N+v4xTLD4nHHJz8Cw/nSWJ+8a5xHFiZIFvtpqe/+5/gzL9SUDQmKFeU1jpJ+i5pfXzoZ/vES+zj/wZPY0CQQDU4RfagCuKhkLdTm9W1GwEbCHN9c3DYpTUyKg8p8g73L0CvYiuNUZidPVPKuzyZtuVvD33QE19uohr88fMU8MNAkEAzM5t0taoMpXfPFeQcBaX2GOKV7e1tTzxURpdDY3PF124TD7jrdd9DgQ23+8LVphf8DYt5nNefyjs1xtdsgFrYQJAbPY+KGgcYU4xoWdJVA9srqLOdsaPKKgn9/YxLsLOmd11nZZ4BHteaHznJzuYhsahNLZinVD7mRRGScNBDFP/bQJAGv2nUYM0dPhAF+YLRg7kayw3I6TB2A6Y2FDWIX6IjFHiZI46+oG0aR5KsunChe/Y2QGUvpgAsigXbkxrTed67Q==");

    //支付宝H5web请求网关地址
    public static final String ALIPAY_PARTNERID_H5WEB = getProperty("alipay.partnerId_h5web", "2016063001566409");
    //支付宝H5web请求网关地址aaz
    public static final String ALIPAY_URL_H5WEB = getProperty("alipay.url_h5web", "https://openapi.alipay.com/gateway.do");
    //支付宝H5web公钥
    public static final String ALIPAY_PUBLIC_KEY_H5WEB = getProperty("alipay.public.key_h5web", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB");
    //支付宝H5web私钥
    public static final String ALIPAY_PRIVATE_KEY_H5WEB = getProperty("alipay.private.key_h5web", "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKhEY4aQMPgYx2wJw8u1GElAlgGmAXA03Mkn/LfFJmHvH5rYaY0cbBla7OBugPYaWe/5TYRF4d9GYNUEv+NTRne1XNWx8kFI6ssemqX9vdzYPM3T7Yr0e/mvli+rDdDb+rCP1tpob7FbPIowCkxfxQM2l4gReOUIdYSqV5+KOFolAgMBAAECgYEAnasfWvqHeCG302s+9QUm0ZTcOoUzudE+xJpcelIYyhGHms+H7/qe7A9AAHhrDK/acDs5QGU+5bkLiO8fYsIoNwQxnEPjxx50pU2p6wzoSXm+A2bED+/OO5SBTwvWot5KOxuo1uNcKwgTEtTCWWCuI+6zp3MZbD1KYd+QQcFULuECQQDVsiBzI/eDDEB+ipcWCngDvY95s9AS5D0WVWLD6jLn3va+aj03QhatlSbnV60q8W+P3GlOX24/+Os4Lhs3CslJAkEAyZP8fM7IwtKqaj0i5jPoOKMfojoAkSJ/LVUczhpSZilJAvhnMoZAYKohmw8zjUovHi4wqfEEZcA/4EAhkpkF/QJBALOLGLaE1pV1es6W9MAhBZrm7qArUrw8pZKZJQVERYcJjziHHG3MCi/HrEsdIDJ/beNqJdQiXAhFxKOMIXOKjXkCQAPFWvHSxHqwijOWlTKNQgjdNFhKkXwlkw98Ch/o6iWzHASMNRIC5O1qAVrvO92mx57e6lYjhhuJLyi4ymf9qNkCQHbeE1C/XnP7h8pPD2kiLqwGkaEgY4v8w7GftKoov9AUAVL1O6qyfuaCsIe8rvcCgHavggxU7IXeDI1whgxzYSw=");

    //支付宝PC请求网关地址
    public static final String ALIPAY_PARTNERID_PC = getProperty("alipay.partnerId_pc", "2088021604845422");
    //支付宝PC请求网关地址
    public static final String ALIPAY_URL_PC = getProperty("alipay.url_pc", "https://mapi.alipay.com/gateway.do");
    //支付宝PC公钥
    public static final String ALIPAY_PUBLIC_KEY_PC = getProperty("alipay.public.key_pc", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB");
    //支付宝PC私钥
    public static final String ALIPAY_PRIVATE_KEY_PC = getProperty("alipay.private.key_pc", "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANRj2iWngamGpMVGjAcOBxP9KPwJECpqwgffHnyFB/sxYlApzy/EOjWD8CpdvArUgKEYcEUGAT9BcfcF/RFaOSBzvNk6BTpFOT+fSizN8C3svUeLHWOIRbPIkkt3PXy2y6S0J1Fn62tAoMghgNH+CwH8dzkhR6xos7jAVC1X4YcpAgMBAAECgYBKSb7Eyuk8bZ72ba2OziZFpJgQR+9CluLVNAKK1VEsWGKl3WndsEoX69U2TGD4fMmdD1o9mlMXWAHPwGV7C07NWkH7kByTakrZB8/4RtwgMQJBAP9pY+N+v4xTLD4nHHJz8Cw/nSWJ+8a5xHFiZIFvtpqe/+5/gzL9SUDQmKFeU1jpJ+i5pfXzoZ/vES+zj/wZPY0CQQDU4RfagCuKhkLdTm9W1GwEbCHN9c3DYpTUyKg8p8g73L0CvYiuNUZidPVPKuzyZtuVvD33QE19uohr88fMU8MNAkEAzM5t0taoMpXfPFeQcBaX2GOKV7e1tTzxURpdDY3PF124TD7jrdd9DgQ23+8LVphf8DYt5nNefyjs1xtdsgFrYQJAbPY+KGgcYU4xoWdJVA9srqLOdsaPKKgn9/YxLsLOmd11nZZ4BHteaHznJzuYhsahNLZinVD7mRRGScNBDFP/bQJAGv2nUYM0dPhAF+YLRg7kayw3I6TB2A6Y2FDWIX6IjFHiZI46+oG0aR5KsunChe/Y2QGUvpgAsigXbkxrTed67Q==");
}
