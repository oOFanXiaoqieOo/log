package com.xo.util.decorator;

import com.xo.util.LogBase;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 前增加时间信息 格式HH:mm:ss 后面有一个空格
 * */
public class LogDecoratorTime extends LogDecorator{
    LogBase logBase;
    public LogDecoratorTime(LogBase logBase){
        this.logBase = logBase;
    }
    @Override
    public String getLogMsg(){
        return getDateStr("HH:mm:ss ")+logBase.getLogMsg();
    }

    public String getLogMsg(String pattern){
        return getDateStr(pattern)+logBase.logMsg;
    }
    private String getDateStr(String pattern){
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(now);
    }
}
