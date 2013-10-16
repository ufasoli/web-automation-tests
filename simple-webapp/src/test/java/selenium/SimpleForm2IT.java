package selenium;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import selenium.base.BaseChromeIT;
import selenium.pages.SimpleForm2Page;

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 16.10.13
 * Time: 09:30
 */
public class SimpleForm2IT extends BaseChromeIT {


    @Test
    public void test(){

        final String url = "http://localhost:9999/simpleForm2.xhtml";
        webDriver.get(url);

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
    }
}
