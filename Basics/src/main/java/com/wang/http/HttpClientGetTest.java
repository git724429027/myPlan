package com.wang.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class HttpClientGetTest {
 
    public static void main(String[] args) throws Exception{
        // HttpClientGetTest.testHttpClientGet();
        HttpClientGetTest.testGetParameter();
        //HttpClientGetTest.testPost();
        //HttpClientGetTest.testPostParam();
        //HttpClientGetTest.testPostParamJson();
        // HttpClientGetTest.testHttpClientUtil();
    }
    /**
     * 1.get请求不带参数
     * @throws Exception
     */
    public static void testHttpClientGet() throws Exception{
        //1.创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //2.创建get对象
        HttpGet get = new HttpGet("http://www.baidu.com");
        //3.执行get请求，并返回响应结果
        CloseableHttpResponse response = client.execute(get);
        //4.处理结果
            //1.获取结果中的状态码
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println(statusCode);
            //2.获取结果中内容
            HttpEntity entity = response.getEntity();//获得实体内容
            String content = EntityUtils.toString(entity, "utf-8");//通过实体工具类转换实体输出格式
            System.out.println(content);
        //5.关闭连接
        client.close();
    }
 
 
    /**
     * 2.get请求带参数
     */
    public static void testGetParameter() throws  Exception{
        //1.创建httpclient对象
        CloseableHttpClient client=HttpClients.createDefault();
        //2.创建封装的URI对象，可以存放参数
        URIBuilder uri=new URIBuilder("https://search.bilibili.com/all");
        //存放参数--可以参照地址栏：键值对方法
        uri.addParameter("keyword","焰灵姬");
        //3.创建get对象:并放入URI
        HttpGet get=new HttpGet(uri.build());
        //4.执行get方法，并返回响应结果
        CloseableHttpResponse response = client.execute(get);
        //5.处理结果
            //1.获取结果状态码
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println(statusCode);
            //2.获取结果实体内容
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, "utf-8");
             System.out.println(content);
        //6.关闭连接
        client.close();
    }
 
    /**
     * 3.POST请求不带参数
     */
    public static void testPost() throws  Exception{
        //1.创建httpclient对象
        CloseableHttpClient client=HttpClients.createDefault();
        //2.创建post对象
        HttpPost post=new HttpPost("http://localhost:8080/test/post");
        //3.执行post方法:得到结果
        CloseableHttpResponse response = client.execute(post);
        //4.处理结果
            //1.得到状态码
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println(statusCode);
            //2.得到实体内容
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, "utf-8");
            System.out.println(content);
        //5.关闭连接
        client.close();
    }
 
    /**
     * 4.POST请求带参数
     */
    public static void testPostParam() throws  Exception{
        //1.创建httpclient对象
        CloseableHttpClient client=HttpClients.createDefault();
        //2.创建post对象
        HttpPost post=new HttpPost("http://localhost:8080/test/post/param");
            //给定参数
            List<BasicNameValuePair> list=new ArrayList<BasicNameValuePair>();
            list.add(new BasicNameValuePair("name","焰灵姬"));
            list.add(new BasicNameValuePair("pwd","yanlingji"));
            //将参数做字符串的转换
            StringEntity entity = new UrlEncodedFormEntity(list, "utf-8");
            //绑定参数
            post.setEntity(entity);
 
        //3.执行post方法:得到结果
        CloseableHttpResponse response = client.execute(post);
        //4.处理结果
            //@1.得到状态码
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println(statusCode);
            //@2.得到实体内容
            HttpEntity en = response.getEntity();
            String content = EntityUtils.toString(en, "utf-8");
            System.out.println(content);
        //5.关闭连接
        client.close();
    }
 
    /**
     * 5.POST请求带JSON格式参数
     */
    public static void testPostParamJson() throws  Exception{
        //1.创建httpclient对象
        CloseableHttpClient client=HttpClients.createDefault();
        //2.创建post对象
        HttpPost post=new HttpPost("http://localhost:8080/test/post/param/json");
       /* //给定参数
        List<BasicNameValuePair> list=new ArrayList<BasicNameValuePair>();
        list.add(new BasicNameValuePair("name","焰灵姬"));
        list.add(new BasicNameValuePair("pwd","yanlingji"));*/
        //将参数做json字符串格式的转换
        String json="{\"name\":\"焰灵姬\",\"pwd\":\"yanlingji\"}";
        StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        //绑定参数
        post.setEntity(entity);
 
        //3.执行post方法:得到结果
        CloseableHttpResponse response = client.execute(post);
        //4.处理结果
        //@1.得到状态码
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        //@2.得到实体内容
        HttpEntity en = response.getEntity();
        String content = EntityUtils.toString(en, "utf-8");
        System.out.println(content);
        //5.关闭连接
        client.close();
    }
 
    /**
     * 测试HttpClient工具类
     */
    public static void testHttpClientUtil(){
        /*String url="http://localhost:8080/test/post/param";
        Map<String, String> param = new HashMap<String, String>();
        param.put("name", "李四");
        param.put("pwd", "lisi");
        String s = HttpClientUtil.doPost(url,param);
        System.out.println(s);*/
 
        /*String url="http://localhost:8080/test/post";
        String s = HttpClientUtil.doPost(url);
        System.out.println(s);*/
 
        String url="http://localhost:8000";
        String json="{\"name\":\"焰灵姬\",\"pwd\":\"yanlingji\"}";
        String s = HttpClientUtil.doPostJson(url, json);
        System.out.println(s);
 
 
    }
}