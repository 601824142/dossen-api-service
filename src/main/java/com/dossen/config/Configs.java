package com.dossen.config;

import com.dossen.util.BeanUtils;

/**
 * 配置类
 */
public class Configs {

    public static String getAppId() {
        return BeanUtils.getProperty("dossen.cgi.client.appId");
    }

    public static String getSecret() {
        return BeanUtils.getProperty("dossen.cgi.client.secret");
    }

    public static String getVersion() {
        return BeanUtils.getProperty("dossen.cgi.client.version");
    }

    public static String getScheme() {
        return BeanUtils.getProperty("dossen.cgi.client.scheme");
    }

    public static String getHost() {
        return BeanUtils.getProperty("dossen.cgi.client.host");
    }

}
