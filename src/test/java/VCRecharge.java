import com.iwhalecloud.zsmart.bss.payment.test.entity.BalanceTransactionMenu;
import com.iwhalecloud.zsmart.bss.payment.test.entity.Portal;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class VCRecharge {
    @BeforeTest
    public void setup() throws Exception{
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
    }

    @Test
    public void vcRecharge() throws Exception{
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait webDriverWait = new WebDriverWait(driver,10);
        Portal portal = new Portal(driver,webDriverWait);

        portal.openUrl("http://172.16.24.140/portal-web/#");
        portal.login("admin", "123");

        //获取vc卡密
        Path path = Paths.get("src\\VCCardFile\\20190409143927.0069_dst.card");
        List<String> list = Files.lines(path).collect(Collectors.toList());
        //利用stream方法,获取一个流, 方便进行数据的聚合处理
        String password = list.stream()
                .skip(11)
                //lambda表达式，用tab分隔成两个字符组,并取第二个字符组的第一行
                .map(str -> str.split("\\t"))
                .map(strs -> strs[1])
                .findFirst().get();

        BalanceTransactionMenu balanceTransactionMenu = portal.openMenu("balance transaction",BalanceTransactionMenu.class);
        balanceTransactionMenu.queryBal("201901260014");
        balanceTransactionMenu.vcRecharge(password,"34544595");
        //删除已使用的卡密
        list.remove(11);
        Files.write(path, list);

    }



}
