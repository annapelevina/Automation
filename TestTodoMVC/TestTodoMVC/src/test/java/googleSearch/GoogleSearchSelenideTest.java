package googleSearch;

import org.junit.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by anna.pelevina on 6/23/2015.
 */
public class GoogleSearchSelenideTest {
    @Test
    public void testSelenideGoogleSearch(){
        open("http://google.com/ncr");
        $("#lst-ib").setValue("selenide").pressEnter();
        $$(".st").get(9).shouldHave(text("Occupational Health Guideline"));
    }
}
