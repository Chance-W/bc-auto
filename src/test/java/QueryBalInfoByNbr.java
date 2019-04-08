import com.iwhalecloud.zsmart.bss.payment.test.entity.BalanceTransactionMenu;
import com.iwhalecloud.zsmart.bss.payment.test.entity.Portal;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class QueryBalInfoByNbr {

    @BeforeTest
    //程序初始化的一些操作
    public void setup() throws Exception {
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
    }


    /**
     * @author yue.wenjuan
     * @version 1.0
     * @CreateDate 2019-04-04
     */
    @Test
    public void qryBalByNbr() throws Exception{
        ChromeDriver driver = new ChromeDriver();
        WebDriverWait webDriverWait = new WebDriverWait(driver,20);
        Portal portal = new Portal(driver,webDriverWait);
        portal.openUrl("http://172.16.24.140/portal-web/#");
        portal.login("admin","Uportal_123");
        //通过号码查询余额
        BalanceTransactionMenu balanceTransactionMenu = portal.openMenu("balance transaction",BalanceTransactionMenu.class);
        balanceTransactionMenu.queryBal("201901260014");
        portal.refreshPage();
        //通过带前缀号码查询
        portal.openMenu("balance transaction",BalanceTransactionMenu.class);
        balanceTransactionMenu.queryBal("995201901260014");
        portal.refreshPage();
        //输入AcctCode进行查询
        portal.openMenu("balance transaction",BalanceTransactionMenu.class);
        balanceTransactionMenu.queryBal("0000005697");
        portal.refreshPage();
        //输入custname查询余额
        portal.openMenu("balance transaction",BalanceTransactionMenu.class);
        balanceTransactionMenu.queryBal("BcAutoCase-YWJ01");

    }

    @AfterTest

    public void clear(){

    }
}
