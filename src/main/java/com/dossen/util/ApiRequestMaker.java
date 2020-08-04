package com.dossen.util;


import com.dossen.constant.HttpConstant;
import com.dossen.constant.SdkConstant;
import com.dossen.model.ApiRequest;

import java.text.SimpleDateFormat;
import java.util.*;

public class ApiRequestMaker {
    public static void make(ApiRequest request,String appKey,String appSecret) {


        /**
         * 将pathParams中的value替换掉path中的动态参数
         * 比如 path=/v2/getUserInfo/[userId]，pathParams 字典中包含 key:userId , value:10000003
         * 替换后path会变成/v2/getUserInfo/10000003
         */
        request.setPath(combinePathParam(request.getPath(), request.getPathParams()));

        /**
         *  拼接URL
         *  HTTP + HOST + PATH(With pathparameter) + Query Parameter
         */
        StringBuilder url = new StringBuilder().append(request.getScheme().getValue()).append(request.getHost()).append(request.getPath());

        if (null != request.getQuerys() && request.getQuerys().size() > 0) {
            url.append("?").append( HttpCommonUtil.buildParamString(request.getQuerys()));
        }

        request.setUrl(url.toString());

        Date current = request.getCurrentDate() == null ? new Date() : request.getCurrentDate();
        //设置请求头中的时间戳
        if (null == request.getFirstHeaderValue( HttpConstant.CLOUDAPI_HTTP_HEADER_DATE)) {
            request.addHeader( HttpConstant.CLOUDAPI_HTTP_HEADER_DATE, getHttpDateHeaderValue(current));
        }

        //设置请求头中的时间戳，以timeIntervalSince1970的形式
        request.addHeader( SdkConstant.CLOUDAPI_X_CA_TIMESTAMP, String.valueOf(current.getTime()));

        //请求放重放Nonce,15分钟内保持唯一,建议使用UUID
        if (null == request.getFirstHeaderValue( SdkConstant.CLOUDAPI_X_CA_NONCE)) {
            //TODO 将UUID中间的-去除
            request.addHeader( SdkConstant.CLOUDAPI_X_CA_NONCE, UUID.randomUUID().toString().replace("-",""));
        }

        //设置请求头中的Api绑定的的AppKey
        if (request.isNeedSignature()) {
            request.addHeader( SdkConstant.CLOUDAPI_X_CA_KEY, appKey);
        }

        //设置应答数据类型
        if (null == request.getFirstHeaderValue( HttpConstant.CLOUDAPI_HTTP_HEADER_ACCEPT)) {
            request.addHeader( HttpConstant.CLOUDAPI_HTTP_HEADER_ACCEPT, request.getMethod().getAcceptContentType());
        }

        if (request.isNeedSignature() && !HttpCommonUtil.isEmpty(request.getSignatureMethod())) {
            request.addHeader( SdkConstant.CLOUDAPI_X_CA_SIGNATURE_METHOD, request.getSignatureMethod());
        }

        /**
         *  如果formParams不为空
         *  将Form中的内容拼接成字符串后使用UTF8编码序列化成Byte数组后加入到Request中去
         */
        if (null != request.getBody() && request.getBody().length > 0 && null == request.getFirstHeaderValue( HttpConstant.CLOUDAPI_HTTP_HEADER_CONTENT_MD5)) {
            request.addHeader( HttpConstant.CLOUDAPI_HTTP_HEADER_CONTENT_MD5, SignUtil.base64AndMD5(request.getBody()));
        }

        /**
         *  将Request中的httpMethod、headers、path、queryParam、formParam合成一个字符串用hmacSha256算法双向加密进行签名
         *  签名内容放到Http头中,用作服务器校验
         */
        if (request.isNeedSignature()) {
            String signature = SignUtil.sign(request, appSecret);
            request.addHeader( SdkConstant.CLOUDAPI_X_CA_SIGNATURE, signature);
        }

    }


    private static String combinePathParam(String path, Map<String, String> pathParams) {
        if (pathParams == null) {
            return path;
        }

        for (String key : pathParams.keySet()) {
            path = path.replace("[" + key + "]", pathParams.get(key));
        }
        return path;
    }


    private static String getHttpDateHeaderValue(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(date);
    }


}
