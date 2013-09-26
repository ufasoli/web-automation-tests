package selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import util.ConstantsLoader;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 26.09.13
 * Time: 09:22
 */
public class BaseChromeIT {

    protected WebDriver webDriver;
    protected Properties constants;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "ext\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait (2, TimeUnit.SECONDS);
        constants = ConstantsLoader.loader().getConstants();
    }

    @After
    public void tearDown() {
        webDriver.close();
    }
}
