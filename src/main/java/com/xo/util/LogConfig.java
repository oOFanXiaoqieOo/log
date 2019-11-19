package com.xo.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**配置文件
 * 内部静态单例模式
 * 只加载一次配置文件*/
public class LogConfig {
    public static final int DEBUG_FLAG=0x01;
    public static final int INFO_FLAG=0x02;
    public static final int WARN_FLAG=0x04;
    public static final int ERROR_FLAG=0x08;
    public static final int FATAL_FLAG=0x10;
    private Properties properties=new Properties();
    private int level=0x0;//按位对应日志等级的输出开关 0x1对应debug 0x8对应error 0x1f标识所有标识全开
    private String debugPath="./ReadMe.txt";
    private String infoPath="./ReadMe.txt";
    private String warnPath="./ReadMe.txt";
    private String errorPath="./ReadMe.txt";
    private String fatalPath="./ReadMe.txt";

    private LogConfig() {
        init();
    }

    private static class Inner {
        private static LogConfig instance=new LogConfig();

    }

    public static LogConfig getInstance(){
        return Inner.instance;
    }

    public int getLevel() {
        return level;
    }

    public String getDebugPath() {
        return debugPath;
    }

    public String getInfoPath() {
        return infoPath;
    }

    public String getWarnPath() {
        return warnPath;
    }

    public String getErrorPath() {
        return errorPath;
    }

    public String getFatalPath() {
        return fatalPath;
    }


    /**
     * 选择配置文件
     */
    public void init(String cfgName) {
        //首先加载jar外部配置文件
        try {
            FileInputStream fin=new FileInputStream(cfgName);
            if (fin != null) {
                //使用properties对象加载输入流
                properties.load(fin);
                loadConfig();
            }
        } catch (FileNotFoundException e) {
            // 使用ClassLoader加载本项目包中的properties配置文件生成对应的输入流
            InputStream in=LogB.class.getClassLoader().getResourceAsStream(cfgName);
            if (in != null) {
                try {
                    properties.load(in);
                    loadConfig();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //选择默认的配置文件
    public void init() {
        init("mylog.properties");
    }

    /**
     * 加载配置文件中的配置信息，没有则使用默认数据
     */
    private void loadConfig() {
        debugPath=properties.getProperty("DEBUG") == null ? debugPath : properties.getProperty("DEBUG");
        infoPath=properties.getProperty("INFO") == null ? infoPath : properties.getProperty("INFO");
        warnPath=properties.getProperty("WARN") == null ? warnPath : properties.getProperty("WARN");
        errorPath=properties.getProperty("ERROR") == null ? errorPath : properties.getProperty("ERROR");
        fatalPath=properties.getProperty("FATAL") == null ? fatalPath : properties.getProperty("FATAL");
        String level=properties.getProperty("level") == null ? "ALL" : properties.getProperty("level");
        if (level.contains("ALL")) {
            this.level=0x1f;
        } else if (level.contains("OFF")) {
            this.level=0x0;
        } else {
            if (!level.contains("FATAL") && !level.contains("FATAL") && !level.contains("ERROR") && !level.contains("WARN") && !level.contains("INFO") && !level.contains("DEBUG")) {//没有关键字，当ALL处理
                this.level=0x1f;
            } else {
                if (level.contains("DEBUG")) {
                    this.level=this.level | DEBUG_FLAG;
                    if (level.contains("DEBUG+")) {
                        this.level=this.level | 0x1e;
                        //this.level=this.level | (~DEBUG_FLAG&0x1f);
                    }
                }
                if (level.contains("INFO")) {
                    this.level=this.level | INFO_FLAG;
                    if (level.contains("INFO+")) {
                        this.level=this.level | 0x1c;
                    } else if (level.contains("INFO-")) {
                        this.level=this.level | 0x1;
                    }
                }
                if (level.contains("WARN")) {
                    this.level=this.level | WARN_FLAG;
                    if (level.contains("WARN+")) {
                        this.level=this.level | 0x18;
                    } else if (level.contains("WARN-")) {
                        this.level=this.level | 0x3;
                    }
                }
                if (level.contains("ERROR")) {
                    this.level=this.level | ERROR_FLAG;
                    if (level.contains("ERROR+")) {
                        this.level=this.level | 0x10;
                    } else if (level.contains("ERROR-")) {
                        this.level=this.level | 0x7;
                    }
                }
                if (level.contains("FATAL")) {
                    this.level=this.level | FATAL_FLAG;
                    if (level.contains("FATAL-")) {
                        this.level=this.level | 0xf;
                    }
                }
            }
        }
    }

}
