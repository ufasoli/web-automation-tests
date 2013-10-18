package selenium.base;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.base.BaseWebDriverIT;
import selenium.util.WebDriverFactory;
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


        constants = ConstantsLoader.loader().getProperties();
        config = ConfigLoader.loader().getProperties();

        webDriver = WebDriverFactory.getDriver(WebDriverFactory.Driver.CHROME, true);
    }

    @After
    public void tearDown() {
        webDriver.close();
        webDriver.quit();

    }
}
