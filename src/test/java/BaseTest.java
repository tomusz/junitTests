import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    protected static WebDriver driver;


    @BeforeAll
    static void setup() {

        WebDriverManager.chromedriver().setup();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @BeforeEach
    void setupDriver() {
        driver = new ChromeDriver();
    }
}
