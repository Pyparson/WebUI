package com.page;

import com.action.Element;
import com.action.Operation;
import org.openqa.selenium.By;

public class HomePage {
    private By home_btn = By.xpath("//li[@title=\"首页\"]");
    private By receive_btn = By.xpath("(//*[contains(text(),\"收件箱\")])[3]");
    private By mail_btn = By.xpath("//*[@title=\"选中此邮件\"]");   //收件箱的复选框
    private By delete_btn = By.xpath("//span[contains(text(),\"删 除\")]");
    private By send_btn = By.xpath("//*[contains(text(),\"写 信\")]");
    private By red_Flag = By.xpath("//span[@title=\"红旗邮件\"]");

    Operation ele = new Operation();

    public void into_Receive(){
        ele.click(receive_btn,30);
    }

    public void back_home(){
        ele.click(home_btn,30);
    }

    public void into_red(){
        ele.click(red_Flag,20);
    }

    public void del_mail(int d) throws InterruptedException {
        ele.click(receive_btn,30);
        Thread.sleep(5000);
        ele.click(mail_btn,30,d);
//        ele.click(delete_btn,30);
    }
}
