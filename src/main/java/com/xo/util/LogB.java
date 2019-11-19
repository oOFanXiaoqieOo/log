package com.xo.util;

import com.xo.util.decorator.LogDecoratorDateTime;
import com.xo.util.decorator.LogDecoratorLevel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * LogBefore(MyLog)
 * 前记录模式
 */
public class LogB extends LogBase {
    public static LogB instanceLog;
    public LogB() {
        super();
    }

    public LogB(String msg) {
        super(msg);
    }

    public void log(String msg,String filePath) {
        LogBase log =new LogB(msg);
        log = new LogDecoratorDateTime(log);
        FileUtil.writePlus(filePath,log.getLogMsg());//接口升级
    }

    public void log(String msg,String level,String mN,String cN,int eN) {
        String filePath =getLevelPath(level);
        LogBase log =new LogB(msg);
        log = new LogDecoratorLevel(log,level,mN,cN,eN);
        log = new LogDecoratorDateTime(log);
        FileUtil.writePlus(filePath,log.getLogMsg());//接口升级
    }

}
