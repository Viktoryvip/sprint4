import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObject.MainPage;
import properties.Configurations;

import java.io.IOException;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;


public class OrderTest {
    @Before
    public void start() throws IOException {
        Configurations configurations = new Configurations();
        configurations.setBrowser("chrome");
    }

    String[][] testData = new String[][]{
            {"Петр", "Петров", "ул. Петровская", "79056578773", "Сокольники", "01.01.2023", "Позвонить за 10 минут до приезда", "трое суток", "black"},
            {"Иван", "Иванов", "Красная площадь", "79266123469", "Лубянка", "01.01.2023", "Позвонить за 30 минут до приезда", "семеро суток", "grey"}
    };
    @Test
    public void order_topButton() {
        // создай драйвер для браузера Chrome
        // перейди на страницу тестового стенда
        boolean  isConfirmThatOrderProcessIsSuccessful  = open(baseUrl, MainPage.class)
                // приянтие куки
                .clickCookieButton()
                // нажми на кнопку "заказать" в верхней части страницы
                .clickOrderTopButton()
                .fillFirstPage(testData[0]) // заполнение 1 страницы регистрации
                .fillSecondPage(testData[0]) // заполнение 2 страницы регистрации
                .confirmThatOrderPageLoadIsSuccessful();
        assertTrue("Ошибка", isConfirmThatOrderProcessIsSuccessful);

    }

    @Test
    public void order_downButton() {
        // перейти на страницу тестового стенда
        boolean  isConfirmThatOrderProcessIsSuccessful  = open(baseUrl, MainPage.class)
                // приянтие куки
                .clickCookieButton()
                // нажми на кнопку "заказать" в средней части страницы
                .clickOrderDownButton()
                .fillFirstPage(testData[1]) // заполнение 1 страницы регистрации
                .fillSecondPage(testData[1]) // заполнение 2 страницы регистрации
                .confirmThatOrderPageLoadIsSuccessful();
        assertTrue("Ошибка", isConfirmThatOrderProcessIsSuccessful);

    }
    @After
    public void afterOrder(){

        closeWebDriver();
    }

}