package com.dossen.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dossen.config.CgiClientUtil;
import com.dossen.model.BaseRP;
import com.dossen.model.HttpClientBuilderParams;


/**
 * @项目名称：Dossen-Cgi-Client
 * @类名称：CgiHttpClient
 * @类描述：CGI 2.0形式接口请求类
 * @创建人：万星明
 * @创建时间：2020/7/22
 */
public class CgiHttpClient {

    /**
     * OneID平台分配的appID
     */
    static String appId = CgiClientUtil.CGI_APP_ID;

    /**
     * OneID平台分配的应用密钥
     */
    static String appSecret = CgiClientUtil.CGI_APP_SECRET;

    static {
        HttpClientBuilderParams httpParam = new HttpClientBuilderParams();
        httpParam.setAppKey(appId);
        httpParam.setAppSecret(appSecret);
        CgiHttpClientHandler.getInstance().init(httpParam);
    }

    /**
     * 请求接口
     * @param url  请求链接
     * @param param  请求参数
     * @param typeReference  响应类型
     * @param <T> 泛型
     * @return 响应泛型
     */
    public static  <T> BaseRP<T> send(String url,Object param,TypeReference<BaseRP<T>> typeReference) {
        String body = CgiHttpClientHandler.getInstance().send(url, param);
        return JSON.parseObject(body,typeReference );
    }

}
