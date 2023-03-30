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

public class BrowserEnvironment {
    private String browserName = "chrome";
    private boolean headlessBrowser;
    private int webElementTimeOut;
    private boolean attachScreenshot;
    private Logger logger;
    private WebDriver driver;

    public BrowserEnvironment() {
        this.headlessBrowser = false;
        this.webElementTimeOut = 10;
        this.attachScreenshot = false;
        this.logger = LoggerFactory.getLogger(BrowserEnvironment.class);
        this.initBrowserSettings();
    }

    private void initBrowserSettings() {
        this.webElementTimeOut = PropertyStore.BROWSER_WEBELEMENT_TIMEOUT.isSpecified() ?
                PropertyStore.BROWSER_WEBELEMENT_TIMEOUT.getIntValue() :
                this.webElementTimeOut;
        this.attachScreenshot = PropertyStore.BROWSER_ATTACH_SCREENSHOT.isSpecified() ?
                PropertyStore.BROWSER_ATTACH_SCREENSHOT.getBooleanValue() :
                this.attachScreenshot;
        this.headlessBrowser = PropertyStore.BROWSER_HEADLESS.isSpecified() ?
                PropertyStore.BROWSER_HEADLESS.getBooleanValue() :
                this.headlessBrowser;
    }

    public WebDriver getDriver() {
        WebDriver driver;
        switch (this.browserName) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                chromeOptions.addArguments("start-maximised");
                driver = new ChromeDriver(chromeOptions);
                driver.get(System.getProperty("appUrl"));
                break;
            case "firefox" :
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                firefoxOptions.addArguments("start-maximised");
                driver = new FirefoxDriver(firefoxOptions);
                driver.get(System.getProperty("appUrl"));
            default :
                InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver(internetExplorerOptions);
                driver.get(System.getProperty("appUrl"));
        }
        this.driver = driver;
        return this.driver;
    }
}
