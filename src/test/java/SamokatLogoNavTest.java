import model.MainPage;
import model.OrderPage;
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
    private final WebDriver driver = new ChromeDriver();
    OrderPage orderPage = new OrderPage(driver);

    @Before
    public void start() {
        driver.get(HOMEPAGE);
        orderPage.clickOrder(true); // переходим на страницу заказа
    }

    @Test
    public void samokatLogoNavTest() {

        orderPage.clickSamokatLogo();
        Assert.assertEquals("Это не главная страница Самоката", HOMEPAGE, driver.getCurrentUrl());

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
