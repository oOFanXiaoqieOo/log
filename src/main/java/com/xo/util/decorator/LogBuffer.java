package com.xo.util.decorator;

import com.xo.util.FileUtil;
import com.xo.util.LogBase;
/**大量（10W以上效果明显）操作同一个文件时使用 可以提高性能*/
public class LogBuffer extends LogDecorator {
    LogBase logBase;
    private static int DEFAULT_BUFFER_SIZE=1024;
    private static String strBuff[]=new String[DEFAULT_BUFFER_SIZE];
    private static int count=0;
    private static String preFilePath="";


    public LogBuffer(LogBase logBase) {
        this.logBase=logBase;
    }

    public String getLogMsg() {
        return logBase.getLogMsg();
    }

    public void log(String filePath) {
        if (preFilePath.equals(filePath)) {
            if (count == DEFAULT_BUFFER_SIZE) {
                buffLog();
            }
            strBuff[count]=logBase.getLogMsg();
            count++;
        } else {
            buffLog();
            strBuff[count]=logBase.getLogMsg();
            count++;
            preFilePath=filePath;
        }
    }

    private void buffLog() {
        if (count > 0) {
//            FileUtil.write(preFilePath, strBuff, count);
            FileUtil.writePlus(preFilePath, strBuff, count);
            count=0;
        }
    }

    public void flush() {
        buffLog();
    }
}
