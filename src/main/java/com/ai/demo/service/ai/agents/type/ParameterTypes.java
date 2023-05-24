package com.ai.demo.service.ai.agents.type;

public enum ParameterTypes {
    TEMPERATURE("temperature"),
    ENERGY("energy"),
    HUMIDITY("humidity"),
    PRESSURE("pressure"),
    UV("uv");

    private final String value;

    ParameterTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
