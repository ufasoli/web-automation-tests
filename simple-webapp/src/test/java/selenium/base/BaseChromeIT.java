package selenium.base;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.base.BaseWebDriverIT;
import util.ConfigLoader;
import util.ConstantsLoader;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 26.09.13
 * Time: 09:22
 */
public class BaseChromeIT extends BaseWebDriverIT {


    protected Properties constants;
    protected Properties config;

    @Before
    public void setUp() {
        final String CHROME_DRIVER_PROPERTY = "chrome.driver.path";
        final String CHROME_DRIVER_SYSTEM_PROPERTY = "webdriver.chrome.driver";

        constants = ConstantsLoader.loader().getProperties();
        config = ConfigLoader.loader().getProperties();

        System.setProperty(CHROME_DRIVER_SYSTEM_PROPERTY, config.getProperty(CHROME_DRIVER_PROPERTY));
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait (5, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        webDriver.close();
        webDriver.quit();

    }
}
