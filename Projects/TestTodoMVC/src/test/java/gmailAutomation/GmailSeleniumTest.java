package gmailAutomation;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static gmailAutomation.credentials.Credentials.login;
import static gmailAutomation.credentials.Credentials.password;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by anna.pelevina on 7/16/2015.
 */
public class GmailSeleniumTest {

    WebDriver driver = new FirefoxDriver();
    WebDriverWait wait = new  WebDriverWait (driver, 5);

    @Test
    public void testGmailLoginSendSearchSelenium() {
//login
        driver.get("https://mail.google.com/");
        driver.findElement(By.id("Email")).sendKeys(login);
        driver.findElement(By.id("next")).click();
        driver.findElement(By.id("Passwd")).sendKeys(password);
        driver.findElement(By.id("signIn")).click();

//create email
        wait.until(visibilityOfElementLocated(By.xpath("//div[contains(text(), 'COMPOSE')]"))).click();
        wait.until(visibilityOfElementLocated(By.linkText("COMPOSE"))).click();
        driver.findElement(By.name("to")).sendKeys("selenidetest1@gmail.com");
        driver.findElement(By.name("subjectbox")).sendKeys("Test Gmail Subject");
        driver.findElement(By.xpath("//div[contains(text(), 'COMPOSE')]")).click();

//refresh mailbox
        driver.findElement((By.cssSelector("a[href='#inbox']"))).click();

//verify delivered email in the top
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".y6"), "Test Gmail Subject"));
       // $$(".y6 :nth-child(1) b").get(0).shouldHave(text("Test Gmail Subject"));

//verify delivered email is only one unread
        //$$(".y6 :nth-child(1) b").filter(text("Test Gmail Subject")).shouldHaveSize(1);

    }

    @After
    public void quitBrowser(){
        driver.quit();
    }
}
