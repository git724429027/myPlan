package com.wang.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author xiaowang
 * @time 2021-09-10 22:43
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {


    @ExcelProperty(value = {"个人信息","名字"},index = 0)
    private String name;
    @ExcelProperty(value = {"个人信息","身份证号"},index = 1)
    private String sfzh;
    @ExcelProperty(value = {"个人信息","年龄"},index = 2)
    private Integer age;
    @ExcelProperty(value = {"其他信息","财产"},index = 3)
    private Double money;
    @ExcelProperty(value = {"创建时间","创建时间"},index = 4)
    private Date createTime;


}
