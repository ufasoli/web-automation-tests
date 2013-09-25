package selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: ULF
 * Date: 24.09.13
 * Time: 15:35
 * To change this template use File | Settings | File Templates.
 */
public class BaseChromeIT {

    protected WebDriver webDriver;


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "ext\\chromedriver.exe");
        webDriver = new ChromeDriver();

    }

    @After
    public void tearDown() {
        webDriver.close();
    }
}
