package com.sii.configuration;

import com.sii.configuration.objects.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class EnvironmentProperty {

    private static Logger logger = LoggerFactory.getLogger(EnvironmentProperty.class);

    private final String APP_ENV;
    private final BrowserEnvironment BROWSER_ENV;

    private EnvironmentProperty() {
        this.APP_ENV = initAppEnv();
        this.BROWSER_ENV = new BrowserEnvironment(PropertyStore.getConfiguration().getDefaultBrowser());
        this.initEnv();
    }

    public static EnvironmentProperty getInstance() {
        return EnvironmentProperty.EnvironmentPropertySingleton.INSTANCE;
    }

    private static String initAppEnv() {
        return PropertyStore.getConfiguration().getDefaultEnvironment();
    }

    private void initEnv() {
        if (!this.APP_ENV.isEmpty()) {
            logger.debug(" >>>>>>>>>>>>>>>>>>>>>>> Environment name : " + this.APP_ENV);
            loadAllEnvPropertiesToSystem();
        } else {
            logger.error("Please provide env name");
            assertThat(true, equalTo(false));

        }
    }

    private void loadAllEnvPropertiesToSystem() {
        setSystemPropertiesFromConfigClass();
    }

    private void setSystemPropertiesFromConfigClass() {
        Environment environmentUnderTests = PropertyStore.getEnvironmentUnderTests();
        Field[] declaredFields = environmentUnderTests.getClass().getDeclaredFields();

        for (Field envConfField : declaredFields) {
            if (!envConfField.toString().isEmpty()) {
                try {
                    System.setProperty(envConfField.getName(), envConfField.get(environmentUnderTests).toString());
                    logger.debug(String.format("Name %s and value %s",
                            envConfField.getName(), envConfField.get(environmentUnderTests).toString()));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    private static class EnvironmentPropertySingleton {
        private static final EnvironmentProperty INSTANCE = new EnvironmentProperty();
    }

}
