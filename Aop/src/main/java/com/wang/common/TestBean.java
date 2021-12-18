package com.wang.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author xiaowang
 * @time 2021-12-17 22:55
 **/
@PropertySource("classpath:test.properties")
// @ConfigurationProperties(prefix = "a")
@Component
@Data
public class TestBean {

    @Value("${a.wang.url}")
    private String wangUrl;
    @Value("${a.yun.url}")
    private String yunUrl;
    @Value("${a.tao.url}")
    private String taoUrl;

}
