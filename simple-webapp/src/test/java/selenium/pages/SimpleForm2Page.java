package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 16.10.13
 * Time: 09:22
 */
public class SimpleForm2Page {

    private WebElement usedFrameworks;
    private WebElement username;
    private WebElement submit;
    private WebElement msg;


    public void sendUsername(String _username){

        username.sendKeys(_username);
    }

    public void selectFrameworks(String expectedMessage, WebDriver webDriver, String ... frameworks){

        if(frameworks != null ){

            for(String framework : frameworks){
                WebElement option = usedFrameworks.findElement(By.xpath(String.format("//option[@value='%s']", framework)));

                if(option !=null){
                    option.click();
                }
            }

        }

        submit.click();

        WebDriverWait waitAjax = new WebDriverWait(webDriver, 10);
        waitAjax.until(ExpectedConditions.visibilityOfElementLocated(By.id("msg")));
        assertThat(msg.getText(), equalTo(expectedMessage));


    }


    public WebElement getUsedFrameworks() {
        return usedFrameworks;
    }

    public void setUsedFrameworks(WebElement usedFrameworks) {
        this.usedFrameworks = usedFrameworks;
    }

    public WebElement getUsername() {
        return username;
    }

    public void setUsername(WebElement username) {
        this.username = username;
    }

    public WebElement getSubmit() {
        return submit;
    }

    public void setSubmit(WebElement submit) {
        this.submit = submit;
    }

    public WebElement getMsg() {
        return msg;
    }

    public void setMsg(WebElement msg) {
        this.msg = msg;
    }
}
