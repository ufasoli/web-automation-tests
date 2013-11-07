package selenium;


import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.base.BaseWebDriverIT;
import selenium.util.WebDriverFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 26.09.13
 * Time: 14:12
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class SimpleFormIT extends BaseWebDriverIT {
    private final String BASE_URL = "http://selenium-tests:9999/simpleForm.xhtml";

    public SimpleFormIT() {
        System.out.println("**********************************************************************");
        System.out.println(String.format("Begin tests for class : %s ", SimpleFormIT.class));
        System.out.println("**********************************************************************");
    }


    @Test
    public void testFormError() {


        for (WebDriverFactory.Driver driver : avilableDrivers) {


            WebDriver webDriver = WebDriverFactory.getDriver(driver, Boolean.parseBoolean(config.get("grid").toString()));
            webDriver.get(BASE_URL);

            writeMessage("About to test a form submit", false, webDriver);
            writeMessage("With validation errors", true, webDriver);


            WebElement submitBtn = webDriver.findElement(By.id("submit"));
            submitBtn.click();

            writeMessage(String.format("Error msg should be : %s", constants.getProperty("simpleForm.errorMsg")), true, webDriver);

            // wait 10
            WebDriverWait wait = new WebDriverWait(webDriver, 10);
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("error-msg")));

            WebElement liError = element.findElement(By.tagName("li"));


            assertThat(liError, not(equalTo(null)));
            assertThat(liError.getText(), Matchers.equalTo(constants.getProperty("simpleForm.errorMsg")));


            webDriver.quit();
        }
    }

    @Test
    public void testFormSuccessful() {

        for (WebDriverFactory.Driver driver : avilableDrivers) {


            WebDriver webDriver = WebDriverFactory.getDriver(driver, Boolean.parseBoolean(config.get("grid").toString()));
            webDriver.get(BASE_URL);

            writeMessage("About to test a form submit", false, webDriver);
            writeMessage("With no validation errors", true, webDriver);

            writeMessage(String.format("Sending username %s", constants.getProperty("simpleForm.userName")), true, webDriver);

            WebElement username = webDriver.findElement(By.id("username"));
            username.sendKeys(constants.getProperty("simpleForm.userName"));


            writeMessage(String.format("Selecting programming languages : [ %s, %s ]", "Java", "Scala"), true, webDriver);

            for (String language : constants.getProperty("simpleForm.usedLanguages").split(",")) {
                WebElement radioBtn = webDriver.findElement(By.xpath(String.format("//input[@value='%s']", language)));
                radioBtn.click();
            }


            writeMessage(String.format("Submitting the form"), true, webDriver);

            WebElement submitBtn = webDriver.findElement(By.id("submit"));
            submitBtn.click();


            WebDriverWait wait = new WebDriverWait(webDriver, 10);
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("msg")));

            writeMessage(String.format("Expected msg should be : %s", constants.getProperty("simpleForm.formOutputMessage")), true, webDriver);

            assertThat(element.getText(), Matchers.equalTo(constants.getProperty("simpleForm.formOutputMessage")));
            webDriver.quit();
        }
    }
}
