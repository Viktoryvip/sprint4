package pageObject;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

public class OrderPage {

    @FindBy(xpath = ".//input[@placeholder='* Имя']")
    private SelenideElement formName;


    @FindBy(xpath = ".//input[@placeholder='* Фамилия']")
    private SelenideElement formSurname;


    @FindBy(xpath = ".//input[@placeholder='* Адрес: куда привезти заказ']")
    private SelenideElement formAddress;

    // метро
    @FindBy(how = How.CLASS_NAME,using = "select-search__value")
    private SelenideElement buttonStation;


    @FindBy(xpath = ".//*[@placeholder='* Телефон: на него позвонит курьер']")
    private SelenideElement formPhoneNum;

    //  кнопка "Далее"
    @FindBy(how = How.XPATH, using = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']")
    private SelenideElement buttonNext;

    // указание даты
    @FindBy(how = How.XPATH,using = ".//*[@placeholder=\"* Когда привезти самокат\"]")
    private SelenideElement dateInput;

    // выпадающий список срока аренды
    @FindBy(how = How.CLASS_NAME,using = "Dropdown-root")
    private SelenideElement buttonRentTerm;

    // комментарий для курьера
    @FindBy(xpath = ".//*[@placeholder='Комментарий для курьера']")
    private SelenideElement formComment;

    // кнопка "Заказать" в конце 2 страницы регистрации заказа
    //    @FindBy(xpath = "//*[contains(text(), 'Заказать')]")
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[3]/button[2]")
    private SelenideElement buttonEndOrder;

    // конпка "Да"
    @FindBy(xpath = "//*[contains(text(), 'Да')]")
    private SelenideElement buttonYes;

    // форма окончания регистрации заказа
    @FindBy(className = "Order_Modal__YZ-d3")
    private SelenideElement formConfirm;

    // кнопка "Назад"
    @FindBy(xpath = "//*[contains(text(), 'Назад')]")
    private SelenideElement buttonBack;

    // список сроков аренды
    @FindBy(how = How.XPATH, using = ".//*[@class='Dropdown-option']")
    public ElementsCollection buttonsRentTerm;

    // список станций метро
    @FindBy(how = How.CLASS_NAME, using = "Order_Text__2broi")
    private ElementsCollection buttonsStations;

     //  выбора из выпадающего списка срока аренды
    private OrderPage ChooseRentTerm(String text){
        buttonRentTerm.click();
        buttonsRentTerm.findBy(exactText(text)).click();
        return this;
    }

    //выбор цвета самоката
    private OrderPage ChooseColor(String color){
        $(By.id(color)).click();
        return this;
    }

    // выбор станции метро
    private OrderPage ChooseStation(String station){
        buttonStation.click();
        buttonsStations.findBy(exactText(station)).click();
        return this;
    }

    // заполнение полей 1 страницы создания заказа
    public OrderPage fillFirstPage(String[] data){
        formName.isDisplayed(); //проверка на прогрузку 1 страницы регистрации
        formName.setValue(data[0]); //Имя
        formSurname.setValue(data[1]); //Фамилия
        formAddress.setValue(data[2]); //Адрес
        formPhoneNum.setValue(data[3]); //Телефон
        ChooseStation(data[4]);//метро
        buttonNext.click();//переход на 2 страницу
        formComment.isDisplayed(); // проверка отображения формы комментария
        return this;
    }

    // заполнение полей 2 страницы создания заказа
    public OrderPage fillSecondPage(String[] data){
        formComment.setValue(data[6]); //коментрий
        ChooseRentTerm(data[7]); //срок аренды
        ChooseColor(data[8]); //цвет самоката
        dateInput.click();
        dateInput.setValue(data[5]); // дата начала аренды
        buttonEndOrder.click();
        return this;
    }
      //форма подтверждения заказа, баг- кнопка неактивна
    public Boolean confirmThatOrderPageLoadIsSuccessful(){
        return  formConfirm.isDisplayed();
    }
}