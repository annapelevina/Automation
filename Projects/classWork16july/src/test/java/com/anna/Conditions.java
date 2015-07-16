package com.anna;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

/**
 * Created by anna.pelevina on 7/16/2015.
 */
public class Conditions {
    public static ExpectedCondition<Boolean> listNthElementsHasText(
            final List<WebElement> list, final int nthElement, final String expectedText){
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver input) {
                try {
                    return list.get(nthElement).getText().contains(expectedText);
                } catch (IndexOutOfBoundsException e){
                    return false;
                }
            }

        };
    }
}
