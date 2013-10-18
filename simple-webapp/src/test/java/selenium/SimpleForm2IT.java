package selenium;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.base.BaseWebDriverIT;
import selenium.pages.SimpleForm2Page;
import selenium.util.WebDriverFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 16.10.13
 * Time: 09:30
 */
public class SimpleForm2IT extends BaseWebDriverIT {


   @Test
    public void test(){

        final String BASE_URL = "http://localhost:9999/simpleForm2.xhtml";



       for (WebDriverFactory.Driver driver : avilableDrivers) {


           WebDriver webDriver = WebDriverFactory.getDriver(driver, true);
           webDriver.get(BASE_URL);



           // init the page object with the different html elements
           // already looked by id
           SimpleForm2Page page = PageFactory.initElements(webDriver, SimpleForm2Page.class);

           // send the username to the input text
           page.sendUsername(constants.getProperty("simpleForm2.userName"));

           // select the appropriate frameworks
           // and check for message returned by the server
           page.selectFrameworks(
                   constants.getProperty("simpleForm2.formOutputMessage"),
                   webDriver,
                   constants.getProperty("simpleForm2.usedFrameworks").split(",")
           );

           webDriver.quit();
       }



    }
}
