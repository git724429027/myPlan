package com.wang.read;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wang.pojo.User;

/**
 * @author xiaowang
 * @time 2021-09-11 23:11
 **/
public class ExcelListener extends AnalysisEventListener {


    @Override
    public void invoke(Object data, AnalysisContext context) {
        System.out.println(data.toString());
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("读取完毕！");
    }
}
