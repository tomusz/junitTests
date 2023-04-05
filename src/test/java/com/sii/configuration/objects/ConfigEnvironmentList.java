package com.sii.configuration.objects;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ConfigEnvironmentList {


    Map<String, Environment> environments = new LinkedHashMap<>();

    @JsonAnySetter
    void setEnvironment(String key, Environment environment) {
        environments.put(key, environment);
    }

    @JsonAnyGetter
    public List<Environment> getEnvironments() {
        return environments.values().stream().toList();
    }

}
