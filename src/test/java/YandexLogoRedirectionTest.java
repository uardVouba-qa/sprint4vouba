import model.MainPage;
import model.OrderPage;
import model.RentPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static model.MainPage.HOMEPAGE;
import static model.MainPage.YANDEXHOMEPAGE;

public class YandexLogoRedirectionTest {
    private final WebDriver driver = new ChromeDriver();
    MainPage mainPage = new MainPage(driver);

    @Before
    public void start() {
        driver.get(HOMEPAGE);
    }
    @Test
    public void yandexHomePageTest() {
        mainPage.clickYandexLogo();

        String originalTab = driver.getWindowHandle();

        // Ожидание появления новой вкладки
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                driver -> driver.getWindowHandles().size() > 1
        );

        // Переключение на новую вкладку
        String newTab = driver.getWindowHandles().stream()
                .filter(handle -> !handle.equals(originalTab))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("New tab was not found"));

        driver.switchTo().window(newTab);
        Assert.assertEquals("Это не главная страница Яндекса", driver.getCurrentUrl(), driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
