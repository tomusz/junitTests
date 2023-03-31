package com.sii.configuration.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConfigBrowser {

    private boolean screenshots;
    private boolean headless;
    @JsonProperty("webElement")
    private ConfigWebElement configWebElement;
    private String environment;
    private String name;

}
