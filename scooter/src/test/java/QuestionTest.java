import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObject.MainPage;
import properties.Configurations;

import java.io.IOException;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QuestionTest {

    @Before
    public void start() throws IOException {
        Configurations configurations = new Configurations();
        configurations.setBrowser("chrome");
    }

    @Test
    public void test_QuesAns() {
                // перейди на страницу тестового стенда
        MainPage mainPage = open(baseUrl, MainPage.class)
                // приянтие куки
                .clickCookieButton();
        for(int i = 0; i < mainPage.buttonsNumber(); i++) {
            mainPage
                    .clickQuestionButton(i)
                    .checkAnswer(i);
        }
    }
    @After
    public  void afterMethod2(){
        closeWebDriver();
    }

}