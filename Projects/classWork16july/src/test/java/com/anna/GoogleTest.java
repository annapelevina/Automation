package com.anna;

import org.junit.BeforeClass;
import org.junit.Test;

import static com.anna.Conditions.listNthElementsHasText;

/**
 * Created by anna.pelevina on 7/16/2015.
 */

public class GoogleTest extends BaseTest {
    GooglePage page = new GooglePage(driver);

    @BeforeClass
    public static void setup()
    {
        Configuration.timeout =8;
    }

    @Test
    public void testGoogleSearch() {
        page.open();
        page.search("Selenium");
        assertThat(listNthElementsHasText(page.results, 9, "package is used"));
}
}
