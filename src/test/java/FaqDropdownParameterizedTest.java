import model.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static model.MainPage.HOMEPAGE;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FaqDropdownParameterizedTest {


    private final WebDriver driver = new ChromeDriver();
    private final int index;              // Индекс FAQ-вопроса
    private final String expectedText;   // Ожидаемый текст ответа

    MainPage mainPage = new MainPage(driver);
    public FaqDropdownParameterizedTest(int index, String expectedText) {
        this.index = index;
        this.expectedText = expectedText;
    }

    @Parameterized.Parameters
    public static Object[][] faqData() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."}, // Вопрос 1
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."}, // Вопрос 2
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."}, // Вопрос 3
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."}, // Вопрос 4
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."}, // Вопрос 5
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."}, // Вопрос 6
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."}, // Вопрос 7
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."} // Вопрос 8
        };
    }

    @Before
    public void start() {
        driver.get(HOMEPAGE);
    }

    @Test
    public void testFaqDropdownAnswers() {


        WebElement questionElement = mainPage.getQuestionElement(index);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionElement);
        mainPage.clickQuestionElement(questionElement);
        String actualText = mainPage.getAnswerTextByIndex(index);

        assertEquals("Текст не совпадает", expectedText, actualText);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
