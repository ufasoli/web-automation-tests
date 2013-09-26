package selenium;


import org.hamcrest.Matchers;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 26.09.13
 * Time: 14:12
 */
public class SimpleFormIT extends BaseChromeIT {

    @Test
    public void testFormError(){
        final String url = "http://localhost:9999/simpleForm.xhtml";
        webDriver.get(url);

        WebElement submitBtn = webDriver.findElement(By.id("submit"));

        submitBtn.click();

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
        final String url = "http://localhost:9999/simpleForm.xhtml";
        webDriver.get(url);

        WebElement username = webDriver.findElement(By.id("username"));
        username.sendKeys(constants.getProperty("simpleForm.userName"));

        WebElement javaRadioBtn = webDriver.findElement(By.xpath("//input[@value='Java']"));
        javaRadioBtn.click();

        WebElement scalaRadioBtn = webDriver.findElement(By.xpath("//input[@value='Scala']"));
        scalaRadioBtn.click();


        WebElement submitBtn = webDriver.findElement(By.id("submit"));

        submitBtn.click();


        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("msg")));

        assertThat(element.getText(), Matchers.equalTo(constants.getProperty("simpleForm.formOutputMessage")));
    }
}
