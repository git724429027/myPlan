package com.wang.service;

import com.wang.common.Response;
import com.wang.utils.SystemServiceLog;
import org.springframework.stereotype.Service;

/**
 * @author xiaowang
 * @time 2021-09-07 22:22
 **/
@Service
public class LogService {


    @SystemServiceLog(description = "service")
    public Response test1(String str1,Integer num){
        int i=num/0;
        return Response.success();
    }


}
