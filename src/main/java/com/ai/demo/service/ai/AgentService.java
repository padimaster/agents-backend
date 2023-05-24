package com.ai.demo.service.ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.demo.service.ai.agents.sender.SenderData;
import com.ai.demo.service.ai.components.Container;

@Service
public class AgentService {
    private final Container container;
    
    @Autowired
    public AgentService(Container container) {
        this.container = container;
    }

    public String getAction(){
        return container.getAction();
    }

    public SenderData updateTemperature(SenderData dataToUpdate){
        System.out.println("SERVICE: Updating Temperature");
        return container.updateTemperature(dataToUpdate);
    }

    public SenderData updateEnergy(SenderData dataToUpdate){
        System.out.println("SERVICE: Updating Energy");
        return container.updateEnergy(dataToUpdate);
    }

    public SenderData updatePreasure(SenderData dataToUpdate){
        System.out.println("SERVICE: Updating Preasure");
        return container.updatePreasure(dataToUpdate);
    }

    public SenderData updateHumidity(SenderData dataToUpdate){
        System.out.println("SERVICE: Updating Humidity");
        return container.updateHumidity(dataToUpdate);
    }

    public SenderData updateUv(SenderData dataToUpdate){
        System.out.println("SERVICE: Updating Uv");
        return container.updateUV(dataToUpdate);
    }
}
