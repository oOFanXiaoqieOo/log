package com.xo.util.decorator;

/**
 * 日志标识信息及基础类
 */
public class LogLevelBase {
    private String logLevel;            //日志等级信息 [D]ebug、[I]nfo、[W]arn、[E]rror、[F]atal、[U]nknown(其他)
    private String moduleNo;            //模块名称          (如果可以转换成Int则只占两位，否则使用.分隔)
    private String componentNo;         //组件（函数）名称    (如果可以转换成Int则只占三位，否则使用.分隔)
    private int errorNo;             //错误号                占三位

    public LogLevelBase() {
        logLevel="U";
        moduleNo="0";
        componentNo="0";
        errorNo=0;
    }

    public LogLevelBase(String level, String mN, String cN, int eN) {
        logLevel=level;
        moduleNo=mN;
        componentNo=cN;
        errorNo=eN;
    }

    public String toString(){
        String ret="["+logLevel;
        ret +=".";
        try {
            Integer mN=Integer.valueOf(moduleNo);
            ret +=String.format("%02d",mN%100);//超出位数部分自动忽略
        }catch(NumberFormatException e){
            ret +=moduleNo;
        }
        ret +=".";
        try {
            Integer cN=Integer.valueOf(componentNo);
            ret +=String.format("%03d",cN%1000 );//超出位数部分自动忽略
        }catch(NumberFormatException e){
            ret +=componentNo;
        }
        ret +=".";
        ret += String.format("%05d",errorNo%100000);//超出位数部分自动忽略
        ret +="]";
        return ret;
    }
}
