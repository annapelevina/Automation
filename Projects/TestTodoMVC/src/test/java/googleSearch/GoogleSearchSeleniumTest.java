package googleSearch;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;


/**
 * Created by anna.pelevina on 7/15/2015.
 */
public class GoogleSearchSeleniumTest {

    WebDriver driver = new FirefoxDriver();
    WebDriverWait wait = new  WebDriverWait (driver, 5);

    @Test
    public void testSeleniumGoogleSearch() {

        driver.get("http://google.com/ncr");

        WebElement searchField = driver.findElement(By.cssSelector("#lst-ib"));

        searchField.sendKeys("selenium" + Keys.ENTER);

        wait.until(textToBePresentInElementLocated(By.cssSelector(".g:nth-child(10)"), "Python bindings for Selenium"));

        //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".st")));
        //assertTrue(driver.findElements(By.cssSelector(".st")).get(9).getText().contains("Python bindings for Selenium"));
        driver.quit();
    }

    //@After
    //public void quitBrowser(){
        //driver.quit();
    //}
}
