package selenium.util;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import util.ConfigLoader;
import util.ConstantsLoader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 18.10.13
 * Time: 09:43
 */
public class WebDriverFactory {

    /**
     * TODO: implement logic for missing drivers
     */
    public static enum Driver {
        CHROME,
        FIREFOX,
        OPERA,
        IE,
        SAFARI
    }


    protected static Properties config;
    protected static Properties constants;
    protected static URL hubURL;


    static {

        final String CHROME_DRIVER_PROPERTY = "chrome.driver.path";
        final String CHROME_DRIVER_SYSTEM_PROPERTY = "webdriver.chrome.driver";
        final String HUB_URL = "selenium.grid.hub.url";

        constants = ConstantsLoader.loader().getProperties();
        config = ConfigLoader.loader().getProperties();

        System.setProperty(CHROME_DRIVER_SYSTEM_PROPERTY, config.getProperty(CHROME_DRIVER_PROPERTY));


        try {
            hubURL = new URL(config.getProperty(HUB_URL));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }


    public static WebDriver getDriver(Driver driver, Boolean grid) {


        switch (driver) {

            case CHROME:
                if (grid) {

                    return
                            new RemoteWebDriver(hubURL, DesiredCapabilities.chrome());
                } else {
                    return new ChromeDriver();
                }

            case FIREFOX:
                if (grid) {

                    return
                            new RemoteWebDriver(hubURL, DesiredCapabilities.firefox());
                } else {
                    return new FirefoxDriver();
                }

            default:
                throw new RuntimeException(String.format("The driver : %s was not recognized or cannot be processed", driver));

        }
    }
}
