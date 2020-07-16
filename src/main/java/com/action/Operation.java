package com.action;

import com.driver.Driver;
import com.logger.LoggerControler;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Operation extends Driver {
    private static LoggerControler log = LoggerControler.getLog(Operation.class);

    public void getUrl(String url){
        driver.get(url);
    }

    //封装Click方法
    public void click(By by, int time){
        Element.findElement(by,time).click();
        log.info("Click Element:"+by);
    }

    public void click(By by,int time,int d){
        Element.findElements(by, time).get(d).click();
        log.info("Click Element("+d+"):"+by);
    }

    //封装文本输入方法,结合clear和sendKeys
    public void setText(By by,int time,String text){
        WebElement element = Element.findElement(by, time);
        element.clear();
        element.sendKeys(text);
        log.info("Element:"+by+"  Input:"+text);
    }

    public void setText(By by,int time,String text,int d){
        WebElement element = Element.findElements(by, time).get(d);
        element.clear();
        element.sendKeys(text);
        log.info("Element("+d+"):"+by+"  Input:"+text);
    }

    //封装获取文本方法
    public String getText(By by,int time){
        String text= Element.findElement(by,time).getText();
        log.info("Get Element:"+by+"'s text! Its text is:"+text);
        return text;
    }

    //封装切换Frame方法,只能通过查找元素的方式进行切换
    public void switchFrame(By by,int time){
        WebElement frame = Element.findElement(by, time);
        driver.switchTo().frame(frame);
        log.info("Switch to Frame:"+frame);
    }

    //封装切换Window方法
    public void switchWindow(){
        String window = driver.getWindowHandle();    //获取当前Window
        //根据Window的值判断是否是当前Window
        for(String windows:driver.getWindowHandles()){
            if(windows.equals(window)){
                continue;
            }
            driver.switchTo().window(windows);
            log.info("Switch to Windows:"+windows);
        }
    }

    //获取当前页面的PageSource
    public String getPageSource(){
        String text = driver.getPageSource();
        log.info("Current PageSource:"+text);
        return text;
    }

    //获取当前页面的Title
    public String getTitle(){
        String text = driver.getTitle();
        log.info("Current Title:"+text);
        return text;
    }

    //获取当前页面的URL
    public String getCurrentUrl(){
        String text = driver.getCurrentUrl();
        log.info("Current URL:"+text);
        return text;
    }

    //浏览器后退操作
    public void browserBack(){
        driver.navigate().back();
        log.info("Browser Back!");
    }

    //浏览器前进操作
    public void browserForward(){
        driver.navigate().forward();
        log.info("Browser Forward!");
    }

    //浏览器最大化
    public void browserMax(){
        driver.manage().window().maximize();
        log.info("Browser Maximize!");
    }

    //浏览器按指定的尺寸缩放,需传入Dimension对象;具有两个属性width和height
    public void browserSetSize(Dimension targetSize){
        driver.manage().window().setSize(targetSize);
        log.info("Browser SetSize:"+targetSize);
    }

    //关闭所有浏览器窗口
    public void closeDrivers(){
        driver.quit();
        log.info("Close All Windows");
    }

    //关闭当前浏览器窗口
    public void closeDriver(){
        driver.close();
        log.info("Close Current Window");
    }

    //截图并保存图片
    public void getScreenshot(String path){
        try{
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile,new File(path));
        }catch (IOException e){
            log.error(e.getMessage(),e);
        }
    }

    //将截图附在Allure报告上
    public void allureScreenshot(String name){
        byte[] photo = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(name, new ByteArrayInputStream(photo));
    }

}
