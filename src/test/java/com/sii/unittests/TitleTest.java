package com.sii.unittests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Page Title Validation")
@Slf4j
class TitleTest extends BaseTest {

    @Test
    @Tag(TestConstants.REGRESSION)
    @Tag("Title")
    void shouldValidateSiiPortalWebPageTitle() {
//        driver.get(url);
//        new WebPageUtils().maximiseWindow.accept(driver);
        String actualTitle = driver.getTitle();
        log.info(String.format("Actual title is %s", actualTitle));
        assertThat(actualTitle).contains(System.getProperty("eTitle"));
    }
}