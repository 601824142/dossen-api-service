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

    public static void test(){
        Map request = new HashMap<String,Object>();
        request.put("bucketName","dcjf");
        request.put("objectName","daily/DCJF_Balance_Data_Daily_20200714.csv");

        BaseRP<Object> baseRP = CgiHttpClient.send("/data/minio/download/url/get", request, new TypeReference<BaseRP<Object>>(){});
        System.out.println("调用结果:"+ JSON.toJSONString(baseRP.getItem()));
    }

    public static void main(String[] args) {
        test();
    }

}
