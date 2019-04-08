package com.iwhalecloud.zsmart.bss.payment.test.entity;

import com.iwhalecloud.zsmart.bss.payment.test.intf.Menu;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.ParseException;



public class BalanceTransactionMenu extends Menu {

    /** balance transaction界面进行余额查询
     * @author yue.wenjuan
     * @version 1.0
     * @CreateDate 2019-04-04
     */
        public void queryBal(String nbr){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("custName")));
        driver.findElement(By.name("custName")).sendKeys(nbr);
        driver.findElement(By.className("js-search-customer")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("js-recharge")));
    }

    /**对查询出来的号码或者账户进行充值
     * @author yue.wenjuan
     * @version 1.0
     * @CreateDate 2019-04-04
     */
    public void recharge(String accNbr,Boolean isAccNbrFlag) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("js-recharge"))).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div[3]/div/button[1]")));
        if(isAccNbrFlag){
            driver.executeScript("$('.ui-dialog').find('input[name=\"accNbr\"]').combobox('text', " + accNbr + ")");
        }

        driver.executeScript("$('.js-charge-btn').find('button:first').click()");
        driver.executeScript("$('.modal-footer').find('button:contains(Recharge)').click()");
        //选择Credit card
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("mt10")));
        driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[2]/div/div[2]/button[2]")).click();
        //点击payment按钮充值
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("sub-title")));
        driver.executeScript("$('.text-right').find('button:first').click()");
        //等待充值结果
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("icon-success")));
    }

    /**
     * @author yue.wenjuan
     * @version 1.0
     * @CreateDate 2019-04-02
     */
    public void qryHistroy (){
        driver.findElement(By.id("ui-id-4")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("js-form-query")));
        driver.findElement(By.xpath("//*[@id=\"balTransQuery\"]/form/div[3]/div/div/button[1]")).click();

    }

    public void addAccountBalance () throws ParseException {

        driver.findElement(By.xpath("//*[@id=\"tabs-acct-balance\"]/div/div/div[1]/div/div[4]/div/span[3]")).click();
        //JavascriptExecutor js=(JavascriptExecutor) driver;
        //js.executeScript("$('.ui-dialog').find('input[name=\"acctResId\"]'.combobox('value', 11294)");
        driver.executeScript("$('.ui-dialog').find('input[name=\"acctResId\"]').combobox('value', 11294)");
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/form/div[2]/div/div[1]/div[1]/input[1]")).sendKeys("20");
        //js.executeScript("$('.ui-dialog').find('input[name=\"unitId\"]'.combobox('value', GB)");
        driver.executeScript("$('.ui-dialog').find('input[name=\"unitId\"]').combobox('value', 6)");
        String dateBcFormat1= new GetTime().dateBcFormat1;
        String dateBcFormat2= new GetTime().dateBcFormat2;
        //获取当前时间
        driver.executeScript("$('input[name=\"effDate\"]').datetimepicker({format:'dd.mm.yyyy hh:ii:ss'}).val(\"" +
                dateBcFormat1 +
                "\")");
        //结束时间
        driver.executeScript("$('input[name=\"expDate\"]').datetimepicker({format:'dd.mm.yyyy hh:ii:ss'}).val(\"" +
                dateBcFormat2 +
                "\")");
        driver.findElement(By.xpath("/html/body/div[4]/div[3]/div/div/button[1]")).click();



    }



}
