package com.xo.util.decorator;

import com.xo.util.LogBase;
/**
 * 前增加信息
 * */
public class LogDecoratorPrefix extends LogDecorator {
    LogBase logBase;
    private String prefix;
    public LogDecoratorPrefix(LogBase logBase){
        this.logBase = logBase;
        this.prefix="";
    }

    public LogDecoratorPrefix(LogBase logBase, String prefix){
        this.logBase = logBase;
        this.prefix= (prefix==null)?"":prefix;
    }
    @Override
    public String getLogMsg(){
        return this.prefix+logBase.getLogMsg();
    }
}
