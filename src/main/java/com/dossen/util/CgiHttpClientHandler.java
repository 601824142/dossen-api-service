package com.dossen.util;

import com.alibaba.fastjson.JSON;
import com.dossen.config.Configs;
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
    static String version  = Configs.getVersion();

    /**
     * 网关请求协议
     */
    static String scheme  = Configs.getScheme();

    /**
     * 网关HOST
     */
    static String host  = Configs.getHost();

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
//        request.addHeader("Access-Id","VIkmldyo");
//        request.addHeader("Access-Key","YKLhAAYC");
//        request.addHeader("App-Code","DCRY");
        ApiResponse apiResponse = sendSyncRequest(request);
        return new String(apiResponse.getBody());
    }

    /**
     * 模板SQL接口
     * @param url  请求链接
     * @param param  请求参数
     * @return
     */
    public String sendSql(String url, Object param)  {
        ApiRequest request = new ApiRequest( HttpMethod.POST_FORM, url, JSON.toJSONString(param).getBytes(StandardCharsets.UTF_8));
        request.addHeader("x-gate-api-version", version);
        request.addHeader("Content-Type", "application/json;charset=UTF-8");
        request.addHeader("Access-Id","sKCE5cwt");
        request.addHeader("Access-Key","iKoUgo0j");
        request.addHeader("App-Code","DCJ");
        ApiResponse apiResponse = sendSyncRequest(request);
        return new String(apiResponse.getBody());
    }



    /**
     * 模板SQL接口
     * @param url  请求链接
     * @param param  请求参数
     * @return
     */
    public String sendSqlZc(String url, Object param)  {
        ApiRequest request = new ApiRequest( HttpMethod.POST_FORM, url, JSON.toJSONString(param).getBytes(StandardCharsets.UTF_8));
        request.addHeader("x-gate-api-version", version);
        request.addHeader("Content-Type", "application/json;charset=UTF-8");
//        request.addHeader("Access-Id","None");
//        request.addHeader("Access-Key","None");
//        request.addHeader("App-Code","ZC");

        request.addHeader("Access-Id","sKCE5cwt");
        request.addHeader("Access-Key","iKoUgoOj");
        request.addHeader("App-Code","DCJ");
        ApiResponse apiResponse = sendSyncRequest(request);
        return new String(apiResponse.getBody());
    }
}
