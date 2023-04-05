package com.sii.configuration;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static com.sii.configuration.consts.PropertiesKeys.BROWSER_ENVIRONMENT;
import static com.sii.configuration.consts.PropertiesKeys.DEFAULT_ENV_KEY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


@Slf4j
public class EnvironmentProperty {

    private final String APP_ENV;
    private final BrowserEnvironment BROWSER_ENV;

    private EnvironmentProperty() {
        this.APP_ENV = initAppEnv();
        this.initEnv();
        this.BROWSER_ENV = new BrowserEnvironment();
    }

    public static EnvironmentProperty getInstance() {
        return EnvironmentProperty.EnvironmentPropertySingleton.INSTANCE;
    }

    private static String initAppEnv() {
        return Optional.ofNullable(PropertyStore.getStringPropertyFromSystem(BROWSER_ENVIRONMENT))
                .orElse(PropertyStore.getConfiguration().getProperties().get(DEFAULT_ENV_KEY).toString());
    }

    private void initEnv() {
        if (!this.APP_ENV.isEmpty()) {
            log.info(" >>>>>>>>>>>>>>>>>>>>>>> Environment name : " + this.APP_ENV);
            loadAllEnvPropertiesToSystem();
        } else {
            log.error("Please provide env name");
            assertThat(true, equalTo(false));

        }
    }

    private void loadAllEnvPropertiesToSystem() {
        setSystemPropertiesFromConfigClass();
    }

    private void setSystemPropertiesFromConfigClass() {
        PropertyStore.setSystemProperties();
    }

    private static class EnvironmentPropertySingleton {
        private static final EnvironmentProperty INSTANCE = new EnvironmentProperty();
    }

}
