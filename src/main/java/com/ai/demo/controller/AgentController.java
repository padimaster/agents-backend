package com.ai.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.demo.model.IrrigationParameters;
import com.ai.demo.service.ai.AgentService;
import com.ai.demo.service.ai.agents.sender.SenderData;
import com.ai.demo.service.ai.agents.type.ParameterTypes;

@RestController
@RequestMapping("/agents")
public class AgentController {
    private final AgentService agentService;

    @Autowired
    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }
    
    @RequestMapping("/calculate/irrigation")
    public String calculate() {
        return "{\"action\":\"" + agentService.getAction() + "\"}";
    }

    @RequestMapping("/temperature")
    public SenderData updateTemperature(@RequestBody String value){
        SenderData dataToUpdate = new SenderData(ParameterTypes.TEMPERATURE, value);
        return agentService.updateTemperature(dataToUpdate);
    }

    @RequestMapping("/energy")
    public String updateEnergy(@RequestBody String value){
        SenderData dataToUpdate = new SenderData(ParameterTypes.ENERGY, value);
        agentService.updateEnergy(dataToUpdate);
        return value;
    }

    @RequestMapping("/preasure")
    public String updatePreasure(@RequestBody String value){
        System.out.println("Updating Preasure " + value);
        SenderData dataToUpdate = new SenderData(ParameterTypes.PRESSURE, value);
        agentService.updatePreasure(dataToUpdate);
        return value;
    }

    @RequestMapping("/humidity")
    public String updateHumidity(@RequestBody String value){
        SenderData dataToUpdate = new SenderData(ParameterTypes.HUMIDITY, value);
        agentService.updateHumidity(dataToUpdate);
        return value;
    }

    @RequestMapping("/uv")
    public String updateUv(@RequestBody String value){
        SenderData dataToUpdate = new SenderData(ParameterTypes.UV, value);
        agentService.updateUv(dataToUpdate);
        return value;
    }
}
