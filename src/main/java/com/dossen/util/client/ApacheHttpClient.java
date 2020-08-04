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


import com.dossen.constant.HttpConstant;
import com.dossen.enums.HttpConnectionModel;
import com.dossen.exception.SdkException;
import com.dossen.model.ApiCallback;
import com.dossen.model.ApiRequest;
import com.dossen.model.ApiResponse;
import com.dossen.model.HttpClientBuilderParams;
import com.dossen.util.ApiRequestMaker;
import com.dossen.util.HttpCommonUtil;
import com.dossen.util.SignUtil;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import sun.net.www.protocol.https.DefaultHostnameVerifier;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ApacheHttpClient extends BaseApiClient {

    private static final int DEFAULT_THREAD_KEEP_ALIVE_TIME = 60;

    private ExecutorService executorService;
    private CloseableHttpClient httpClient;

    protected ApacheHttpClient() {
    }

    public void init(final HttpClientBuilderParams params) {

        //设置Socket配置
        SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).setSoKeepAlive(true).setSoReuseAddress(true)
                .setSoTimeout((int) params.getReadTimeout()).build();
        //获取注册建造者
        Registry<ConnectionSocketFactory> registry = getRegistry();
        //如果参数中的不为空,则用参数的
        if (params.getRegistry() != null) {
            registry = params.getRegistry();
        }

        //通过客户端管理池创建链接管理者
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager( registry );
        //设置相应参数
        connectionManager.setDefaultConnectionConfig(ConnectionConfig.custom().build());
        connectionManager.setDefaultSocketConfig(socketConfig);
        connectionManager.setMaxTotal(params.getDispatchMaxRequests());
        connectionManager.setDefaultMaxPerRoute(params.getDispatchMaxRequestsPerHost());

        //
        HttpRequestRetryHandler requestRetryHandler = params.getRequestRetryHandler();
        if (null == requestRetryHandler) {
            requestRetryHandler = new HttpRequestRetryHandler() {
                @Override
                public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                    if (executionCount > 2) {
                        return false;
                    }

                    if (exception instanceof NoHttpResponseException) {
                        return true;
                    }
                    return false;
                }
            };
        }


        RequestConfig defaultConfig = RequestConfig.custom()
                .setConnectTimeout((int) params.getConnectionTimeout())
                .setSocketTimeout((int) params.getReadTimeout())
                .setConnectionRequestTimeout((int) params.getReadTimeout())
                .build();


        httpClient = HttpClients.custom().setConnectionManager( connectionManager ).setDefaultRequestConfig(defaultConfig).setRetryHandler( requestRetryHandler ).build();
        ApacheIdleConnectionCleaner.registerConnectionManager( connectionManager, params.getMaxIdleTimeMillis());

        this.appKey = params.getAppKey();
        this.appSecret = params.getAppSecret();
        host = params.getHost();
        scheme = params.getScheme();


        if (params.getExecutorService() == null) {
            executorService = new ThreadPoolExecutor(0, params.getDispatchMaxRequests(), DEFAULT_THREAD_KEEP_ALIVE_TIME, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
                    new DeafultAsyncThreadFactory() );
        } else {
            executorService = params.getExecutorService();
        }


    }


    /**
     * 生成注册建造者
     * @return 注册建造者
     */
    private static Registry<ConnectionSocketFactory> getRegistry() {
        //创建注册建造者<链接Socket工厂>
        RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.create();
        try {
            //注册http与https
            registryBuilder.register("http", PlainConnectionSocketFactory.INSTANCE).build();
            registryBuilder.register("https", new SSLConnectionSocketFactory(SSLContext.getDefault(), new DefaultHostnameVerifier()));
        } catch (Exception e) {
            throw new RuntimeException("HttpClientUtil init failure !", e);
        }
        return registryBuilder.build();
    }

    private static Registry<ConnectionSocketFactory> getNoVerifyRegistry() {
        RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.create();
        try {
            registryBuilder.register("http", PlainConnectionSocketFactory.INSTANCE).build();
            registryBuilder.register(
                    "https",
                    new SSLConnectionSocketFactory(new SSLContextBuilder().loadTrustMaterial(
                            KeyStore.getInstance(KeyStore.getDefaultType()), new TrustStrategy() {
                                @Override
                                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                                    return true;
                                }
                            }).build(),
                            new HostnameVerifier() {
                                @Override
                                public boolean verify(String paramString, SSLSession paramSSLSession) {
                                    return true;
                                }
                            }));

        } catch (Exception e) {
            throw new RuntimeException("HttpClientUtil init failure !", e);
        }
        return registryBuilder.build();
    }


    private HttpUriRequest buildRequest(ApiRequest apiRequest) {
        if (apiRequest.getHttpConnectionMode() == HttpConnectionModel.SINGER_CONNECTION) {
            apiRequest.setHost(host);
            apiRequest.setScheme(scheme);
        }

        ApiRequestMaker.make(apiRequest, appKey, appSecret);
        RequestBuilder builder = RequestBuilder.create(apiRequest.getMethod().getValue());

        try {
            URIBuilder uriBuilder = new URIBuilder();
            uriBuilder.setScheme(apiRequest.getScheme().name());
            uriBuilder.setHost(apiRequest.getHost());
            uriBuilder.setPath(apiRequest.getPath());
            if (!HttpCommonUtil.isEmpty(apiRequest.getQuerys())) {
                for (Entry<String, String> entry : apiRequest.getQuerys().entrySet()) {
                    uriBuilder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            builder.setUri(uriBuilder.build());
        } catch (URISyntaxException e) {
            throw new SdkException("build http request uri failed", e);
        }

        EntityBuilder bodyBuilder = EntityBuilder.create();
        bodyBuilder.setContentType( ContentType.parse(apiRequest.getMethod().getRequestContentType()));
        if (!HttpCommonUtil.isEmpty(apiRequest.getFormParams())) {
            List<NameValuePair> paramList = new ArrayList<>();
            for (Entry<String, String> entry : apiRequest.getFormParams().entrySet()) {
                paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            bodyBuilder.setParameters(paramList);
            builder.setEntity(bodyBuilder.build());
        } else if (!HttpCommonUtil.isEmpty(apiRequest.getBody())) {
            bodyBuilder.setBinary(apiRequest.getBody());
            builder.setEntity(bodyBuilder.build());
        }

        for (Entry<String, List<String>> entry : apiRequest.getHeaders().entrySet()) {
            for (String value : entry.getValue()) {
                builder.addHeader(entry.getKey(), value);
            }
        }

        HttpUriRequest req = builder.build();
        System.out.println("消息头：");
        Header header = null;
        HeaderIterator it = req.headerIterator();
        while (it.hasNext()) {
            header = it.nextHeader();
            System.out.println(header.getName() + "=" + header.getValue());
        }

        return req;
    }

    private ApiResponse parseToApiResponse(HttpResponse httpResponse) throws IOException {
        ApiResponse result = new ApiResponse(httpResponse.getStatusLine().getStatusCode());

        // headers
        result.setHeaders(new HashMap<String, List<String>>());
        for (Header header : httpResponse.getAllHeaders()) {
            List<String> values = result.getHeaders().get(header.getName());

            if (values == null) {
                values = new ArrayList<String>();
            }

            values.add(header.getValue());
            result.getHeaders().put(header.getName().toLowerCase(), values);
        }

        // message
        result.setMessage(httpResponse.getStatusLine().getReasonPhrase());


        if (httpResponse.getEntity() != null) {
            // content type
            Header contentType = httpResponse.getEntity().getContentType();
            if (contentType != null) {
                result.setContentType(contentType.getValue());
            } else {
                result.setContentType( HttpConstant.CLOUDAPI_CONTENT_TYPE_TEXT);
            }

            // body
            result.setBody( EntityUtils.toByteArray(httpResponse.getEntity()));

            String contentMD5 = result.getFirstHeaderValue( HttpConstant.CLOUDAPI_HTTP_HEADER_CA_CONTENT_MD5);
            if (null != contentMD5 && !"".equals(contentMD5)) {
                String localContentMd5 = SignUtil.base64AndMD5(result.getBody());
                if (!contentMD5.equalsIgnoreCase(localContentMd5)) {
                    throw new SdkException("Server Content MD5 does not match body content , server md5 is " + contentMD5 + "  local md5 is " + localContentMd5 + " body is " + new String(result.getBody()));
                }
            }
        } else {
            String contentTypeStr = result.getFirstHeaderValue( HttpConstant.CLOUDAPI_HTTP_HEADER_CONTENT_TYPE);
            if (null == contentTypeStr) {
                contentTypeStr = HttpConstant.CLOUDAPI_CONTENT_TYPE_TEXT;
            }
            result.setContentType(contentTypeStr);
        }


        return result;

    }

    @Override
    public final ApiResponse sendSyncRequest(ApiRequest apiRequest) {
        HttpUriRequest httpRequest = buildRequest(apiRequest);
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpRequest);
            return parseToApiResponse(httpResponse);
        } catch (IOException e) {
            throw new SdkException(e);
        } finally {
            HttpCommonUtil.closeQuietly(httpResponse);
        }
    }


    @Override
    public final void sendAsyncRequest(final ApiRequest apiRequest,final ApiCallback apiCallback) {
        final long start = System.currentTimeMillis();
        executorService.submit(new Callable<ApiResponse>() {
            @Override
            public ApiResponse call() throws Exception {
                ApiResponse apiResponse;
                try {
                    apiResponse = sendSyncRequest(apiRequest);
                } catch (Exception e) {
                    if (apiCallback != null) {
                        apiCallback.onFailure(apiRequest, e);
                    }
                    throw e;
                }
                if (apiCallback != null) {
                    long latency = System.currentTimeMillis() - start;
                    apiResponse.addHeader("X-CA-LATENCY", String.valueOf(latency));
                    apiCallback.onResponse(apiRequest, apiResponse);

                }
                return apiResponse;
            }
        });
    }

    private static class DeafultAsyncThreadFactory implements ThreadFactory {

        private final AtomicInteger counter = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "Aliyun_SDK_Async_ThreadPool_" + counter.incrementAndGet());
        }
    }
}
