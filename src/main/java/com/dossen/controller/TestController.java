package com.dossen.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dossen.model.BaseRP;
import com.dossen.util.CgiHttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @项目名称：dossen-api-service
 * @类名称：
 * @类描述：
 * @创建人：万星明
 * @创建时间：2020/8/13
 */

@Controller
public class TestController {

    @ResponseBody
    @RequestMapping(value = "/testGetDownLoadUrl", method = RequestMethod.GET)
    public Object testGetDownLoadUrl(){
        Map<String,Object> request = new HashMap<>();
        request.put("bucketName","dcjf");
        request.put("objectName","daily/DCJF_Balance_Data_Daily_20200714.csv");

        BaseRP<Object> baseRP = CgiHttpClient.send("/data/minio/download/url/get", request, new TypeReference<BaseRP<Object>>(){});
        System.out.println("调用结果:"+ JSON.toJSONString(baseRP.getItem()));
        return baseRP;
    }

}
