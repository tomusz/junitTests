package com.sii.configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class BrowserEnvironment {
    private String browserName;
    private boolean headlessBrowser;
    private int webElementTimeOut;
    private boolean attachScreenshot;
    private String environmentName;
    private Logger logger;
    private WebDriver driver;


    public BrowserEnvironment() {
        this.browserName = Optional.ofNullable(PropertyStore.getBrowserConfiguration().getName())
                .orElse(PropertyStore.getConfiguration().getDefaultBrowser());
        this.headlessBrowser = PropertyStore.getBrowserConfiguration().isHeadless();
        this.webElementTimeOut = PropertyStore.getWebElementConfiguration().getTimeout();
        this.attachScreenshot = PropertyStore.getBrowserConfiguration().isScreenshots();
        this.environmentName = PropertyStore.getBrowserConfiguration().getEnvironment();
        this.logger = LoggerFactory.getLogger(BrowserEnvironment.class);
    }
    public BrowserEnvironment(String defaultBrowser) {
        this.browserName = Optional.ofNullable(PropertyStore.getBrowserConfiguration().getName()).orElse(defaultBrowser);
        this.headlessBrowser = PropertyStore.getBrowserConfiguration().isHeadless();
        this.webElementTimeOut = PropertyStore.getWebElementConfiguration().getTimeout();
        this.attachScreenshot = PropertyStore.getBrowserConfiguration().isScreenshots();
        this.environmentName = PropertyStore.getBrowserConfiguration().getEnvironment();
        this.logger = LoggerFactory.getLogger(BrowserEnvironment.class);
    }

    public BrowserEnvironment(String browserName, boolean headlessBrowser, int webElementTimeOut,
                              boolean attachScreenshot, String environmentName) {
        this.browserName = browserName;
        this.headlessBrowser = headlessBrowser;
        this.webElementTimeOut = webElementTimeOut;
        this.attachScreenshot = attachScreenshot;
        this.environmentName = environmentName;
    }

    public WebDriver getDriver() {
        WebDriver driver;
        switch (this.browserName) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                chromeOptions.addArguments("start-maximised");
                driver = new ChromeDriver(chromeOptions);
                driver.get(PropertyStore.getEnvironmentUnderTests().getApplicationUrl());
                break;
            case "firefox" :
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                firefoxOptions.addArguments("start-maximised");
                driver = new FirefoxDriver(firefoxOptions);
                driver.get(PropertyStore.getEnvironmentUnderTests().getApplicationUrl());
            default :
                InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver(internetExplorerOptions);
                driver.get(PropertyStore.getEnvironmentUnderTests().getApplicationUrl());
        }
        this.driver = driver;
        return this.driver;
    }
}
