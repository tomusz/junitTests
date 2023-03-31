package com.sii.unittests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.sii.configuration.PropertyStore;
import com.sii.configuration.objects.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Page Title Validation")
@Slf4j
class TitleTest extends BaseTest {

    @Test
    @Tag(TestConstants.REGRESSION)
    @Tag("Title")
    void shouldValidateSiiPortalWebPageTitle() throws IOException {
//        driver.get(url);
//        new WebPageUtils().maximiseWindow.accept(driver);

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(classLoader.getResource("config.yaml").getFile());

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

//        String fileText = FileHandler.loadFileFR("C:\\Users\\twachowicz\\IdeaProjects\\upskillProgram\\junitTests\\src\\main\\resources\\config.yaml");
        Configuration configuration = mapper.readerFor(Configuration.class).readValue(file);

        System.out.println("base = " + configuration.getDefaultBrowser());
        System.out.println("base browser = " + configuration.getDefaultEnvironment());
        System.out.println("base browser = " + configuration.getConfigBrowser());
        System.out.println("base browser = " + configuration.getEnvironments());
        System.out.println("base browser = " + configuration.getConfigBrowser());

        System.out.println("browser = " + configuration.getConfigBrowser().getEnvironment());
        System.out.println("browser = " + configuration.getConfigBrowser().isScreenshots());
        System.out.println("browser = " + configuration.getConfigBrowser().isHeadless());

        System.out.println("WebElement = " + configuration.getConfigBrowser().getConfigWebElement().getTimeout());

        System.out.println("envs = " + configuration.getEnvironments().get(0));
        System.out.println("envs = " + configuration.getEnvironments().get(1));

        System.out.println("envs = " + configuration.getEnvironments().get(1).getEnvironmentName());
        System.out.println("envs = " + configuration.getEnvironments().get(1).getApplicationUrl());
        System.out.println("envs = " + configuration.getEnvironments().get(1).getTitle());

//        System.out.println(configuration.getEnvironments().stream().get("defaultBrowser"));
//        System.out.println(configuration.getProperties().get("browser"));
//        System.out.println(configuration.getProperties().get("environment"));
//        System.out.println(configuration.getProperties().get("timeout"));


        PropertyStore.loadConfigFileYaml();

        String actualTitle = driver.getTitle();
        log.info(String.format("Actual title is %s", actualTitle));
        assertThat(actualTitle).contains(System.getProperty("eTitle"));
    }
}