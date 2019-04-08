import com.iwhalecloud.zsmart.bss.payment.test.entity.BalanceTransactionMenu;
import com.iwhalecloud.zsmart.bss.payment.test.entity.Portal;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class PrepaidRecharge {
    @BeforeTest
    //程序初始化的一些操作
    public void setup() throws Exception {
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
    }


    /**
     * @author yue.wenjuan
     * @version 1.0
     * @CreateDate 2019 -04-04
     */
    @Test
    public void testRecharge() throws Exception {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        Portal portal = new Portal(driver, webDriverWait);
        portal.openUrl("http://172.16.24.140/portal-web/#");
        portal.login("admin", "Uportal_123");
        //通过号码充值
        BalanceTransactionMenu balanceTransactionMenu = portal.openMenu("balance transaction",BalanceTransactionMenu.class);
        balanceTransactionMenu.queryBal("201901260014");
        balanceTransactionMenu.recharge("201901260014",true);
        portal.refreshPage();
        //通过账户充值
        portal.openMenu("balance transaction",BalanceTransactionMenu.class);
        balanceTransactionMenu.queryBal("0000005697");
        balanceTransactionMenu.recharge("0000005697",false);

//        portal.closeUrl();
    }


    @AfterTest

    public void clear(){

    }
}
