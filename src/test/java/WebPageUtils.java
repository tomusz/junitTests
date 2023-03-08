import org.openqa.selenium.WebDriver;

import java.util.function.Consumer;

public class WebPageUtils {
    protected static Consumer<WebDriver> maximiseWindow = d -> d.manage().window().maximize();
}
