package com.wang.controller;

import com.wang.common.Response;
import com.wang.service.LogService;
import com.wang.utils.SystemControllerLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xiaowang
 * @time 2021-09-07 21:48
 **/
@RestController
@RequestMapping("/wang/login")
public class LoginController {

    @Resource
    private LogService logService;

    @SystemControllerLog(description = "测试1")
    @RequestMapping("/test1")
    public String test1(){
        return "test1";
    }

    @SystemControllerLog(description = "测试2")
    @RequestMapping("/test2")
    public Response test2(String str){
        return logService.test1("hh",2);
    }

    @SystemControllerLog(description = "测试3")
    @RequestMapping("/test3")
    public Response test2(Integer i,String str){
        return logService.test1("hh",2);
    }

}
