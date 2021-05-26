package com.tianxing.dr.framework.trace.logging.deprecated;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author tianxing
 */
//@Configuration
public class JwmsLoggerConfiguration implements ApplicationContextInitializer<ConfigurableApplicationContext> {





    private void config(){
//        // creates pattern layout
//        PatternLayout layout = new PatternLayout();
//        String conversionPattern = "%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}";
//        layout.setConversionPattern(conversionPattern);
//
//        // creates console appender
//        ConsoleAppender consoleAppender = new ConsoleAppender();
//        consoleAppender.setLayout(layout);
//        consoleAppender.activateOptions();
//
//        // creates file appender
//        FileAppender fileAppender = new FileAppender();
//        fileAppender.setFile("applog3.txt");
//        fileAppender.setLayout(layout);
//        fileAppender.activateOptions();
//
//        // configures the root logger
//        Logger rootLogger = Logger.getRootLogger();
//        rootLogger.setLevel(Level.DEBUG);
//        rootLogger.addAppender(consoleAppender);
//        rootLogger.addAppender(fileAppender);
//
//        // creates a custom logger and log messages
//        Logger logger = Logger.getLogger(LoggerConfiguration.class);
//        logger.debug("this is a debug log message");
//        logger.info("this is a information log message");
//        logger.warn("this is a warning log message");

    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        //加载MDC配置
//        MDC.getMDCAdapter();
//        org.apache.log4j.MDC.get("a");

    }
}
