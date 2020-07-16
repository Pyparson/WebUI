package com.test;

import com.action.Operation;
import com.driver.Driver;
import com.logger.LoggerControler;
import com.tool.FileUnit;
import com.tool.MysqlUtil;
import io.qameta.allure.Attachment;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestLog {
//    WebDriver driver;

    @Test
    public void testLog(){
        LoggerControler log = LoggerControler.getLog(TestLog.class);
        log.info("This is info!!!");
        log.debug("This is debug!!!");
        log.error("This is error!!!");
        log.warn("This is warn!!!");
    }

    @Attachment(value = "截图",type = "image/png")
    public byte[] takePhoto(WebDriver driver){
        byte[] photo = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        return photo;
    }

//    @Test
//    public void testWeb() throws InterruptedException {
//        Operation action = new Operation();
//        WebDriver driver = Driver.openDriver("chrome");
//        driver.get("file:///Users/hfy/Documents/01_Code/WebUI/Log/index.html");
//        Thread.sleep(3000);
//        action.allureScreenshot("测试截图");
//        action.browserSetSize(new Dimension(800,800));
//        takePhoto(driver);
//        Thread.sleep(3000);
//        action.getUrl();
//        action.allureScreenshot("测试截图");
//        action.closeDrivers();
//    }

    @Test
    public void testFile(){
        FileUnit file = new FileUnit();
        String path = "/Users/hfy/Documents/01_Code/argraceng/Log/Wifi_join.txt";
        List li= file.readTxtFile(path);
        System.out.println(li);
        System.out.println(li.size());
        for (int i=0;i<li.size();i++){
            System.out.println(li.get(i));
        }
        file.writeTxtFile(path,"时间2019-09-26 20:44:03.910808!第75次配网情况:Success,耗时:33s");
    }

    @Test
    public void testYaml(){
        String rootPath = System.getProperty("user.dir")+ File.separator + "config" +
                File.separator + "config.yml";
        FileUnit file = new FileUnit();
        Map<String,Object>  text= file.getYaml(rootPath);
        System.out.println(text.get("URL"));
        System.out.println(((Map<String,Object>)text.get("Name")).get("Python").toString());
        System.out.println(((ArrayList)text.get("case")).get(0).toString());
    }

    @Test
    public void testSql(){
        MysqlUtil sql = new MysqlUtil();
        System.out.println(sql.getSqlValues("SELECT id FROM article_articlecolumn","id"));
    }


    @Test
    public void testQuery(){
        int c=2;
        System.out.println(c);
        System.out.println(c++);
        System.out.println(c--);
    }
}
