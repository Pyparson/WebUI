package com.action;

import com.driver.Driver;
import com.logger.LoggerControler;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Element extends Driver {
    private static LoggerControler log = LoggerControler.getLog(Element.class);
//    public static WebElement findElement(By by,int time){
//        WebElement element = null;
//        try{
//            WebDriverWait wait = new WebDriverWait(driver,time);
//            wait.until(ExpectedConditions.presenceOfElementLocated(by));
//            element = driver.findElement(by);
//            log.info("Found Element:" + by);
//        }catch (Exception e){
//            log.error("Element:" + by + " not Found...");
//            e.printStackTrace();
//        }
//        return element;
//    }

    //显示等待查找单个控件元素
    public static WebElement findElement(By by, int time) {
        WebElement element = null;
        try {
            element = new WebDriverWait(driver, time).until(new ExpectedCondition<WebElement>() {
                public WebElement apply(@NullableDecl WebDriver input) {
                    log.info("Found Element:" + by);
                    return driver.findElement(by);
                }
            });
        } catch (Exception e) {
            log.error("Element:" + by + " not Found...");
            log.error(e.getMessage(),e);
        }
        return element;
    }

    //显示等待查找多个控件元素
    public static List<WebElement> findElements(By by, int time){
        List<WebElement> elements = null;
        try{
            elements = new WebDriverWait(driver,time).until(new ExpectedCondition<List<WebElement>>() {
                public List<WebElement> apply(@NullableDecl WebDriver input) {
                    log.info("Found Elements:" + by);
                    return driver.findElements(by);
                }
            });
        }catch (Exception e){
            log.error("Elements:" + by + " not Found...");
            log.error(e.getMessage(),e);
        }
        return elements;
    }
}
