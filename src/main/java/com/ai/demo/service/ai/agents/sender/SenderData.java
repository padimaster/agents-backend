package com.ai.demo.service.ai.agents.sender;

import lombok.Data;

import java.io.Serializable;

import com.ai.demo.service.ai.agents.type.ParameterTypes;

@Data
public class SenderData implements Serializable{
    private ParameterTypes type;
    private String value;

    public SenderData(ParameterTypes type, String value) {
        this.type = type;
        this.value = value;
    }
}
