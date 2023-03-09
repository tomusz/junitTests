import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Page Title Validation")
class TitleTest extends BaseTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/testData.csv")
    @Tag(TestConstants.REGRESSION)
    @Tag("{0}")
    void shouldValidateSiiPortalWebPageTitle(String url, String expectedTitle) {
        driver.get(url);
        WebPageUtils.maximiseWindow.accept(driver);
        String actualTitle = driver.getTitle();
        assertThat(actualTitle).contains(expectedTitle);
    }
}