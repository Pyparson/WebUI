package com.logger;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class LoggerControler {
    private static Logger logger =null;
    private static LoggerControler loggerControler =null;

    public static LoggerControler getLog(Class<?> T){
        if(logger==null){
            Properties prop = new Properties();
            try{
                String rootPath = System.getProperty("user.dir")+ File.separator + "config" +
                        File.separator + "log4j.properties";
                InputStream is = new FileInputStream(rootPath);
                prop.load(is);
            }catch (Exception e){
                e.printStackTrace();
            }
            PropertyConfigurator.configure(prop);
            logger = Logger.getLogger(T);
            loggerControler = new LoggerControler();
        }
        return loggerControler;
    }

    public void info(Object msg){
        logger.info(msg);
    }

    public void info(Object msg,Throwable t){
        logger.info(msg,t);
    }

    public void debug(Object msg){
        logger.debug(msg);
    }

    public void debug(Object msg,Throwable t){
        logger.debug(msg,t);
    }

    public void error(Object msg){
        logger.error(msg);
    }

    public void error(Object msg,Throwable t){
        logger.error(msg,t);
    }

    public void warn(Object msg){
        logger.warn(msg);
    }

    public void warn(Object msg,Throwable t){
        logger.warn(msg,t);
    }
}
