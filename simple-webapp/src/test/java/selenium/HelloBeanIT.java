package selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 * Created with IntelliJ IDEA.
 * User: ULF
 * Date: 24.09.13
 * Time: 15:34
 * To change this template use File | Settings | File Templates.
 */
public class HelloBeanIT extends BaseChromeIT {

    private final String url = "http://localhost:9999/";

    @Test
    public void sayHelloTest() {

        webDriver.get(url);

        verifyPageTitle("Simple selenium tests jsf page");
        enterUserName("Trivadis");
        verifyMessage("Hello Trivadis !!!");
    }

    private void verifyPageTitle(String expectedTitle) {

        assertThat(webDriver.getTitle(), equalTo(expectedTitle));
    }

    private void enterUserName(String username) {
        // find the input text box
        WebElement element = webDriver.findElement(By.name("username"));

        // set the user name in input text box
        element.sendKeys(username);

        // submit form
        WebElement submit = webDriver.findElement(By.name("submit"));
        submit.click();
    }

    private void verifyMessage(String expectedMessage) {

        // wait until the ajax request is processed and the msg element is
        // rendered in the DOM
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("msg")));


        assertThat(element.getText(), equalTo(expectedMessage));
    }


}
