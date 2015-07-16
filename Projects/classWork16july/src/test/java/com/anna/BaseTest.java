package com.anna;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by anna.pelevina on 7/16/2015.
 */
public class BaseTest {
    static WebDriver driver = new FirefoxDriver();

    public void waitUntil (int timeout, ExpectedCondition<?> condition)
    {
        (new WebDriverWait(driver, timeout)).until(condition);
    }
    public void assertThat (ExpectedCondition <?> condition)
    {
        waitUntil (Configuration.timeout,condition);
    }
    static int timeout = 8;

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
}
