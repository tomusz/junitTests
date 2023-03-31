package com.sii.configuration.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Configuration {

   private String defaultBrowser;

   private String defaultEnvironment;

   @JsonProperty("browser")
   private ConfigBrowser configBrowser;


    @JsonProperty("environment")
    private List<Environment> environments;
}
