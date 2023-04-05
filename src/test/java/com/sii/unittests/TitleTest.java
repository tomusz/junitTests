package com.sii.unittests;

import com.sii.configuration.PropertyStore;
import com.sii.configuration.consts.PropertiesKeys;
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
        String actualTitle = driver.getTitle();
        log.info(String.format("Actual title is %s", actualTitle));
        assertThat(actualTitle).contains(PropertyStore.getStringPropertyFromSystem(PropertiesKeys.ENVIRONMENT_TITLE));
    }
}