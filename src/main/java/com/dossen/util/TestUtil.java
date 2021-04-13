package com.dossen.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dossen.model.BaseRP;

import java.util.*;

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
        request.put("objectName","daily/DCJF_Balance_Data_Daily_20201130.csv");
        BaseRP<Object> baseRP = CgiHttpClient.send("/data/minio/download/url/get", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP.getItem());
    }


    public static String test2(){
        Map request = new HashMap<String,Object>();
        request.put("hotelId","0771047");
        request.put("bizDay","2020-10-22");
        request.put("monthNum","12");
        BaseRP<Object> baseRP = CgiHttpClient.send("/data/hotel/business/query", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP);
    }

    public static String test3(){
        Map request = new HashMap<String,Object>();
//        String[] idList = {"62379245191696384"};
//        request.put("organizationIDList",idList);
//        request.put("needChildren",true);
        request.put("organizationType","DEPARTMENT");
        BaseRP<Object> baseRP = CgiHttpClient.send("/oneid/organization/query", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP);
    }

    public static String test41(){
        Map request = new HashMap<String,Object>();
        request.put("organizationId","0020001");
//        request.put("endTime","1605036600000");
//        request.put("startTime","1604959200000");
        request.put("type","SHOP");
        BaseRP<Object> baseRP = CgiHttpClient.send("/data/flow/store/query", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP);
    }

    public static String test4(){
        Map request = new HashMap<String,Object>();
        request.put("organizationId","62379321758716148");
        request.put("type","AREA");
        BaseRP<Object> baseRP = CgiHttpClient.send("/data/flow/trend/query", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP);
    }

    public static String test5(){
        Map request = new HashMap<String,Object>();
        request.put("organizationId","62379321758716148");
        request.put("type","AREA");
//        request.put("startTime","1605715200000");
//        request.put("endTime","1605801599999");
        BaseRP<Object> baseRP = CgiHttpClient.send("/data/flow/realtime/query", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP);
    }

    public static String test6(){
        Map request = new HashMap<String,Object>();
        request.put("params","{\"HotelID\":0716013,\"startBizday\":\"2019-04-06\",\"endBizday\":\"2020-04-06\"}");
        BaseRP<Object> baseRP = CgiHttpClient.send("/data/template/access/5", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP);
    }


    public static String test7(){
        Map request = new HashMap<String,Object>();
        request.put("organizationType","WARZONE");
        BaseRP<Object> baseRP = CgiHttpClient.send("/oneid/organization/query", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP);
    }


    public static String test8(){
        Map request = new HashMap<String,Object>();
        request.put("employeeID","029303");
        BaseRP<Object> baseRP = CgiHttpClient.send("/oneid/user/query", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP);
    }


    public static String test9(){
        Map request = new HashMap<String,Object>();
        request.put("userAccountID","166766511783022592");
        BaseRP<Object> baseRP = CgiHttpClient.send("/oneid/user/get", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP);
    }


    public static String test10(){
        List<String> list = new ArrayList<>();
        list.add("62382838955250044");

        for (String id : list) {
            Map request = new HashMap<String,Object>();
            request.put("appID","224357176743211009");
            request.put("userAccountID",id);
            BaseRP<Object> baseRP = CgiHttpClient.send("/oneid/user/system/authorize", request, new TypeReference<BaseRP<Object>>(){});
            System.out.println("调用结果:"+JSON.toJSONString(baseRP));
        }

        return "调用结果:"+list.size();
    }


    public static String tes11(){
        Map request = new HashMap<String,Object>();
        request.put("userAccountID","62382838942667258");
        BaseRP<Object> baseRP = CgiHttpClient.send("/observatory/member/image/get", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP);
    }


    public static String tes12(){
        Map request = new HashMap<String,Object>();
        request.put("params","{\"hotelId\":0716013,\"startDay\":\"2020-04-06\",\"endDay\":\"2020-06-06\"}");
        BaseRP<Object> baseRP = CgiHttpClient.send("/data/template/access/26", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP);
    }

    public static String tes13(){
        Map request = new HashMap<String,Object>();
        request.put("params","{\"hotelId\":0020001,\"startDay\":\"2020-12-19\",\"endDay\":\"2020-12-20\"}");
        BaseRP<Object> baseRP = CgiHttpClient.send("/data/template/access/27", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP);
    }

    public static String tes14(){
        Map request = new HashMap<String,Object>();
        request.put("params","{\"hotelId\":0716013,\"startDay\":\"2019-04-06\",\"endDay\":\"2020-04-06\"}");
        BaseRP<Object> baseRP = CgiHttpClient.send("/data/template/access/28", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP);
    }

    public static String tes15(){
        Map request = new HashMap<String,Object>();
        request.put("params","{\"hotelId\":0716013,\"startDay\":\"2019-04-06\",\"endDay\":\"2020-04-06\"}");
        BaseRP<Object> baseRP = CgiHttpClient.send("/data/template/access/29", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP);
    }

    public static String tes16(){
        Map request = new HashMap<String,Object>();
        request.put("params","{\"hotelId\":0716013,\"startDay\":\"2019-04-06\",\"endDay\":\"2020-04-06\"}");
        BaseRP<Object> baseRP = CgiHttpClient.send("/data/template/access/30", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP);
    }

    public static String tes17(){
        Map request = new HashMap<String,Object>();
        request.put("params","{\"hotelId\":0716013,\"startDay\":\"2020-04-06\",\"endDay\":\"2020-06-06\"}");
        BaseRP<Object> baseRP = CgiHttpClient.send("/data/template/access/31", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP);
    }

    public static String tes18(){
        Map request = new HashMap<String,Object>();
        request.put("params","{\"hotelId\":0716013,\"startDay\":\"2019-04-06\",\"endDay\":\"2020-04-06\"}");
        BaseRP<Object> baseRP = CgiHttpClient.send("/data/template/access/32", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP);
    }

    public static String test19(){
        Map request = new HashMap<String,Object>();
        request.put("params","{\"hotelId\":0716013,\"startDay\":\"2019-04-06\",\"endDay\":\"2020-04-06\"}");
        BaseRP<Object> baseRP = CgiHttpClient.send("/data/template/access/33", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP);
    }





    /**
     * 生成8位UUID
     * @return 8位UUID
     */
    public static String generateShortUUID() {
        String[] baseString={"0","1","2","3",
                             "4","5","6","7","8","9",
                             "a","b","c","d","e",
                             "f","g","h","i","j",
                             "k","l","m","n","o",
                             "p","q","r","s","t",
                             "u","v","w","x","y",
                             "z","A","B","C","D",
                             "E","F","G","H","I",
                             "J","K","L","M","N",
                             "O","P","Q","R","S",
                             "T","U","V","W","X","Y","Z"};

        StringBuilder shortBuffer = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(baseString[x % 0x3E]);
        }
        return shortBuffer.toString();
    }


    /**
     * 获取UUID主键
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }


    public static String test20(){
        Map request = new HashMap<String,Object>();
        request.put("params","{\"userid\":62383410227844025,\"bizday\":\"2020-12-29\"}");
        BaseRP<Object> baseRP = CgiHttpClient.send("/data/template/access/51", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP);
    }


    public static String test21(){
        Map request = new HashMap<String,Object>();
        request.put("params","{\"ymonth\":\"2021-01\"}");
        BaseRP<Object> baseRP = CgiHttpClient.send("/data/template/access/53/20/24", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP);
    }


    public static String test22(){
        Map request = new HashMap<String,Object>();
        request.put("params","{\"ymonth\":\"2021-01\"}");
        BaseRP<Object> baseRP = CgiHttpClient.send("/data/template/access/53/20/25", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP);
    }

    public static String test23(){
        Map request = new HashMap<String,Object>();
        request.put("params","{\"lng\":113.274536,\"lat\":23.139097,\"distince\":1}");
        BaseRP<Object> baseRP = CgiHttpClient.sendSql("/data/template/access/50", request, new TypeReference<BaseRP<Object>>(){});
        return "调用结果:"+JSON.toJSONString(baseRP);
    }


    public static void main(String[] args) {
        String test = test();
        System.out.println(test);

    }

}
