package selenium.base;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.util.WebDriverFactory;
import util.ConfigLoader;
import util.ConstantsLoader;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 15.10.13
 * Time: 13:26
 */
public class BaseWebDriverIT {



    protected WebDriverFactory.Driver [] avilableDrivers = {WebDriverFactory.Driver.CHROME, WebDriverFactory.Driver.FIREFOX};

    public final String DEFAULT_MSG_DIV_ID = "#demo-message";
    public final String DEFAULT_MSG_WRAPPER_DIV_ID = "#demo-message-wrapper";


    protected Properties constants;
    protected Properties config;

    @Before
    public void setUp() {


        constants = ConstantsLoader.loader().getProperties();
        config = ConfigLoader.loader().getProperties();
    }



    /**
     * Writes a message to a pre-defined div in the template.xhtml page
     * @see BaseWebDriverIT#DEFAULT_MSG_DIV_ID
     * @see BaseWebDriverIT#DEFAULT_MSG_WRAPPER_DIV_ID
     * @param message the message to write on the page
     * @param append whether to append the message to the existing text ( a <br /> will be prepended to the message)
     */
    protected void writeMessage(String message, Boolean append, WebDriver webDriver){

        JavascriptExecutor jsExecutor = ((JavascriptExecutor)webDriver);
        jsExecutor.executeScript(String.format("$('%s').show();", DEFAULT_MSG_WRAPPER_DIV_ID));

        WebDriverWait wait = new WebDriverWait(webDriver, 10, 3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(DEFAULT_MSG_DIV_ID.replace("#", ""))));

        if(append){
            // prepend line break when appending messages
            jsExecutor.executeScript(String.format("$('%s').append('<br />');",DEFAULT_MSG_DIV_ID));
            jsExecutor.executeScript(String.format("$('%s').append('%s');", DEFAULT_MSG_DIV_ID, message)) ;
        }
        else{
            jsExecutor.executeScript(String.format("$('%s').html('%s');", DEFAULT_MSG_DIV_ID, message)) ;
        }
        new WebDriverWait(webDriver, 10, 3000).until(
                ExpectedConditions.textToBePresentInElement(By.id(DEFAULT_MSG_DIV_ID.replace("#", "")), message));
    }
}
