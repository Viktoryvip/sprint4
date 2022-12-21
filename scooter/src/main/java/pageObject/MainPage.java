package pageObject;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static org.testng.Assert.assertEquals;

    public class MainPage {

        // куки
        @FindBy(how = How.ID, using = "rcc-confirm-button")
        private SelenideElement buttonСookie;

        //  кнопка "Заказать" сверху
        @FindBy(how = How.CLASS_NAME, using = "Button_Button__ra12g")
        private SelenideElement buttonOrderTop;

        // кнопка "Заказать" снизу
        @FindBy(how = How.CLASS_NAME, using = "Home_FinishButton__1_cWm")
        private SelenideElement buttonOrderDown;

        // вопросы
        @FindBy(how = How.XPATH, using = ".//*[@class='accordion__heading']")
        private ElementsCollection buttonsQuestion;

        // ответы
        @FindBy(how = How.XPATH, using = ".//*[@class='accordion__panel']")
        private ElementsCollection buttonsAnswer;

        // кнопка сверху "статус заказа"
        @FindBy(how = How.CLASS_NAME, using = "Header_Link__1TAG7")
        private SelenideElement buttonStatus;

        // вернуть количество кнопок вопросов
        public int buttonsNumber() {
            return buttonsQuestion.size();
        }

        // нажать кнопку "Заказать" вверху страницы
        public OrderPage clickOrderTopButton() {
            buttonOrderTop.isDisplayed();
            buttonOrderTop.click();
            return page(OrderPage.class);
        }

        // нажать на кнопку заказа в середине страницы
        public OrderPage clickOrderDownButton() {
            buttonOrderDown.click();
            return page(OrderPage.class);
        }

        // нажать на вопрос
        public MainPage clickQuestionButton(int number) {
            buttonsQuestion.get(number).click();
            return this;
        }

        // проверить соответствие ответа и вопроса
        public MainPage checkAnswer(int number) {
            String answer = $(byXpath(".//*[@class=\'accordion__panel\' and not(@hidden)]")).innerText();
                     // номер вопроса и активный текст ответа
            assertEquals(buttonsAnswer.get(number).getText(), answer);
            return this;
        }

        //нажать на кнопку принятия куки
        public MainPage clickCookieButton() {
            if (buttonСookie.isDisplayed())
                buttonСookie.click();
            return this;
        }

    }