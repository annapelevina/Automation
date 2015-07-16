package com.anna;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by anna.pelevina on 7/16/2015.
 */
public class GooglePage {
    @FindBy(css = "#ires .g")
    List <WebElement> results;

    public void open()
    {
        driver.get("http://google.com/ncr");

    }
    public void search (String text){
        driver.findElement(By.name("q")).sendKeys("Selenium", Keys.ENTER);

    }
    public GooglePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
}

    private WebDriver driver = new FirefoxDriver();
}
