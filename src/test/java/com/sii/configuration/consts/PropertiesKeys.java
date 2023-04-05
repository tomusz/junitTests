package com.sii.configuration.consts;

public class PropertiesKeys {
    public static final String BASE_PREFIX ="";
    public static final String BROWSER_PREFIX ="browser.";
    public static final String ENVIRONMENT_PREFIX ="environment.";
    public static final String WEBELEMENT_PREFIX = BROWSER_PREFIX + "webelement.";

    public static final String DEFAULT_ENV_KEY = "defaultEnvironment";
    public static final String DEFAULT_BROWSER_KEY = "defaultBrowser";

    public static final String BROWSER_ENVIRONMENT = BROWSER_PREFIX + "environment";
    public static final String BROWSER_NAME = BROWSER_PREFIX + "name";
    public static final String BROWSER_HEADLESS = BROWSER_PREFIX + "headless";
    public static final String BROWSER_WEBELEMENT_TIMEOUT = WEBELEMENT_PREFIX + "timeout";
    public static final String BROWSER_ATTACH_SCREENSHOTS = BROWSER_PREFIX + "screenshots";

    public static final String ENVIRONMENT_URL = ENVIRONMENT_PREFIX + "appUrl";
    public static final String ENVIRONMENT_TITLE = ENVIRONMENT_PREFIX + "title";
}
