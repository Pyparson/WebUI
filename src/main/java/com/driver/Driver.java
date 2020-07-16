package com.driver;

import com.logger.LoggerControler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.net.MalformedURLException;

public class Driver {
    public static WebDriver driver;
    private static LoggerControler log = LoggerControler.getLog(Driver.class);
    public static WebDriver openDriver(String platfrom){
        String rootPath = System.getProperty("user.dir")+ File.separator + "driver";
        if(platfrom.equals("chrome")){
            String path = rootPath + File.separator + "chromedriver";
            System.setProperty("webdriver.chrome.driver",path);
            driver = new ChromeDriver();
            log.info("Open Chrome");
        }else if (platfrom.equals("firefox")){
            String path = rootPath + File.separator + "firefoxdriver";
            System.setProperty("webdriver.firefox.bin",path);
            driver = new FirefoxDriver();
            log.info("Open Firefox");
        }else if (platfrom.equals("safari")){
            String path = rootPath + File.separator + "safaridriver";
            System.setProperty("webdriver.safari.driver",path);
            driver = new SafariDriver();
            log.info("Open Safari");
        }else if (platfrom.equals("ie")){
            String path = rootPath + File.separator + "iedriver";
            System.setProperty("webdriver.ie.driver",path);
            driver=new InternetExplorerDriver();
            log.info("Open IE");
        }else{
            log.info("Platfrom is error...");
        }
        return driver;
    }

}
