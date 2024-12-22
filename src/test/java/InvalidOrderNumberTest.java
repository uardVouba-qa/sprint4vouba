import model.MainPage;
import model.WebDriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static model.MainPage.HOMEPAGE;

public class InvalidOrderNumberTest {
    private static final String DEFAULT_BROWSER_NAME = "CHROME"; // Установи значение для выбора браузера CHROME или FIREFOX
    private WebDriver driver;

    @Before
    public void start() {
        driver = WebDriverFactory.createForName(DEFAULT_BROWSER_NAME);
        driver.get(HOMEPAGE);
    }

    @Test
    public void invalidOrderNumberTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.getOrderStatus("111");
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(mainPage.orderNotFound));
        Assert.assertTrue("Не отображается изображение 'Такого заказа нет'", mainPage.orderNotFound());

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
