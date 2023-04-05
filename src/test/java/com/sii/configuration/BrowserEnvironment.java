package com.sii.configuration;

import com.sii.configuration.consts.PropertiesKeys;
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
    private Logger logger;
    private WebDriver driver;


    public BrowserEnvironment() {
        this.browserName = Optional.ofNullable(PropertyStore.getStringPropertyFromSystem(PropertiesKeys.BROWSER_NAME))
                .orElse(PropertyStore.getStringPropertyFromSystem(PropertiesKeys.DEFAULT_BROWSER_KEY));
        this.headlessBrowser = PropertyStore.getBooleanPropertyFromSystem(PropertiesKeys.BROWSER_HEADLESS);
        this.webElementTimeOut = PropertyStore.getIntPropertyFromSystem(PropertiesKeys.BROWSER_WEBELEMENT_TIMEOUT);
        this.attachScreenshot = PropertyStore.getBooleanPropertyFromSystem(PropertiesKeys.BROWSER_ATTACH_SCREENSHOTS);
        this.logger = LoggerFactory.getLogger(BrowserEnvironment.class);
    }

    public WebDriver getDriver() {
        WebDriver driver;
        switch (this.browserName) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                chromeOptions.addArguments("--start-maximised");
                driver = new ChromeDriver(chromeOptions);
                driver.get(System.getProperty(PropertiesKeys.ENVIRONMENT_URL));
                break;
            case "firefox" :
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                firefoxOptions.addArguments("--start-maximised");
                driver = new FirefoxDriver(firefoxOptions);
                driver.manage().window().maximize();
                driver.get(System.getProperty(PropertiesKeys.ENVIRONMENT_URL));
            default :
                InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver(internetExplorerOptions);
                driver.manage().window().maximize();
                driver.get((System.getProperty(PropertiesKeys.ENVIRONMENT_URL)));
        }
        this.driver = driver;
        return this.driver;
    }
}
