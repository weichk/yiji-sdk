package com.yiji.openapi.sdk.transport;

import com.yiji.openapi.sdk.ApiSdkConstants;
import com.yiji.openapi.sdk.common.utils.HttpResult;
import com.yiji.openapi.sdk.common.utils.Https;
import com.yiji.openapi.sdk.exception.ApiClientNetworkException;
import com.yiji.openapi.sdk.exception.ApiServerException;

import java.util.Map;

/**
 * 通讯组件
 * <p/>
 * 自动根据请求的schema判断是http还是https（采用全部信任服务端方式）
 *
 * @author zhike@yiji.com
 */
public class HttpTransport implements Transport {

    private String gateway = ApiSdkConstants.SERVICE_GATEWAY;
    private int connectTimeout = ApiSdkConstants.HTTP_CONNECT_TIMEOUT;
    private int readTimeout = ApiSdkConstants.HTTP_READ_TIMEOUT;

    public String exchange(Map<String, String> request) {
        try {
            HttpResult result = Https.getInstance().connectTimeout(connectTimeout).readTimeout(readTimeout).post(gateway, request);
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
     * @param request
     * @return
     * @see com.yiji.openapi.sdk.transport.Transport#redirectExchange(Map)
     */
    public String redirectExchange(Map<String, String> request) {
        try {
            HttpResult result = Https.getInstance().post(gateway, request,true);
            if (!String.valueOf(result.getStatus()).startsWith("302")) {
                throw new ApiServerException("status:" + result.getStatus() + ", body:" + result.getBody());
            }
            return result.getRedirectURL();
        } catch (ApiServerException ase) {
            throw ase;
        } catch (Exception e) {
            throw new ApiClientNetworkException("通讯错误：" + e.getMessage());
        }

    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

}
