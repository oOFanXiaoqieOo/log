package com.xo.util;

import com.xo.util.decorator.LogBuffer;
import com.xo.util.decorator.LogDecoratorDateTime;
import com.xo.util.decorator.LogDecoratorSuffix;
import com.xo.util.decorator.LogLevelBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TestLog {
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testLog() throws Exception{
        LogBase log =new LogB();
        LogBase log2 =new LogB();
        for(int i =0;i<100000;i++) {
            int index = i%10;
            log =new LogB("test");
//            log.setLogMsg("test");
            log = new LogDecoratorDateTime(log);
            log = new LogDecoratorSuffix(log,"["+i+"]");
            log = new LogBuffer(log);
//            log.log("E://HONG//ReadMe"+index+".txt");
//            log.log("E://HONG//ReadMe"+index+20+".txt");
//            log.log("E://HONG//ReadMe"+index+40+".txt");
//            log.log("E://HONG//ReadMe"+index+40+".txt");
//            log.log("E://HONG//ReadMe666.txt");
            log.log("导出成功["+i+"]","I","Log","Test",0);
            log2.log("导出成功","D","Log","Test",0);
        }
        log.logFlush();//需要进行手动清除缓存

    }

    @Test
    public void testinit() throws IOException {
        LogB logb = new LogB("lalala");
        logb.debug("1","2",1);
        logb.info("1","2",2);
        logb.warn("1","2",3);
        logb.error("1","2",4);
        logb.fatal("1","2",5);
        logb.log("hahahah","D","1","2",666);
    }

    @Test
    public void logLevelinit() throws IOException {
        LogLevelBase l =new LogLevelBase("E","","",0);
        System.out.println(l.toString());
    }
}
