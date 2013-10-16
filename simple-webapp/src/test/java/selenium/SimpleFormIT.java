package selenium;


import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.base.BaseChromeIT;

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
public class SimpleFormIT extends BaseChromeIT {
    private final String URL = "http://localhost:9999/simpleForm.xhtml";

    public SimpleFormIT(){
        System.out.println("**********************************************************************");
        System.out.println(String.format("Begin tests for class : %s ", SimpleFormIT.class));
        System.out.println("**********************************************************************");
    }


    @Test
    public void testFormError(){

        webDriver.get(URL);

        writeMessage("About to test a form submit", false);
        writeMessage("With validation errors", true);


        WebElement submitBtn = webDriver.findElement(By.id("submit"));
        submitBtn.click();

        writeMessage(String.format("Error msg should be : %s",constants.getProperty("simpleForm.errorMsg")) , true);

        // wait 10
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("error-msg")));

        WebElement liError = element.findElement(By.tagName("li"));



        assertThat(liError, not(equalTo(null)));
        assertThat(liError.getText(), Matchers.equalTo(constants.getProperty("simpleForm.errorMsg")));
    }

    @Test
    public void testFormSuccessful(){

        webDriver.get(URL);

        writeMessage("About to test a form submit", false);
        writeMessage("With no validation errors", true);

        writeMessage(String.format("Sending username %s" , constants.getProperty("simpleForm.userName")), true);

        WebElement username = webDriver.findElement(By.id("username"));
        username.sendKeys(constants.getProperty("simpleForm.userName"));


        writeMessage(String.format("Selecting programming languages : [ %s, %s ]" , "Java","Scala"), true);

        for(String language : constants.getProperty("simpleForm.usedLanguages").split(","))  {
            WebElement radioBtn = webDriver.findElement(By.xpath(String.format("//input[@value='%s']", language)));
            radioBtn.click();
        }


        writeMessage(String.format("Submitting the form"), true);

        WebElement submitBtn = webDriver.findElement(By.id("submit"));
        submitBtn.click();


        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("msg")));

        writeMessage(String.format("Expected msg should be : %s",constants.getProperty("simpleForm.formOutputMessage")) , true);

        assertThat(element.getText(), Matchers.equalTo(constants.getProperty("simpleForm.formOutputMessage")));
    }
}
