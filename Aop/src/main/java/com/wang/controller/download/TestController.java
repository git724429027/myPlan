package com.wang.controller.download;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author xiaowang
 * @time 2021-12-13 22:53
 **/
@RestController
@RequestMapping("/download")
@Slf4j
public class TestController {


    @RequestMapping("/test1")
    public void test1(HttpServletRequest request){
        String path = request.getServletContext().getRealPath("/resource"); //c盘的一个tomcat目录
        log.info("path:{}",path);

        String format = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());

        File file = new File(path, format);
        if (!file.exists()){
            file.mkdirs();
        }
    }

    @RequestMapping("/test2")
    public void test2(){
        String path = System.getProperty("user.dir"); //项目的根路径即 MyPlan/
        log.info("path:{}",path);

        String format = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());

        File file = new File(path, format);
        if (!file.exists()){
            file.mkdirs();
        }
    }
    @RequestMapping("/test3")
    public void test3() throws FileNotFoundException {
        String path = ResourceUtils.getURL("classpath:").getPath(); //  D:/Code/ideaCode/MyPlan/Aop/target/classes/
        log.info("path:{}",path);

        String format = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());

        File file = new File(path, format);
        if (!file.exists()){
            file.mkdirs();
        }
    }

    //读文件夹
    @RequestMapping("/test4")
    public void test4()  {
        String path = Thread.currentThread().getContextClassLoader().getResource("resource").getPath(); //D:/Code/ideaCode/MyPlan/Aop/target/classes/resource
        log.info("path:{}",path);
        // String filePath = URLDecoder.decode(path, "UTF-8");//如果路径中带有中文会被URLEncoder,因此这里需要解码
        String format = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());

        File file = new File(path, format);
        if (!file.exists()){
            file.mkdirs();
        }
    }

    //读文件夹
    @RequestMapping("/test5")
    public void test5()  {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("fileName");
    }

}
