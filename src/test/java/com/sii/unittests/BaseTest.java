package com.sii.unittests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {

    protected WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger("com.sii.unittests.BaseTest.class");


    @BeforeAll
    static void setup() {
        WebDriverManager.chromedriver().setup();
        logger.debug("WebDriver Initialized properly");
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
