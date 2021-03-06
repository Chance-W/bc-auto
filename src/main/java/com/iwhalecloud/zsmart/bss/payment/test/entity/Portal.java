package com.iwhalecloud.zsmart.bss.payment.test.entity;

import com.iwhalecloud.zsmart.bss.payment.test.intf.Menu;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
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

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.className("js-all-menu")));

        //把所有可能会抛出错误的代码都放在try语句块中，而把那些用于错误处理的代码放在catch块中
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(By.className("js-bz-div")));
//            driver.findElementByClassName("js-close").click();
            driver.findElement(By.xpath("/html/body/div[3]/div[3]/button[1]")).click();
        }
        catch (NoSuchElementException | TimeoutException e) {
            System.out.println(e);
        }
        driver.findElementByClassName("js-all-menu").click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("searchMenuInput"))).sendKeys(menuName);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("glyphicon-search"))).click();

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
