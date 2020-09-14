package com.dossen.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置类
 */
@Component
public class CgiClientUtil {

    public static String CGI_VERSION;

    public static String CGI_SCHEME;

    public static String CGI_HOST;

    public static String CGI_APP_ID;

    public static String CGI_APP_SECRET;

    public String getCgiVersion() {
        return CGI_VERSION;
    }

    @Value("${dossen.oneid.cgi.version}")
    public void setCgiVersion(String cgiVersion) {
        CGI_VERSION = cgiVersion;
    }

    public String getCgiScheme() {
        return CGI_SCHEME;
    }

    @Value("${dossen.cgi.client.scheme}")
    public void setCgiScheme(String cgiScheme) {
        CGI_SCHEME = cgiScheme;
    }

    public String getCgiHost() {
        return CGI_HOST;
    }

    @Value("${dossen.cgi.client.host}")
    public void setCgiHost(String cgiHost) {
        CGI_HOST = cgiHost;
    }

    public String getCgiAppId() {
        return CGI_APP_ID;
    }

    @Value("${dossen.oneid.appid}")
    public void setCgiAppId(String cgiAppId) {
        CGI_APP_ID = cgiAppId;
    }

    public String getCgiAppSecret() {
        return CGI_APP_SECRET;
    }

    @Value("${dossen.oneid.appsecret}")
    public void setCgiAppSecret(String cgiAppSecret) {
        CGI_APP_SECRET = cgiAppSecret;
    }
}
