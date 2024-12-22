import model.MainPage;
import model.OrderPage;
import model.WebDriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static model.MainPage.HOMEPAGE;
import static model.MainPage.YANDEXHOMEPAGE;

public class SamokatLogoNavTest {
    private static final String DEFAULT_BROWSER_NAME = "CHROME"; // Установи значение для выбора браузера CHROME или FIREFOX
    private WebDriver driver;

    @Before
    public void start() {
        driver = WebDriverFactory.createForName(DEFAULT_BROWSER_NAME);
        driver.get(HOMEPAGE);
        OrderPage orderPage = new OrderPage(driver);
        orderPage.clickOrder(true); // переходим на страницу заказа
    }

    @Test
    public void samokatLogoNavTest() {
        OrderPage orderPage = new OrderPage(driver);
        orderPage.clickSamokatLogo();
        Assert.assertEquals("Это не главная страница Самоката", HOMEPAGE, driver.getCurrentUrl());

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
