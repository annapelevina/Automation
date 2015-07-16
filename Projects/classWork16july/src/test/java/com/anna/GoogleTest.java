package com.anna;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

/**
 * Created by anna.pelevina on 7/16/2015.
 */

public class GoogleTest {
    static WebDriver driver = new FirefoxDriver();
    public WebElement findResultNumber10(){
        return driver.findElement(By.cssSelector("#ires .g:nth-child(10)"));
    }

    @Test
    public void testGoogleSearch(){
        driver.get("http://google.com/ncr");
        driver.findElement(By.name("q")).sendKeys("Selenium", Keys.ENTER);

        (new WebDriverWait(driver, 4)).until(textToBePresentInElement(findResultNumber10(),
                "Selenium is one of many important dietary minerals"));
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
}
