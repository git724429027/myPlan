package com.wang.common;

import lombok.Data;


/**
 * @author xiaowang
 * @time 2021-08-28 18:49
 **/
@Data
public class Response {

    private Integer code;
    private String message;
    private Object data;

    private Response(){
    }

    public static  Response success(){
        Response response= new Response();
        response.setCode(200);
        response.setMessage("成功！");
        return response;
    }

    public static Response error(){
        Response response= new Response();
        response.setCode(400);
        response.setMessage("失败！");
        return response;
    }

    public Response code(Integer code){
        this.setCode(code);
        return this;
    }

    public Response message(String message){
        this.setMessage(message);
        return this;
    }

    public Response data(Object data){
        this.setData(data);
        return this;
    }


}
