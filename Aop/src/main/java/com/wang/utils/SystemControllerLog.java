package com.wang.utils;

import java.lang.annotation.*;

/**
 * @author xiaowang
 * @time 2021-09-07 22:16
 **/

@Target({ElementType.PARAMETER, ElementType.METHOD})//作用在参数和方法上
@Retention(RetentionPolicy.RUNTIME)//运行时注解
@Documented//表明这个注解应该被 javadoc工具记录
public @interface SystemControllerLog {
    String description() default "";
}
