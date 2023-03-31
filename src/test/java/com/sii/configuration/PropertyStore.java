package com.sii.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public enum PropertyStore {
    BROWSER("browser"), BROWSER_WEBELEMENT_TIMEOUT("browser.webElement.timeout"),
    BROWSER_HEADLESS("browser.headless"), ENVIRONMENT("browser.environment"),
    BROWSER_ATTACH_SCREENSHOT("browser.screenshots");

    public static final String CONFIG_PROP = "config.properties";
    public static final String CONFIG_YAML = "config.yaml";
    private static Properties properties = null;
    private String propertyKey;
    private String value;

    PropertyStore(String key) {
        this.propertyKey = key;
        this.value = this.retrieveValue(key);
    }

    private String retrieveValue(String key) {
        return System.getProperty(key) != null ? System.getProperty(key) : getValueFromConfigFile(key);
    }

    private String getValueFromConfigFile(String key) {
        if (properties == null) {
            properties = loadConfigFile();
        }
        Object objectFromFile = properties.get(key);
        return objectFromFile != null ? Objects.toString(objectFromFile) : null;
    }

    public static void loadConfigFileYaml() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(classLoader.getResource(CONFIG_YAML).getFile());
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    }

    private Properties loadConfigFile() {
        try {
            InputStream configFile = null;
            Properties properties = null;

            try {
                configFile = ClassLoader.getSystemClassLoader().getResourceAsStream(CONFIG_PROP);
                properties = new Properties();
                properties.load(configFile);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                assert configFile != null;
                configFile.close();
            }
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isSpecified() {
        return StringUtils.isNotEmpty(this.value);
    }

    public String getValue() {
        return this.retrieveValue(this.propertyKey);
    }

    public int getIntValue() {
        return Integer.parseInt(this.retrieveValue(this.propertyKey));
    }

    public boolean getBooleanValue() {
        return this.isSpecified() && Boolean.parseBoolean(this.value);
    }

}
