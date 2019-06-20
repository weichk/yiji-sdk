/*
 * www.yiji.com Inc.
 * Copyright (c) 2016 All Rights Reserved
 */

/*
 * 修订记录:
 * zhike@yiji.com 2016-07-29 16:02 创建
 *
 */
package com.yiji.openapi.sdk.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author zhike@yiji.com
 */
public class IOUtils {
    private static final Log logger = LogFactory.getLog(IOUtils.class);
    /**
     * 将一个输入流转化为字符串
     */
    public static String toString(InputStream tInputStream){
        if (tInputStream != null){
            try{
                BufferedReader tBufferedReader = new BufferedReader(new InputStreamReader(tInputStream));
                StringBuffer tStringBuffer = new StringBuffer();
                String sTempOneLine = new String("");
                while ((sTempOneLine = tBufferedReader.readLine()) != null){
                    tStringBuffer.append(sTempOneLine);
                }
                return tStringBuffer.toString();
            }catch (Exception ex){
                logger.error("输入流转化字符串失败",ex);
            }
        }
        return null;
    }
}
