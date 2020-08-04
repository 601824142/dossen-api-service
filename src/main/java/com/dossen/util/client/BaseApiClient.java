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

package com.dossen.util.client;


import com.dossen.enums.Scheme;
import com.dossen.exception.SdkException;
import com.dossen.model.ApiCallback;
import com.dossen.model.ApiRequest;
import com.dossen.model.ApiResponse;

/**
 * apiClient基类
 */
public abstract class BaseApiClient {

    String appKey;
    String appSecret;
    Scheme scheme;
    String host;
    boolean isInit = false;

    protected void checkIsInit() {
        if (!isInit) {
            throw new SdkException("MUST initial client before using");
        }
    }


    protected abstract ApiResponse sendSyncRequest(ApiRequest apiRequest);

    protected abstract void sendAsyncRequest(final ApiRequest apiRequest,final ApiCallback apiCallback);


}
