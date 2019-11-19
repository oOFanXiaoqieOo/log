package com.xo.util.decorator;

import com.xo.util.LogBase;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 前增加日期信息 格式yyyy-MM-dd 后面有一个空格
 * */
public class LogDecoratorDate extends LogDecorator {
    LogBase logBase;
    public LogDecoratorDate(LogBase logBase){
        this.logBase = logBase;
    }
    @Override
    public String getLogMsg(){
        return getDateStr("yyyy-MM-dd ")+logBase.getLogMsg();
    }

    public String getLogMsg(String pattern){
        return getDateStr(pattern)+logBase.getLogMsg();
    }
    private String getDateStr(String pattern){
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(now);
    }
}
