package com.dossen.config;

import org.springframework.stereotype.Component;

/**
 * @项目名称：dossen-api-service
 * @类名称：CgiClientConfig
 * @类描述：
 * @创建人：万星明
 * @创建时间：2020/8/4
 */

@Component
public class CgiClientConfig {

    public static String CGI_VERSION = "2.0";

    public static String CGI_SCHEME = "HTTP://";

    public static String CGI_HOST = "cgi.dossen.com";

    public static String CGI_APP_ID = "189155456522301441";

    public static String CGI_APP_SECRET = "e283ad86afb758c1fbca226623c6b31a";
}