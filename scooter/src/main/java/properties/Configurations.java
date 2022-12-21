package properties;

import com.codeborne.selenide.Configuration;

public class Configurations {
    public void setBrowser(String browser){
        Configuration.browser = browser;
        Configuration.startMaximized= true;
        Configuration.baseUrl = "https://qa-scooter.praktikum-services.ru/";
    }
}