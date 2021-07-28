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

    public static String CGI_APP_ID = "1408003042392957004";

    public static String CGI_APP_SECRET = "6b5478d1af9b3cbadc7bc9553ec3c85d";


//    public static String CGI_HOST = "cgi.test.dossen.com";
//
//    public static String CGI_APP_ID = "173946580829868033";
//
//    public static String CGI_APP_SECRET = "42d6741445afba6140a8d4051edc938f";
}
