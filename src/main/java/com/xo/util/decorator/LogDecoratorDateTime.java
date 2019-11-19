package com.xo.util.decorator;

import com.xo.util.LogBase;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 前增加日期时间信息 格式yyyy-MM-dd HH:mm:ss.SSS 后面有一个空格
 * 支持自己设置输出的时间格式,示例如下
 * LogBase logBase = new LogDecoratorDateTime(logBase,pattern)
 * */
public class LogDecoratorDateTime extends LogDecorator {
    LogBase logBase;
    private String pattern="";
    public LogDecoratorDateTime(LogBase logBase){
        this.logBase = logBase;
        pattern ="yyyy-MM-dd HH:mm:ss.SSS ";//默认输出格式
    }

    public LogDecoratorDateTime(LogBase logBase,String pattern){
        this.logBase = logBase;
        this.pattern =pattern;
    }

    @Override
    public String getLogMsg(){
        return getDateStr(pattern)+logBase.getLogMsg();
    }

    private String getDateStr(String pattern){
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(now);
    }
}
