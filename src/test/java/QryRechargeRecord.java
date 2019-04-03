import com.iwhalecloud.zsmart.bss.payment.test.entity.BalanceTransactionMenu;
import com.iwhalecloud.zsmart.bss.payment.test.entity.Portal;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class QryRechargeRecord {
    @BeforeTest
    //程序初始化的一些操作
    public void setup() throws Exception {
        System.setProperty("webdriver.chrome.driver", "F:\\\\r90webtest\\drivers\\chromedriver.exe");
    }

    @Test
    public void testQryRechargeRecord() throws Exception{
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        Portal portal = new Portal(driver, webDriverWait);
        portal.openUrl("http://172.16.24.140/portal-web/#");
        portal.login("admin", "123");
        BalanceTransactionMenu balanceTransactionMenu = portal.openMenu("balance transaction",BalanceTransactionMenu.class);
        balanceTransactionMenu.queryBal("201901260014");
        balanceTransactionMenu.qryHistroy();
//        portal.closeUrl();
    }

    @AfterTest
    public void clear(){

    }
}