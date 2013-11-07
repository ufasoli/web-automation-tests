package selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.base.BaseWebDriverIT;
import selenium.util.WebDriverFactory;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 26.09.13
 * Time: 09:22
 */
public class HelloBeanIT extends BaseWebDriverIT {


    @Test
    public void sayHelloTest() throws InterruptedException {

        System.out.println("******************************");
        System.out.println(String.format("Begin tests for class : %s ", HelloBeanIT.class));
        System.out.println("******************************");
        final String BASE_URL = "http://selenium-tests:9999/";

        for (WebDriverFactory.Driver driver : avilableDrivers) {


            WebDriver webDriver = WebDriverFactory.getDriver(driver, Boolean.parseBoolean(config.get("grid").toString()));
            webDriver.get(BASE_URL);

            verifyPageTitle(constants.getProperty("helloBean.pageTitle"), webDriver);
            enterUserName(constants.getProperty("helloBean.userName"), webDriver);
            verifyMessage(constants.getProperty("helloBean.outputMessage"), webDriver);


            webDriver.quit();
        }


    }

    private void verifyPageTitle(String expectedTitle, WebDriver webDriver) {

        assertThat(webDriver.getTitle(), equalTo(expectedTitle));
    }

    private void enterUserName(String username, WebDriver webDriver) throws InterruptedException {

        writeMessage("About to test submitting a username", false, webDriver);

        // find the input text box
        WebElement element = webDriver.findElement(By.name("username"));


        // set the user name in input text box
        element.sendKeys(username);

        // submit form
        WebElement submit = webDriver.findElement(By.name("submit"));
        submit.click();
    }

    private void verifyMessage(String expectedMessage, WebDriver webDriver) {

        writeMessage(String.format("Expected result should be : %s", expectedMessage), true, webDriver);
        // wait until the ajax request is processed and the msg element is
        // rendered in the DOM
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("msg")));


        assertThat(element.getText(), equalTo(expectedMessage));

    }


}
