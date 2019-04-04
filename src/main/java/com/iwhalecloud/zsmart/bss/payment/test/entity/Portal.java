package com.iwhalecloud.zsmart.bss.payment.test.entity;

import com.iwhalecloud.zsmart.bss.payment.test.intf.Menu;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Portal {

    ChromeDriver driver;
    WebDriverWait webDriverWait;


    public Portal(ChromeDriver driver, WebDriverWait webDriverWait) {
        //构造函数不能加实现
        this.driver = driver;
        this.webDriverWait = webDriverWait;
    }

    //打开登录界面
    public void openUrl(String url){

        driver.navigate().to(url);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginUserCode")));
    }

    //输入用户名密码点击登录
    public void login(String userName, String password) {
        driver.findElement(By.xpath("//*[@id=\"loginUserCode\"]")).sendKeys(userName);
        driver.findElement(By.xpath("//*[@id=\"loginPassword\"]")).sendKeys(password);
        driver.findElement(By.className("js-login")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("js-all-menu")));
    }

    //菜单查询框查询菜单
    public <T extends Menu> T openMenu(String menuName, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("js-all-menu")));
        driver.findElement(By.className("js-all-menu")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchMenuInput"))).sendKeys(menuName);
//        driver.findElement(By.id("searchMenuInput")).sendKeys(menuName);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("glyphicon-search"))).click();
//        driver.findElement(By.className("glyphicon-search")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/section[1]/header/div/div[3]/div[1]/div[5]/div[2]/div[2]/div[1]/dl/dd/ul/li/a/span")));
        //点击查询出来的结果
        driver.findElement(By.xpath("/html/body/div/div/section[1]/header/div/div[3]/div[1]/div[5]/div[2]/div[2]/div[1]/dl/dd/ul/li/a/span")).click();

        T menu = clazz.newInstance();
        menu.setDriver(driver);
        menu.setWebDriverWait(webDriverWait);
        return menu;
    }
    public void closeUrl(){
        driver.quit();
    }
    public void refreshPage(){
        driver.navigate().refresh();
    }
}
