package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    public static final String HOMEPAGE = "https://qa-scooter.praktikum-services.ru/"; //URL главной страницы
    public static final String YANDEXHOMEPAGE = "https://ya.ru/"; // URL главной страницы Яндекса
    private static final String FAQQUESTIONSELEMENT = "accordion__heading-"; // часть локатора вопроса + нужно добавлять индекс в методе для выбора конкретного вопроса
    private static final String FAQANSWERELEMENT = "accordion__panel-"; // часть локатора ответа + нужно добавлять индекс в методе для выбора конкретного ответа
    private final By logoYandex = By.xpath(".//img[@alt='Yandex']"); // Логотип Yandex
    private final By orderStatusButton = By.className("Header_Link__1TAG7"); // Кнопка статус заказа
    private final By numberOrderField = By.xpath(".//input[@placeholder='Введите номер заказа']"); // Поле ввода номера заказа
    private final By goButton = By.cssSelector(".Button_Button__ra12g.Header_Button__28dPO"); // Кнопка Go для проверки статуса заказа
    public final By orderNotFound = By.xpath(".//img[@alt='Not found']"); // Изображение заказ не найден

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для получения элемента вопроса по индексу из id локатора
    public WebElement getQuestionElement(int index) {
        return driver.findElement(By.id(FAQQUESTIONSELEMENT + index));
    }

    // Метод для клика по вопросу id
    public void clickQuestionElement(WebElement question) {
        question.click();
    }

    // Получение текста ответа FAQ
    public String getAnswerTextByIndex(int index) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(FAQANSWERELEMENT + index))); // Ждет, пока элемент станет видимым
        return driver.findElement(By.id(FAQANSWERELEMENT + index)).getText();
    }
    // Метод для клика по логотипу Яндекса
    public void clickYandexLogo() {
        driver.findElement(logoYandex).click();
    }
    // Метод для проверки статуса заказа по номеру
    public void getOrderStatus(String orderNumber) {
        driver.findElement(orderStatusButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(driver.findElement(numberOrderField)));
        driver.findElement(numberOrderField).sendKeys(orderNumber);
        driver.findElement(goButton).click();
    }
    // Метод для возврата состояния отображения элемента Заказ не найден
    public boolean orderNotFound() {
        return driver.findElement(orderNotFound).isDisplayed();
    }
}
