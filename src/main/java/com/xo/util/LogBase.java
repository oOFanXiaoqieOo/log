package com.xo.util;

import com.xo.util.decorator.LogBuffer;
import com.xo.util.decorator.LogDecoratorDateTime;
import com.xo.util.decorator.LogDecoratorLevel;

/**
 * 日志基类
 * 每条日志信息建议添加等级、模块号、组件号、错误号，以便查看
 *
 * */
public abstract class LogBase {
    public String logMsg="";
    private LogConfig logConfig;        //静态内部类单例模式，使用时加载一次的的配置信息
                                        //所有Log类及其子类均使用同一个配置信息

    public LogBase() {
    }

    public LogBase(String msg) {
        logMsg=msg;
    }

    public String getLogMsg() {
        return logMsg;
    }

    public void setLogMsg(String logMsg) {
        this.logMsg=logMsg;
    }
    public void log(String filePath) {
//        FileUtil.write(filePath,this.getLogMsg());
        FileUtil.writePlus(filePath, this.getLogMsg());//接口升级
    }

    public void log(String msg, String filePath) {
//        FileUtil.write(filePath,this.getLogMsg());
        FileUtil.writePlus(filePath, msg);//接口升级
    }

    public void log(String msg, String level, String mN, String cN, int eN) {
        LogBase log=new LogB(msg);
        log=new LogDecoratorLevel(log, level, mN, cN, eN);
        log=new LogDecoratorDateTime(log);
        FileUtil.writePlus(getLevelPath(level), log.getLogMsg());//接口升级
    }

    /**
     * 使用logBuffer装饰的 日志输出结束后，软件退出前，需要调用该函数，否则会出现数据丢失情况
     */
    public void logFlush() {
        if (this instanceof LogBuffer) {
            LogBuffer lb=(LogBuffer) this;
            lb.flush();
        }
    }

    /**
     * 获得对应的等级的存储路径
     */
    public String getLevelPath(String level) {
        if (level == "D") {
            return logConfig.getInstance().getDebugPath();
        } else if (level == "I") {
            return logConfig.getInstance().getInfoPath();
        } else if (level == "W") {
            return logConfig.getInstance().getWarnPath();
        } else if (level == "E") {
            return logConfig.getInstance().getErrorPath();
        } else if (level == "F") {
            return logConfig.getInstance().getFatalPath();
        } else {
            return logConfig.getInstance().getDebugPath();
        }
    }

    //只记录日志等级
    public void debug(String mN, String cN, int eN) {
        if ((logConfig.getInstance().getLevel() & LogConfig.DEBUG_FLAG) > 0) {
            log(this.getLogMsg(),  "D", mN, cN, eN);
        }
    }

    public void info(String mN, String cN, int eN) {
        if ((logConfig.getInstance().getLevel() & LogConfig.INFO_FLAG) > 0) {
            log(this.getLogMsg(),  "I", mN, cN, eN);
        }
    }

    public void warn(String mN, String cN, int eN) {
        if ((logConfig.getInstance().getLevel() & LogConfig.WARN_FLAG) > 0) {
            log(this.getLogMsg(),  "W", mN, cN, eN);
        }
    }

    public void error(String mN, String cN, int eN) {
        if ((logConfig.getInstance().getLevel() & LogConfig.ERROR_FLAG) > 0) {
            log(this.getLogMsg(),  "E", mN, cN, eN);
        }
    }

    public void fatal(String mN, String cN, int eN) {
        if ((logConfig.getInstance().getLevel() & LogConfig.FATAL_FLAG) > 0) {
            log(this.getLogMsg(),  "F", mN, cN, eN);
        }
    }

    //记录日志等级和异常提示信息，需要自己定义
    public void debug() {
        if ((logConfig.getInstance().getLevel() & LogConfig.DEBUG_FLAG) > 0) {
            log("[D]" + this.getLogMsg(), logConfig.getInstance().getDebugPath());
        }
    }

    public void info() {
        if ((logConfig.getInstance().getLevel() & LogConfig.INFO_FLAG) > 0) {
            log("[I]" + this.getLogMsg(), logConfig.getInstance().getInfoPath());
        }
    }

    public void warn() {
        if ((logConfig.getInstance().getLevel() & LogConfig.WARN_FLAG) > 0) {
            log("[W]" + this.getLogMsg(), logConfig.getInstance().getWarnPath());
        }
    }

    public void error() {
        if ((logConfig.getInstance().getLevel() & LogConfig.ERROR_FLAG) > 0) {
            log("[E]" + this.getLogMsg(), logConfig.getInstance().getErrorPath());
        }
    }

    public void fatal() {
        if ((logConfig.getInstance().getLevel() & LogConfig.FATAL_FLAG) > 0) {
            log("[F]" + this.getLogMsg(), logConfig.getInstance().getFatalPath());
        }
    }
}
