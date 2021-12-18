package com.wang.pojo;

import com.wang.utils.HttpClientUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MyTask extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //TODO 这里写定时任务的执行逻辑
        Map<String,String> map=new HashMap<>();
        map.put("name","张三");
        map.put("age","13");
        System.out.println(HttpClientUtil.doGet("http://localhost:8001/payment/test", map));
        System.out.println(HttpClientUtil.doPost("http://localhost:8001/payment/test", map));
        System.out.println("简单的定时任务执行时间："+new Date().toLocaleString());
    }
}