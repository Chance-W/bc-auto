package com.iwhalecloud.zsmart.bss.payment.test.intf;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Menu {

    protected ChromeDriver driver;

    protected WebDriverWait webDriverWait;

    public Menu() {
    }

    public void setDriver(ChromeDriver driver) {
        this.driver = driver;
    }

    public void setWebDriverWait(WebDriverWait webDriverWait) {
        this.webDriverWait = webDriverWait;
    }
}
