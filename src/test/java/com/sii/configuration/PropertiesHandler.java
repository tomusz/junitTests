package com.sii.configuration;

import com.sii.configuration.enums.EnvironmentNames;
import com.sii.configuration.objects.Configuration;
import com.sii.configuration.objects.Environment;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.sii.configuration.consts.PropertiesKeys.*;

@Slf4j
public class PropertiesHandler {

    public static void addPropertiesToSystem(Configuration configuration) {
        addPropertiesToSystemWithSuffix(BASE_PREFIX, configuration.getProperties());
        addPropertiesToSystemWithSuffix(BROWSER_PREFIX, configuration.getConfigBrowser().getProperties());
        addPropertiesToSystemWithSuffix(WEBELEMENT_PREFIX, configuration.getConfigBrowser().getConfigWebElement().getProperties());
        addEnvironmentPropertiesToSystem(PropertyStore.getChosenEnvironmentName(), configuration.getEnvironments().getEnvironments());
    }

    private static void addEnvironmentPropertiesToSystem(String environmentName, List<Environment> environments) {
        Environment env = environments.get(getEnvironmentIdByName(environmentName));
        if (!env.getProperties().toString().isEmpty()) {
            try {
                env.getProperties().entrySet()
                        .forEach(x -> {
                            System.setProperty(ENVIRONMENT_PREFIX + x.getKey(), x.getValue().toString());
                            log.debug(String.format("Name %s and value %s",
                                    x.getKey(), x.getKey()));
                        });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static void addPropertiesToSystemWithSuffix(String prefix, Map<String, Object> properties) {
        if (!properties.toString().isEmpty()) {
            try {
                properties.entrySet()
                        .forEach(x -> {
                            System.setProperty(prefix + x.getKey(), x.getValue().toString());
                            log.debug(String.format("Name %s and value %s",
                                    x.getKey(), x.getKey()));
                        });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
    }

    private static int getEnvironmentIdByName(String name) {
        return Arrays.stream(EnvironmentNames.values())
                .filter(x -> x.getName().equals(name))
                .map(EnvironmentNames::getId)
                .findFirst().get();
    }

}
