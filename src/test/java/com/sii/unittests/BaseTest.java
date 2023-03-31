package com.sii.unittests;

import com.sii.configuration.BrowserEnvironment;
import com.sii.configuration.EnvironmentProperty;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

@Slf4j
public class BaseTest {

    public static EnvironmentProperty environmentProperty;
    protected static WebDriver driver;
    private static BrowserEnvironment browserEnvironment;

    @BeforeAll
    static void setup() {

        environmentProperty = EnvironmentProperty.getInstance();
        browserEnvironment = new BrowserEnvironment();
        driver = browserEnvironment.getDriver();
        log.debug("WebDriver Initialized properly");
    }

    @AfterEach
    void teardown() {
        driver.quit();
        log.debug("driver closed.");
    }

}
