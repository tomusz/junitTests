package com.sii.configuration.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Environment {

    @JsonProperty("envName")
    public String environmentName;

    public String title;
    @JsonProperty("appUrl")
    public String applicationUrl;

}
