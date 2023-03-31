package com.sii.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.sii.configuration.enums.EnvironmentNames;
import com.sii.configuration.objects.ConfigBrowser;
import com.sii.configuration.objects.ConfigWebElement;
import com.sii.configuration.objects.Configuration;
import com.sii.configuration.objects.Environment;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

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

    public static Environment getEnvironmentUnderTests() {
        initConfiguration();
        EnvironmentNames chosenEnvironment = Arrays.stream(EnvironmentNames.values())
                .filter(x -> x.getName().equals(configuration.getConfigBrowser().getEnvironment()))
                .findFirst().get();
        return configuration.getEnvironments().get(chosenEnvironment.getId());
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

}
