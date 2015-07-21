package gmailAutomation;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static gmailAutomation.credentials.Credentials.login;
import static gmailAutomation.credentials.Credentials.password;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by anna.pelevina on 7/16/2015.
 */
public class GmailSeleniumTest {

    WebDriver driver = new FirefoxDriver();
    WebDriverWait wait = new  WebDriverWait (driver, 10);

    @Test
    public void testGmailLoginSendSearchSelenium() {
//login
        driver.get("https://mail.google.com/");
        wait.until(visibilityOfElementLocated(By.id("Email")));
        driver.findElement(By.id("Email")).sendKeys(login);
        driver.findElement(By.id("next")).click();
        wait.until(visibilityOfElementLocated(By.id("Passwd")));
        driver.findElement(By.id("Passwd")).sendKeys(password);
        driver.findElement(By.id("signIn")).click();

//create email
        wait.until(visibilityOfElementLocated(By.xpath("//div[contains(text(), 'COMPOSE')]"))).click();
        wait.until(visibilityOfElementLocated(By.name("subjectbox")));
        driver.findElement(By.name("to")).sendKeys("selenidetest1@gmail.com");
        driver.findElement(By.name("subjectbox")).sendKeys("Test Gmail Subject");
        driver.findElement(By.xpath("//div[contains(text(), 'Send')]")).click();

//refresh mailbox
        wait.until(visibilityOfElementLocated(By.partialLinkText("Inbox"))).click();

//verify delivered email in the top
        wait.until(visibilityOfElementLocated(By.cssSelector("[role=\"main\"] .xT span")));
        List<WebElement> elements = driver.findElements(By.cssSelector("[role=\"main\"] .xT span"));
        //or driver.findElements(By.xpath("//span//.[contains(text(), 'Test Gmail Subject')]")
        assertEquals(elements.get(0).getText(), "Test Gmail Subject");

//verify email is only one unread
        assertEquals(driver.findElements(By.xpath("//span//.[contains(text(), 'Test Gmail Subject')]")).size(),1);

    }

    @After
    public void quitBrowser(){
        driver.quit();
    }
}
