package com.xo.util.decorator;

import com.xo.util.LogBase;
/**
 * 增加日志标识
 * 组装成String格式
 * 形如[D00.000.00000]
 * */
public class LogDecoratorLevel extends LogDecorator {
    LogBase logBase;
    LogLevelBase logLevelBase;
    public LogDecoratorLevel(LogBase logBase){
        this.logBase = logBase;
        this.logLevelBase = new LogLevelBase();
    }

    public LogDecoratorLevel(LogBase logBase,LogLevelBase l){
        this.logBase = logBase;
        this.logLevelBase = l;
    }

    public LogDecoratorLevel(LogBase logBase,String level,String mN,String cN,int eN){
        this.logBase = logBase;
        this.logLevelBase = new LogLevelBase(level,mN,cN,eN);
    }
    @Override
    public String getLogMsg(){
        return (this.logLevelBase.toString() + logBase.getLogMsg());
    }
}
