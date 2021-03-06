package com.yiji.openapi.sdk.common.utils;

import com.yiji.openapi.sdk.common.ApiConstants;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.*;
import java.util.Map.Entry;

public class Servlets {


    public static void writeResponse(HttpServletResponse response, String data) {
        OutputStream output = null;
        InputStream input = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType(ApiConstants.JSON_UTF_8);
            output = response.getOutputStream();
            input = new ByteArrayInputStream(data.getBytes(Charset.forName("UTF-8")));
            IOUtils.copy(input, output);
            output.flush();
        } catch (Exception e) {
            throw new RuntimeException("响应请求(flushResponse)失败:" + e.getMessage());
        } finally {
            IOUtils.closeQuietly(output);
            IOUtils.closeQuietly(input);
        }
    }

    public static void writeResponse(HttpServletResponse response, String data, String contentType) {
        OutputStream output = null;
        InputStream input = null;
        try {
            response.setCharacterEncoding("UTF-8");
            if (Strings.isBlank(contentType)) {
                response.setContentType(ApiConstants.JSON_UTF_8);
            }
            output = response.getOutputStream();
            input = new ByteArrayInputStream(data.getBytes(Charset.forName("UTF-8")));
            IOUtils.copy(input, output);
            output.flush();
        } catch (Exception e) {
            throw new RuntimeException("响应请求(flushResponse)失败:" + e.getMessage());
        } finally {
            IOUtils.closeQuietly(output);
            IOUtils.closeQuietly(input);
        }
    }

    public static void redirect(HttpServletResponse response, String location) {
        try {
            response.sendRedirect(location);
        } catch (Exception e) {
            throw new RuntimeException("重定向失败,location:" + location + " :", e);
        }
    }

    /**
     * 获取参数map,我们不允许用户传入多个同名参数.
     *
     * @param request
     * @return
     */
    public static Map<String, String> getParameters(ServletRequest request) {
        Validate.notNull(request, "Request must not be null");
        Map<String, String> params = new HashMap<String, String>();
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String[] values = request.getParameterValues(name);
            if (values == null || values.length == 0) {
                continue;
            }
            String value = values[0];
            if (value != null) {
                params.put(name, value);
            }
        }
        return params;
    }

    public static String buildQueryString(Map<String, String> params) {
        if (params == null || params.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<Entry<String, String>> it = params.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, String> entry = it.next();
            if (entry.getValue() != null) {
                sb.append(entry.getKey()).append('=').append(Encodes.urlEncode(entry.getValue()));
            }
            if (it.hasNext()) {
                sb.append('&');
            }
        }
        return sb.toString();
    }

    /**
     * 获取请求的URL路径
     *
     * @param request
     * @return
     */
    public static String getRequestPath(HttpServletRequest request) {
        return Strings.substringAfter(request.getRequestURI(), request.getContextPath());
    }


    /**
     * 取得带相同前缀的Request Parameters, copy from spring WebUtils.
     * <p>
     * 返回的结果的Parameter名已去除前缀.
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, Object> getParametersStartingWith(ServletRequest request, String prefix) {
        Validate.notNull(request, "Request must not be null");
        Enumeration paramNames = request.getParameterNames();
        Map<String, Object> params = new TreeMap<String, Object>();
        if (prefix == null) {
            prefix = "";
        }
        while (paramNames != null && paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            if ("".equals(prefix) || paramName.startsWith(prefix)) {
                String unprefixed = paramName.substring(prefix.length());
                String[] values = request.getParameterValues(paramName);
                if (values == null || values.length == 0) {
                    // Do nothing, no values found at all.
                } else if (values.length > 1) {
                    params.put(unprefixed, values);
                } else {
                    params.put(unprefixed, values[0]);
                }
            }
        }
        return params;
    }

}

