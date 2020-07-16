package com.page;

import com.action.Element;
import com.action.Operation;
import org.openqa.selenium.By;

public class LoginPage {
    private By user_filed = By.name("email");
    private By pwd_filed = By.name("password");
    private By login_btn = By.id("dologin");
    private By frame = By.xpath("//iframe[@frameborder=\"0\"]");
    Operation ele = new Operation();

    public void login(String user,String pwd){
        ele.switchFrame(frame,30);
        ele.setText(user_filed,30,user);
        ele.setText(pwd_filed,30,pwd);
        ele.click(login_btn,30);
    }
}
