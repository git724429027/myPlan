package com.wang.aop;

import com.google.gson.Gson;
import com.wang.common.Response;
import com.wang.utils.SystemControllerLog;
import com.wang.utils.SystemServiceLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * @author xiaowang
 * @time 2021-09-07 22:19
 **/
@Aspect
@Component
@SuppressWarnings("all")//关闭代码中的不规范提示
public class SystemLogAspect {

    //本地异常日志记录对象
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    //Service层切点
    /**
           * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
           */
    @Pointcut("@annotation(com.wang.utils.SystemServiceLog)")
    public void serviceAspect(){
    }

    //Controller层切点
    @Pointcut("@annotation(com.wang.utils.SystemControllerLog)")
    public void controllerAspect(){
    }

    /**
     65      * 设置操作异常切入点记录异常日志 扫描所有controller包下操作
     66      */
      @Pointcut("execution(* com.wang.controller..*.*(..))")
      public void operExceptionLogPoinCut() {
      }


    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) throws ClassNotFoundException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();

        try {
            //*========控制台输出=========*//
            System.out.println("==============前置通知开始==============");
            System.out.println("请求方法" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()));
            System.out.println("方法描述：" + getControllerMethodDescription(joinPoint));
            logger.info("请求uri:{}",request.getRequestURI());
            logger.info("请求url:{}",request.getRequestURL());
            logger.info("请求方式：{}",request.getMethod());
            //保存数据库
        }catch (Exception e){
            //记录本地异常日志
            logger.error("==前置通知异常==");
            logger.error("异常信息：{}",e.getMessage());
        }
    }

    @AfterThrowing("controllerAspect()")
    public void doAfterThrowing(JoinPoint joinPoint) throws ClassNotFoundException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();

        try {
            //*========控制台输出=========*//
            System.out.println("==============异常通知开始==============");
            System.out.println("请求方法" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()));
            System.out.println("方法描述：" + getControllerMethodDescription(joinPoint));
            logger.info("请求uri:{}",request.getRequestURI());
            logger.info("请求url:{}",request.getRequestURL());
            logger.info("请求方式：{}",request.getMethod());
            //保存数据库
        }catch (Exception e){
            //记录本地异常日志
            logger.error("==异常通知异常==");
            logger.error("异常信息：{}",e.getMessage());
        }
    }

    @Around("controllerAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws ClassNotFoundException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();

        try {
            //*========控制台输出=========*//
            System.out.println("==============环绕通知开始==============");
            System.out.println("请求方法" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()));
            System.out.println("方法描述：" + getControllerMethodDescription(joinPoint));
            logger.info("请求uri:{}",request.getRequestURI());
            logger.info("请求url:{}",request.getRequestURL());
            logger.info("请求方式：{}",request.getMethod());
            Response response = (Response) joinPoint.proceed();
            return response;
            //保存数据库
        }catch (Throwable throwable) {
            //记录本地异常日志
            logger.error("==环绕通知异常==");
            logger.error("异常信息：{}",throwable.getMessage());
        }
        return Response.error();
    }

    /**
     * @Description  获取注解中对方法的描述信息 用于service层注解
     * @date 2018年9月3日 下午5:05
     */
    public static String getServiceMethodDescription(JoinPoint joinPoint) throws Exception{
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    description = method.getAnnotation(SystemServiceLog.class).description();
                    break;
                }
            }
        }
        return description;
    }



    /**
     * @Description  获取注解中对方法的描述信息 用于Controller层注解
     * @date 2018年9月3日 上午12:01
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();//目标方法名
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                        description=method.getAnnotation(SystemControllerLog.class).description();
                        break;
                }
            }
        }
        return description;
    }

    /**
     * @Description  异常通知 用于拦截service层记录异常日志
     * @date 2018年9月3日 下午5:43
     */
    @AfterThrowing(pointcut = "serviceAspect()",throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint,Throwable e){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户
        //获取请求ip
        //获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        Gson gson=new Gson();
        if (joinPoint.getArgs()!=null&&joinPoint.getArgs().length>0){
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                params+= gson.toJson(joinPoint.getArgs()[i])+";";
            }
        }
        try{
            /*========控制台输出=========*/
            System.out.println("=====异常通知开始=====");
            System.out.println("异常代码:" + e.getClass().getName());
            System.out.println("异常信息:" + e.getMessage());
            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            System.out.println("方法描述:" + getServiceMethodDescription(joinPoint));
            System.out.println("请求参数:" + params);
        }catch (Exception ex){
            //记录本地异常日志
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());
        }
    }



}
