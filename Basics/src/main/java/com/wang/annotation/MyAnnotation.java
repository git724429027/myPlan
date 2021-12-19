package com.wang.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xiaowang
 * @time 2021-12-18 22:56
 **/
@Target(ElementType.METHOD)  //标记这个注解应该是哪种 Java 成员。
@Retention(RetentionPolicy.RUNTIME)  //标识这个注解怎么保存，是只在代码中，还是编入class文件中，或者是在运行时可以通过反射访问。
public @interface MyAnnotation {
    // String value();
    String value() default ""; //设置默认值
}
