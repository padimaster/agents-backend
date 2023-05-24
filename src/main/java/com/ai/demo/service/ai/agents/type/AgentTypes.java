package com.ai.demo.service.ai.agents.type;

public enum AgentTypes {
    REACTOR("reactor-agent"),
    RECEPTOR("receptor-agent"),
    TEMPERATURE("temperature-agent"),
    ENERGY("energy-agent"),
    HUMIDITY("humidity-agent"),
    PREASURE("preasure-agent"),
    UV("uv-agent");
    

    private final String value;

    AgentTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
