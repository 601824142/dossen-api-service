package com.dossen.util;

import com.alibaba.fastjson.JSON;
import com.dossen.config.CgiClientConfig;
import com.dossen.enums.HttpMethod;
import com.dossen.enums.Scheme;
import com.dossen.model.ApiRequest;
import com.dossen.model.ApiResponse;
import com.dossen.model.HttpClientBuilderParams;
import com.dossen.util.client.ApacheHttpClient;

import java.nio.charset.StandardCharsets;

/**
 * @项目名称：Dossen-Cgi-Client
 * @类名称：CgiHttpClient
 * @类描述：CGI 2.0形式接口请求实现类
 * @创建人：万星明
 * @创建时间：2020/7/22
 */

public class CgiHttpClientHandler extends ApacheHttpClient {

    static CgiHttpClientHandler instance = new CgiHttpClientHandler();

    /**
     * 网关版本号
     */
//    static String version  = CgiClientUtil.CGI_VERSION;
    static String version  = CgiClientConfig.CGI_VERSION;
    /**
     * 网关请求协议
     */
//    static String scheme  = CgiClientUtil.CGI_SCHEME;
    static String scheme  = CgiClientConfig.CGI_SCHEME;
    /**
     * 网关HOST
     */
//    static String host  = CgiClientUtil.CGI_HOST;
    static String host  = CgiClientConfig.CGI_HOST;


    public static CgiHttpClientHandler getInstance() {
        return instance;
    }

    @Override
    public void init(HttpClientBuilderParams httpClientBuilderParams){
        httpClientBuilderParams.setScheme( Scheme.getSchemeByName(scheme));
        httpClientBuilderParams.setHost(host);
        super.init(httpClientBuilderParams);
    }

    /**
     * 请求接口
     * @param url  请求链接
     * @param param  请求参数
     * @return
     */
    public String send(String url, Object param)  {
        ApiRequest request = new ApiRequest( HttpMethod.POST_BODY, url, JSON.toJSONString(param).getBytes(StandardCharsets.UTF_8));
        request.addHeader("x-gate-api-version", version);
        request.addHeader("Content-Type", "application/json;charset=UTF-8");
        ApiResponse apiResponse = sendSyncRequest(request);
        return new String(apiResponse.getBody());
    }
}
