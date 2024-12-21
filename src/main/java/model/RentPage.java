package model;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class RentPage {
    private final WebDriver driver;

    private final By dateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']"); // Поле дата доставки
    private final By rentInput = By.cssSelector(".Dropdown-placeholder"); // Поле количество дней аренды
    private final By rentCountDays = By.xpath(".//div[@class='Dropdown-option' and text()='сутки']"); // Выбор количества дней аренды из списка
    private final By blackCheckbox = By.xpath(".//input[@id='black']"); // Чекбокс черный цвет
    private final By greyCheckbox = By.xpath(".//input[@id='grey']"); // Чекбокс черный цвет
    private final By commentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']"); // Поле комментарий
    private final By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']"); // Кнопка заказать
    private final By buttonYes = By.xpath(".//button[text()='Да']"); // Кнопка Да из модального окна для подтверждения заказа
    private final By orderSuccess= By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']"); // Модальное окно с текстом Заказ оформлен

    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод для выбора даты заказа самоката
    public void setDateInput(String date){
        WebElement element = driver.findElement(dateInput);
        element.sendKeys(date);
        element.sendKeys(Keys.ESCAPE);
    }
    //Метод для выбора срока аренды самоката
    public void setRentCountDays(){
        driver.findElement(rentInput).click();
        driver.findElement(rentCountDays).click();
    }
    //Метод для выбора цвета самоката
    public void setColor(String color){
        if (color.equals("черный")) {
            driver.findElement(blackCheckbox).click();
        }
        else if (color.equals("серый")) {
            driver.findElement(greyCheckbox).click();
        }
    }
    //Метод для заполнения поля Комментарий курьеру
    public void setCommentInput(String comment){
        driver.findElement(commentInput).sendKeys(comment);
    }
    //Метод для клика на кнопку заказать
    public void clickOrderButton(){
        driver.findElement(orderButton).click();
    }
    //Метод для клика на кнопку "Да" в модальном окне "Хотите оформить заказ?"
    public void clickYesButton(){
        driver.findElement(buttonYes).click();
    }
    //Метод для проверки отображения Модального окна "Заказ оформлен"
    public void  isOrderSuccess(){
        Assert.assertTrue("Элемент не отображается", driver.findElement(orderSuccess).isDisplayed());
    }

    //Метод для заполнения формы и перехода на следующий шаг
    public void fillRentPage(String date, String color, String comment){
        setDateInput(date);
        setRentCountDays();
        setColor(color);
        setCommentInput(comment);
        clickOrderButton();
        clickYesButton();
        isOrderSuccess();
    }
}
