package com.kuang.study;

import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.ansi.AnsiColor;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.StaticLog;
import cn.hutool.log.dialect.console.ConsoleColorLog;
import cn.hutool.log.dialect.console.ConsoleColorLogFactory;
import cn.hutool.log.dialect.console.ConsoleLogFactory;
import cn.hutool.log.level.Level;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


class StudyApplicationTests {

    @Test
    void colorLog() {

        //ConsoleColorLog.setColorFactory(AnsiColor.YELLOW);
        LogFactory.setCurrentLogFactory(ConsoleColorLogFactory.class);

        StaticLog.debug("This is static {} log", "debug");
        StaticLog.info("This is static {} log", "info");
        StaticLog.error("This is static {} log", "error");
        StaticLog.warn("This is static {} log", "warn");
        StaticLog.trace("This is static {} log", "trace");
    }
@Test
    void  commonLog(){
    LogFactory.setCurrentLogFactory(ConsoleLogFactory.class);
    StaticLog.debug("This is static {} log", "debug");
    StaticLog.info("This is static {} log", "info");
}
}
