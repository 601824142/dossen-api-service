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

    public static String CGI_HOST = "cgi.dossen.com:31500";

    public static String CGI_APP_ID = "224357176743211009";

    public static String CGI_APP_SECRET = "257e189b13c6386b263358c80d2417c2";


//    public static String CGI_HOST = "10.0.31.39:31500";
//
//    public static String CGI_APP_ID = "217867835895058433";
//
//    public static String CGI_APP_SECRET = "ec89a9bbb11daed6f96718b8101c0483";
}
