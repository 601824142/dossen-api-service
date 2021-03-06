package com.dossen.model;


import com.dossen.enums.Scheme;
import com.dossen.exception.SdkException;

public class BaseClientInitialParam {
    String appKey;
    String appSecret;
    String host;
    Scheme scheme;
    long connectionTimeout = 10000L;
    long readTimeout = 10000L;
    long writeTimeout = 10000L;

    public void check() {
        if (isEmpty(appKey) || isEmpty(appKey)) {
            throw new SdkException("app key or app secret must be initialed");
        }
    }

    protected boolean isEmpty(String str) {
        return str == null || str.equals("");
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public long getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(long connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public long getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(long readTimeout) {
        this.readTimeout = readTimeout;
    }

    public long getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(long writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Scheme getScheme() {
        return scheme;
    }

    public void setScheme(Scheme scheme) {
        this.scheme = scheme;
    }
}
