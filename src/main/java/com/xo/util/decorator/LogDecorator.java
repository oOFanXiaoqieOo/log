package com.xo.util.decorator;
import com.xo.util.LogBase;
/**
 * 装饰基类
 *  针对GetLogMsg方法，装饰logMsg属性
 * */
public abstract class LogDecorator extends LogBase {
    public abstract String getLogMsg();
}
