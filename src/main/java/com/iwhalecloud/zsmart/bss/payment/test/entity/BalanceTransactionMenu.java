package com.iwhalecloud.zsmart.bss.payment.test.entity;

import com.iwhalecloud.zsmart.bss.payment.test.intf.Menu;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BalanceTransactionMenu extends Menu {

    //balance transaction界面进行余额查询
    public void queryBal(String nbr){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("custName")));
        driver.findElement(By.name("custName")).sendKeys(nbr);
        driver.findElement(By.className("js-search-customer")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("js-recharge")));
    }
    //对查询出来的号码进行充值
    public void recharge(String accNbr) {

        driver.findElement(By.className("js-recharge")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div[3]/div/button[1]")));
        driver.executeScript("$('.ui-dialog').find('input[name=\"accNbr\"]').combobox('text', " + accNbr + ")");

        driver.executeScript("$('.js-charge-btn').find('button:first').click()");
        driver.executeScript("$('.modal-footer').find('button:contains(Recharge)').click()");

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("mt10")));
        driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[2]/div/div[2]/button[2]")).click();

        driver.findElement(By.className("js-payment")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("close")));

        driver.executeScript("$('.ui-draggable-handle').find('button:first').click()");
    }

    public void qryHistroy (){
        driver.findElement(By.id("ui-id-4")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("js-form-query")));
        driver.findElement(By.xpath("//*[@id=\"balTransQuery\"]/form/div[3]/div/div/button[1]")).click();

    }
}
