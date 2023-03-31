package com.sii.configuration.enums;

public enum EnvironmentNames {

    TEST("test", 0), INT("int", 1);

    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    EnvironmentNames(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
