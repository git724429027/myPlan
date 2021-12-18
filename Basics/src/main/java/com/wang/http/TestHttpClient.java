package com.wang.http;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaowang
 * @time 2021-09-15 21:18
 **/
public class TestHttpClient {
    public static void main(String[] args) {
        String str = HttpClientUtil.doGet("http://localhost:8001/payment/get/29");
        System.out.println(str);

        String json="{\"id\":\"29\",\"serial\":\"123\"}";
        String str2 = HttpClientUtil.doPostJson("http://localhost:8001/payment/create", json);
        System.out.println(str2);

        Map<String,String> map=new HashMap<>();
        map.put("name","张三");
        map.put("age","13");
        System.out.println(HttpClientUtil.doGet("http://localhost:8001/payment/test", map));
        System.out.println(HttpClientUtil.doPost("http://localhost:8001/payment/test", map));
    }
}
