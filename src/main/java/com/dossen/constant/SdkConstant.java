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

import java.nio.charset.Charset;

/**
* @项目名称：Dossen-Cgi-Client
* @类名称：SdkConstant
* @类描述：SDK相关常量
* @创建人：万星明
* @创建时间：2020/7/22
*/

public class SdkConstant {

    /**
     * 签名Header
     */
   public static final String CLOUDAPI_X_CA_SIGNATURE = "x-ca-signature";

    /**
     * 所有参与签名的Header
     */
   public static final String CLOUDAPI_X_CA_SIGNATURE_HEADERS = "x-ca-signature-headers";

    /**
     * 请求时间戳
     */
   public static final String CLOUDAPI_X_CA_TIMESTAMP = "x-ca-timestamp";

    /**
     * 请求放重放Nonce,15分钟内保持唯一,建议使用UUID
     */
   public static final String CLOUDAPI_X_CA_NONCE = "x-ca-nonce";

    /**
     * APP KEY
     */
   public static final String CLOUDAPI_X_CA_KEY = "x-ca-key";

    /**
     * APP KEY
     */
   public static final String CLOUDAPI_X_CA_ERROR_MESSAGE = "x-ca-error-message";
   //编码UTF-8
   public static final Charset CLOUDAPI_ENCODING = Charset.forName("UTF-8");
   //Header头的编码
   public static final Charset CLOUDAPI_HEADER_ENCODING = Charset.forName("ISO-8859-1");
   //签名算法
   public static final String CLOUDAPI_X_CA_SIGNATURE_METHOD = "X-Ca-Signature-Method";
   //换行符
   public static final String CLOUDAPI_LF = "\n";
   //参与签名的系统Header前缀,只有指定前缀的Header才会参与到签名中
   public static final String CLOUDAPI_CA_HEADER_TO_SIGN_PREFIX_SYSTEM = "x-ca-";
}
