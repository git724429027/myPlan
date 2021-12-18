package com.wang.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

/**
 * @author xiaowang
 * @time 2021-12-08 23:42
 **/
@RestController
@RequestMapping("/https")
@Slf4j
public class HttpsController {


    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/image")
    public String getImage(HttpServletResponse response) throws IOException {
        ResponseEntity<byte[]> entity = restTemplate.getForEntity("https://api.dujin.org/bing/1366.php", byte[].class);
        byte[] body = entity.getBody();
        response.setContentType("image/jpeg");
        response.setCharacterEncoding("UTF-8");
        OutputStream outputSream = response.getOutputStream();
        outputSream.write(body);
        outputSream.flush();
        log.info("返回结果：{}",body);
        return "success";
    }


    @RequestMapping("/base64")
    public String getBase64() throws IOException {
        ResponseEntity<byte[]> entity = restTemplate.getForEntity("https://api.dujin.org/bing/1366.php", byte[].class);
        byte[] body = entity.getBody();
        String base64 = new String(Base64.getEncoder().encode(body));
        log.info("base64:{}",base64);
        return base64;
    }

}
