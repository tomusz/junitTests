package com.sii.unittests;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public class WebPageUtils {

    Logger logger = LoggerFactory.getLogger("com.sii.unittests.WebPageUtils.class");

    protected Consumer<WebDriver> maximiseWindow = d -> {
        d.manage().window().maximize();
        logger.debug("Window maximised");
    };
}
