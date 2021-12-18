package com.wang.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.wang.pojo.User;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author xiaowang
 * @time 2021-09-10 22:42
 **/
public class Test1 {



    @Test
    public void test1(){
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
        EasyExcel.write(fileName,User.class).sheet("sheet1").doWrite(users);
    }

    @Test
    public void test2() throws MalformedURLException {
        String fileName="D:/"+System.currentTimeMillis()+".xlsx";
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("name","张三");
        map.put("age",13);
        map.put("money",13.13);
        Map<String,Object> map1=new HashMap<String, Object>();
        map1.put("name","李四");
        map1.put("age",14);
        map1.put("money",14.13);
        Map<String,Object> map2=new HashMap<String, Object>();
        map2.put("name","王五");
        map2.put("age",15);
        map2.put("money",15.13);
        List<Map<String,Object>> datas=new ArrayList<Map<String,Object>>();
        datas.add(map);
        datas.add(map1);
        String[] titleNames = {"姓名", "年龄", "钱","封面图"};
        //在图片的字段后面加上showImg，不然出来的是图片链接
        String[] listKeys = {"name", "age", "money","covershowImg"};
        EasyExcel.write(fileName).head(head(titleNames)).sheet("sheet1").doWrite(dataList(datas,listKeys));
    }


    public static List<List<String>> head(String[] array) {
        List<List<String>> list = new ArrayList<List<String>>();
        for (String s : array) {
            List<String> head = new ArrayList<String>();
            head.add(s);
            list.add(head);
        }
        return list;
    }


    public static List<List<Object>> dataList(List<Map<String, Object>> list, String[] listKey) throws MalformedURLException {
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        for (Map<String, Object> map : list) {
            List<Object> data = new ArrayList<Object>();
            for (String s : listKey) {
                if (map.get(s) == null) {
                    data.add("");
                } else {
                    //数据格式处理 发现包含showImg字段就展示网络图片（简单的判断）
                    //也可以根据自己的需求进行格式化操作都放在这里
                    Object obj = map.get(s);
                    if(s.contains("showImg")  && obj.toString().contains("http")){
                        data.add(new URL(obj.toString()));
                    }else {
                        data.add(obj.toString());
                    }
                }
            }
            dataList.add(data);
        }
        return dataList;
    }


    /**
     * web浏览器写 自动列宽
     * 导出图片只支持单张，需要导出就在listkey的图片字段拼接showImg
     * @param response
     * @param fileName  文件名称
     * @param headArray 文件头
     * @param listKey   list key
     * @param dataList  数据list
     */
    public static void download(HttpServletResponse response, String fileName, String[] headArray, String[] listKey, List<Map<String, Object>> dataList) throws IOException {
        try {
            EasyExcel.write(outputStream(response, fileName)).head(head(headArray))
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet()
                    .doWrite(dataList(dataList, listKey));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("导出失败");
        }
    }

    public static OutputStream outputStream(HttpServletResponse response, String fileName) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //防止中文乱码
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        return response.getOutputStream();
    }

    public void download(HttpServletResponse response) throws IOException {
        //查询出的数据
        String[] titleNames = {"姓名", "年龄", "性别","封面图"};
        //在图片的字段后面加上showImg，不然出来的是图片链接
        String[] listKeys = {"name", "age", "sex","covershowImg"};
    }

}
