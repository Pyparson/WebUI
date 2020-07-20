package com.test;

import com.action.Operation;
import com.driver.Driver;
import com.page.HomePage;
import com.page.LoginPage;
import org.testng.annotations.*;

import static java.lang.Thread.*;

public class TestWeb {
    Operation op = new Operation();
    LoginPage login = new LoginPage();
    HomePage home = new HomePage();

    @BeforeClass
    public void testLogin() throws InterruptedException {
        Driver.openDriver("chrome");
        op.getUrl("https://mail.163.com/");
        login.login("xxxxx","xxxxx!");
        sleep(3000);
    }

    @AfterClass
    public void close(){
        op.closeDrivers();
    }

    @BeforeMethod
    public void testHome(){
        home.back_home();
    }

    @Test
    public void testReceive(){
        home.into_Receive();
        op.allureScreenshot("testReceive");
    }

    @Test
    public void testRed(){
        home.into_red();
        op.allureScreenshot("testRed");
    }

    @Test
    public void testDel() throws InterruptedException {
        sleep(1000);
        home.del_mail(8);
        sleep(3000);
        op.allureScreenshot("testDel");
    }
}
