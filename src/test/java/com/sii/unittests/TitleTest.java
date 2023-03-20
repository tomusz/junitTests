package com.sii.unittests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Page Title Validation")
class TitleTest extends BaseTest {

    Logger logger = LoggerFactory.getLogger("com.sii.unittests.TitleTest.class");

    @ParameterizedTest
    @CsvFileSource(resources = "/testData.csv")
    @Tag(TestConstants.REGRESSION)
    @Tag("Title")
    void shouldValidateSiiPortalWebPageTitle(String url, String expectedTitle) {
        driver.get(url);
        new WebPageUtils().maximiseWindow.accept(driver);
        String actualTitle = driver.getTitle();
        logger.info(String.format("Actual title is %s", actualTitle));
        assertThat(actualTitle).contains(expectedTitle);
    }
}