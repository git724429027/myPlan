package com.wang.fanse;

import com.wang.annotation.ClassAnnotation;
import com.wang.annotation.FiledAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author xiaowang
 * @time 2021-12-19 15:33
 **/
public class TestAnnotation {
    public static void main(String[] args) {
        try {
            Class<?> aClass = Class.forName("com.wang.fanse.Person");
            //获取类上的指定注解
            ClassAnnotation annotation = aClass.getAnnotation(ClassAnnotation.class);
            Annotation[] annotations = aClass.getAnnotations();
            for (Annotation annotation1 : annotations) {
                Class<? extends Annotation> aClass1 = annotation1.getClass();
            }
            System.out.println(annotation.value());
            //获取属性上的指定注解
            Field name = aClass.getDeclaredField("name");
            name.setAccessible(true);
            FiledAnnotation filedAnnotation = name.getAnnotation(FiledAnnotation.class);
            System.out.println(filedAnnotation.value());
            System.out.println(filedAnnotation.type());
            System.out.println(filedAnnotation.length());
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
