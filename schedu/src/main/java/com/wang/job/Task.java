package com.wang.job;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Author: crush
 * @Date: 2021-07-29 15:35
 * version 1.0
 */
@Data
@Accessors(chain = true) // 方便链式编写 习惯所然
public class Task {
    /**
     * 动态任务名曾
     */
    private String name;

    /**
     * 设定动态任务开始时间
     */
    private LocalDateTime start;
}