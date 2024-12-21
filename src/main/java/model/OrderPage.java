package model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage {
    private final WebDriver driver;

    private final By headOrderButton = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']"); // Верхняя кнопка заказать
    private final By bottomOrderButton = By.xpath(".//button[text()='Заказать']/parent::div[@class='Home_FinishButton__1_cWm']"); // Нижняя кнопка заказать
    private final By samokatLogobutton = By.xpath(".//img[@alt='Scooter']"); // Логотип самокат в шапке сайта

    private final By nameField = By.cssSelector("input[placeholder='* Имя']"); // Поле имя
    private final By secondNameField = By.cssSelector("input[placeholder='* Фамилия']"); // Поле фамилия
    private final By addressField = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']"); // Поле адрес
    private final By metroField = By.cssSelector("input[placeholder='* Станция метро']"); // Поле Станция метро
    private final By metroStation = By.xpath(".//li[@data-index='0']"); // Станция метро первая в списке
    private final By phoneField = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']"); // Поле телефон
    private final By buttonNext = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM"); // Кнопка Далее

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод для нажатия на кнопку "Заказать" исходя из значения параметра Верхняя или Нижняя кнопка
    public void clickOrder(boolean isHead) {
        if (isHead) {
            driver.findElement(headOrderButton).click();
        }
        else {
            WebElement element = driver.findElement(bottomOrderButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            driver.findElement(bottomOrderButton).click();
        }

    }
    //Метод заполнения поля Имя
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    //Метод заполнения поля Фамилия
    public void setSecondName(String secondName) {
        driver.findElement(secondNameField).sendKeys(secondName);
    }

    //Метод заполнения поля Адрес
    public void setAddress(String addressName) {
        driver.findElement(addressField).sendKeys(addressName);
    }

    //Метод для открытия списка станций метро
    public void openStationList() {
        driver.findElement(metroField).click();
    }

    //Метод для выбора станции метро из списка
    public void choiceMetroStation() {
        driver.findElement(metroStation).click();
    }

    //Метод заполнения поля телефон
    public void setPhone(String phoneNumber) {
        driver.findElement(phoneField).sendKeys(phoneNumber);
    }

    //Метод для нажатия на кнопку Далее
    public void nextStep() {
        driver.findElement(buttonNext).click();
    }
    //Метод для нажатия на логотип Самоката
    public void clickSamokatLogo() {
        driver.findElement(samokatLogobutton).click();
    }


    //Метод для заполнения формы и перехода на следующий шаг
    public void fillOrderPage(String name, String secondName, String address, String phoneNumber, boolean isHead) {
        clickOrder(isHead); //Нажать на кнопку заказать
        setName(name); // Заполнить поле Имя
        setSecondName(secondName); // Заполнить поле Фамилия
        setAddress(address); // Заполнить поле Адрес
        openStationList(); // Открыть список станций метро
        choiceMetroStation(); // Выбрать станцию метро
        setPhone(phoneNumber); // Заполнить поле Телефон
        nextStep(); // Нажать на кнопку Далее
    }


}
