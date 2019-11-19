package com.xo.util.decorator;

import com.xo.util.LogBase;
/**
 * 后增加信息
 * */
public class LogDecoratorSuffix extends LogDecorator {
    LogBase logBase;
    private String suffix;
    public LogDecoratorSuffix(LogBase logBase){
        this.logBase = logBase;
        this.suffix="";
    }

    public LogDecoratorSuffix(LogBase logBase, String suffix){
        this.logBase = logBase;
        this.suffix= (suffix==null)?"":suffix;
    }
    @Override
    public String getLogMsg(){
        return logBase.getLogMsg()+this.suffix;
    }
}
