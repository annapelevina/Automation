package com.anna;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by anna.pelevina on 7/16/2015.
 */
public class GooglePage {
    @FindBy(css = "#ires .g:nth-child(10)")
    WebElement resultNumber10;
}
