package com.dossen.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dossen.model.BaseRP;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @项目名称：dossen-api-service
 * @类名称：TestUtil
 * @类描述：测试类
 * @创建人：万星明
 * @创建时间：2020/7/27
 */
public class TestUtil {

    public static void test(){
        Map request = new HashMap<String,Object>();
        request.put("bucketName","dcjf");
        request.put("objectName","daily/DCJF_Balance_Data_Daily_20210419.csv");
        BaseRP<Object> baseRP = CgiHttpClient.send("/data/minio/download/url/get", request, new TypeReference<BaseRP<Object>>(){});
        System.out.println("调用结果:"+ JSON.toJSONString(baseRP.getItem()));
    }


    public static String test9(){
        Map request = new HashMap<String,Object>();
        request.put("userAccountID","62382838942666858");
        BaseRP<Object> baseRP = CgiHttpClient.send("/oneid/user/get", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP);
    }

    public static String test10(){
        List<String> list = new ArrayList<>();
        list.add("62382838942666858");

        for (String id : list) {
            Map request = new HashMap<String,Object>();
            request.put("appID","224357176743211009");
            request.put("userAccountID",id);
            BaseRP<Object> baseRP = CgiHttpClient.send("/oneid/user/system/authorize", request, new TypeReference<BaseRP<Object>>(){});
            System.out.println("调用结果:"+JSON.toJSONString(baseRP));
        }

        return "调用结果:"+list.size();
    }

    public static void main(String[] args) throws ParseException {

            String str = "是否为主键列(1是、-1不是) ";
            String regEx = "[\n1234567890`~!@#$%^&*(+-=|{}':;',\\[\\].<>/?~！@#￥%……&*()（）——+|{}【】‘；：”“’。， 、？]";
            str.replaceAll("", "");
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            String newString = m.replaceAll("").trim();
            System.out.println(newString);

    }

}
