/*
 * Copyright 2017 Alibaba Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dossen.constant;

/**
 * @项目名称：Dossen-Cgi-Client
 * @类名称：HttpConstant
 * @类描述：Http协议相关常量
 * @创建人：万星明
 * @创建时间：2020/7/22
 */

public class HttpConstant {

    //请求Header Accept
    public static final String CLOUDAPI_HTTP_HEADER_ACCEPT = "accept";
    //请求Body内容MD5 Header
    public static final String CLOUDAPI_HTTP_HEADER_CONTENT_MD5 = "content-md5";
    //请求Body内容MD5 Header
    public static final String CLOUDAPI_HTTP_HEADER_CA_CONTENT_MD5 = "x-ca-content-md5";
    //请求Header Content-Type
    public static final String CLOUDAPI_HTTP_HEADER_CONTENT_TYPE = "content-type";
    //请求Header Date
    public static final String CLOUDAPI_HTTP_HEADER_DATE = "date";

    //表单类型Content-Type
    public static final String CLOUDAPI_CONTENT_TYPE_FORM = "application/x-www-form-urlencoded; charset=utf-8";
    // 流类型Content-Type
    public static final String CLOUDAPI_CONTENT_TYPE_STREAM = "application/octet-stream; charset=utf-8";
    //JSON类型Content-Type
    public static final String CLOUDAPI_CONTENT_TYPE_JSON = "application/json";
    //文本类型Content-Type
    public static final String CLOUDAPI_CONTENT_TYPE_TEXT = "application/text; charset=utf-8";
}
