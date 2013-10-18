package selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.base.BaseChromeIT;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 26.09.13
 * Time: 09:22
 */
public class HelloBeanIT extends BaseChromeIT {


    @Test
    public void sayHelloTest() throws InterruptedException {

        System.out.println("******************************");
        System.out.println(String.format("Begin tests for class : %s ", HelloBeanIT.class));
        System.out.println("******************************");
        final String BASE_URL = "http://localhost:9999/";
        webDriver.get(BASE_URL);

        verifyPageTitle(constants.getProperty("helloBean.pageTitle"));
        enterUserName(constants.getProperty("helloBean.userName"));
        verifyMessage(constants.getProperty("helloBean.outputMessage"));
    }

    private void verifyPageTitle(String expectedTitle) {

        assertThat(webDriver.getTitle(), equalTo(expectedTitle));
    }

    private void enterUserName(String username) throws InterruptedException {

       writeMessage("About to test submitting a username", false);

        // find the input text box
        WebElement element = webDriver.findElement(By.name("username"));


        // set the user name in input text box
        element.sendKeys(username);

        // submit form
        WebElement submit = webDriver.findElement(By.name("submit"));
        submit.click();
    }

    private void verifyMessage(String expectedMessage) {

        writeMessage(String.format("Expected result should be : %s", expectedMessage), true);
        // wait until the ajax request is processed and the msg element is
        // rendered in the DOM
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("msg")));


        assertThat(element.getText(), equalTo(expectedMessage));
        assertThat(Integer.valueOf(element.getText()), greaterThan(2));
    }


}
