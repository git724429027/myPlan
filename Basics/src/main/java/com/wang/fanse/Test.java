package com.wang.fanse;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author xiaowang
 * @time 2021-09-04 19:44
 **/
public class Test {
    public static void main(String[] args) {
        //得到 Class 的三种方式
        //1、通过对象调用 getClass() 方法来获取,通常应用在：比如你传过来一个 Object
        //  类型的对象，而我不知道你具体是什么类，用这种方法
        Person person = new Person();
        Class<? extends Person> aClass = person.getClass();
        //2、直接通过 类名.class 的方式得到,该方法最为安全可靠，程序性能更高
        //  这说明任何一个类都有一个隐含的静态成员变量 class
        Class<? extends Person> bClass=Person.class;
        //3、通过 Class 对象的 forName() 静态方法来获取，用的最多，
        //   但可能抛出 ClassNotFoundException 异常
        try {
            Class<? extends Person> cClass = (Class<? extends Person>) Class.forName("com.wang.fanse.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //获取public变量
        Field[] fields = bClass.getFields();
        for (Field field : fields) {
            System.out.println("public变量名："+field.getName());
        }

        Field[] declaredFields = bClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("all变量名："+declaredField.getName());
        }

        //获得类的public类型的方法。这里包括 Object 类的一些方法
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            System.out.println("public方法名："+method.getName());
        }

        //获取所有自己写的方法
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("all方法名："+declaredMethod.getName());
            Parameter[] parameters = declaredMethod.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println(declaredMethod.getName()+":"+parameter.getName()+":"+parameter.getType());
            }
        }
        //执行private的方法
        try {
            Method say = aClass.getDeclaredMethod("say", String.class);
            say.setAccessible(true);
            say.invoke(aClass.newInstance(),"哈哈哈");
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
