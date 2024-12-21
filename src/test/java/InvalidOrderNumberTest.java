import model.MainPage;
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
    private final WebDriver driver = new ChromeDriver();
    MainPage mainPage = new MainPage(driver);

    @Before
    public void start() {
    driver.get(HOMEPAGE);
    }

    @Test
    public void invalidOrderNumberTest() {

        mainPage.getOrderStatus("111");
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(mainPage.orderNotFound));
        Assert.assertTrue("Не отображается изображение 'Такого заказа нет'", mainPage.orderNotFound());

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
