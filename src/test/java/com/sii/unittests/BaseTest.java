package com.sii.unittests;

import com.sii.configuration.BrowserEnvironment;
import com.sii.configuration.EnvironmentProperty;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class BaseTest {

    protected static WebDriver driver;
    private static BrowserEnvironment browserEnvironment;
    public static EnvironmentProperty environmentProperty;

    @BeforeAll
    static void setup() {

//        environmentProperty = EnvironmentProperty.getInstance();
//        browserEnvironment = new BrowserEnvironment();
//        driver = browserEnvironment.getDriver();
        log.debug("WebDriver Initialized properly");
    }

    @AfterEach
    void teardown() {
        driver.quit();
        log.debug("driver closed.");
    }

//    @BeforeEach
//    void setupDriver() {
//        driver = new ChromeDriver();
//    }
}
