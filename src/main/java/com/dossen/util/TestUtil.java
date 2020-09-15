package com.dossen.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dossen.model.BaseRP;

import java.util.HashMap;
import java.util.Map;

/**
 * @项目名称：dossen-api-service
 * @类名称：TestUtil
 * @类描述：测试类
 * @创建人：万星明
 * @创建时间：2020/7/27
 */
public class TestUtil {

    public static String test(){
        Map request = new HashMap<String,Object>();
        request.put("bucketName","dcjf");
        request.put("objectName","daily/DCJF_Balance_Data_Daily_20200809.csv");
        BaseRP<Object> baseRP = CgiHttpClient.send("/data/minio/download/url/get", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP.getItem());
    }

    public static void main(String[] args) {

        for (int i=0;i<=100;i++){
            String test = test();
            System.out.println(test);
        }

    }

}
