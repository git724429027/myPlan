package com.wang.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.wang.pojo.User;
import com.wang.read.ExcelListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xiaowang
 * @time 2021-09-11 22:42
 **/
public class Test1 {
    public static void main(String[] args) {
        String fileName="D:/"+System.currentTimeMillis()+".xlsx";
        User user1 = new User("张三", "123456", 13, 13.13, new Date());
        User user2 = new User("李四", "444444", 14, 14.13, new Date());
        User user3 = new User("王五", "555555", 15, 15.13, new Date());
        User user4 = new User("赵六", "666666", 16, 16.13, new Date());
        List<User> users=new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        /**
         * 写多个sheet
         */
        ExcelWriter writer = EasyExcel.write(fileName).build();
        WriteSheet sheet1 = EasyExcel.writerSheet(0, "人员信息1").head(User.class).build();
        writer.write(users,sheet1);
        WriteSheet sheet2 = EasyExcel.writerSheet(1, "人员信息2").head(User.class).build();
        writer.write(users,sheet2);
        writer.finish();

        ExcelReader reader = EasyExcel.read(fileName, new ExcelListener()).build();

        ReadSheet sheet01 = EasyExcel.readSheet(0).headRowNumber(2).head(User.class).build();

        reader.read(sheet01);

        ReadSheet sheet02 = EasyExcel.readSheet(1).headRowNumber(2).head(User.class).build();

        reader.read(sheet02);

    }
}
