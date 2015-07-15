package gmailAutomation;

import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static gmailAutomation.Credentials.login;
import static gmailAutomation.Credentials.password;

/**
 * Created by anna.pelevina on 7/15/2015.
 */
public class GmailSelenideTest {

    @Test
    public void testGmailLoginSendSearch(){

        Configuration.timeout=10000;
//login
        open("https://mail.google.com/");
        $(By.id("Email")).setValue(login);
        $(By.id("next")).click();
        $(By.id("Passwd")).setValue(password);
        $(By.id("signIn")).click();

//create email
        $(byText("COMPOSE")).click();
        $(By.name("to")).setValue("selenidetest1@gmail.com");
        $(By.name("subjectbox")).setValue("Test Gmail Subject");
        $(byText("Send")).click();

//refresh
        $("[title='Gmail']").click();

//verify delivered email in the top
         $$(".y6 :nth-child(1) b").get(0).shouldHave(text("Test Gmail Subject"));

//verify delivered email is only one unread
        $$(".y6 :nth-child(1) b").filter(text("Test Gmail Subject")).shouldHaveSize(1);

    }
  }
