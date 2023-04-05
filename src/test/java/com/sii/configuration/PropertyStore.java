package com.sii.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.sii.configuration.consts.ConfigPropertiesKeys;
import com.sii.configuration.consts.PropertiesKeys;
import com.sii.configuration.objects.ConfigBrowser;
import com.sii.configuration.objects.ConfigWebElement;
import com.sii.configuration.objects.Configuration;

import java.io.File;
import java.io.IOException;

public class PropertyStore {

    public static final String CONFIG_YAML = "config.yaml";
    private static Configuration configuration = null;


    private static void initConfiguration() {
        if (configuration == null) {
            configuration = loadConfigFile();
        }
    }

    public static Configuration getConfiguration() {
        initConfiguration();
        return configuration;
    }

    public static ConfigBrowser getBrowserConfiguration() {
        initConfiguration();
        return configuration.getConfigBrowser();
    }

    public static ConfigWebElement getWebElementConfiguration() {
        initConfiguration();
        return configuration.getConfigBrowser().getConfigWebElement();
    }

    private static Configuration loadConfigFile() {

        File configFile;
        Configuration conf;
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            configFile = new File(classLoader.getResource(CONFIG_YAML).getFile());
            conf = mapper.readerFor(Configuration.class).readValue(configFile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return conf;
    }

    public static String getChosenEnvironmentName() {
        return configuration.getConfigBrowser().getProperties().get(ConfigPropertiesKeys.BROWSER_ENVIRONMENT) != null ?
                configuration.getConfigBrowser().getProperties().get(ConfigPropertiesKeys.BROWSER_ENVIRONMENT).toString() :
                configuration.getProperties().get(PropertiesKeys.DEFAULT_ENV_KEY).toString();
    }

    public static void setSystemProperties() {
        PropertiesHandler.addPropertiesToSystem(configuration);
    }

    public static int getIntPropertyFromSystem(String key) {
        return Integer.parseInt(System.getProperty(key));
    }

    public static boolean getBooleanPropertyFromSystem(String key) {
        return Boolean.parseBoolean(System.getProperty(key));
    }

    public static String getStringPropertyFromSystem(String key) {
        return System.getProperty(key);
    }
}