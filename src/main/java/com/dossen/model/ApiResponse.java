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

package com.dossen.model;


import com.alibaba.fastjson.JSONObject;
import com.dossen.constant.HttpConstant;
import com.dossen.constant.SdkConstant;

/**
 * api同步调用应答类
 */
public final class ApiResponse extends ApiHttpMessage {

    int code;
    String message;
    String contentType;
    Exception ex;

    public ApiResponse(int code) {
        this.code = code;
    }

    public ApiResponse(int errorCode, String message, Exception ex) {
        this.code = errorCode;
        this.message = message;
        this.ex = ex;
    }

    public ApiResponse(JSONObject jsonObject) {
        this.parse(jsonObject);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Exception getEx() {
        return ex;
    }

    public void setEx(Exception ex) {
        this.ex = ex;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public void parse(JSONObject message) {
        super.parse(message);
        this.code = Integer.parseInt(message.get("status").toString());
        this.contentType = getFirstHeaderValue( HttpConstant.CLOUDAPI_HTTP_HEADER_CONTENT_TYPE);
        if (null != this.getFirstHeaderValue( SdkConstant.CLOUDAPI_X_CA_ERROR_MESSAGE)) {
            this.message = this.getFirstHeaderValue( SdkConstant.CLOUDAPI_X_CA_ERROR_MESSAGE);
        }
    }
}
