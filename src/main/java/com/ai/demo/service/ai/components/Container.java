package com.ai.demo.service.ai.components;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import org.springframework.stereotype.Component;

import com.ai.demo.service.ai.agents.reactor.ReactorAgent;
import com.ai.demo.service.ai.agents.receptor.ReceptorAgent;
import com.ai.demo.service.ai.agents.sender.SenderAgent;
import com.ai.demo.service.ai.agents.sender.SenderData;
import com.ai.demo.service.ai.agents.type.AgentTypes;
import com.ai.demo.service.ai.agents.type.ParameterTypes;

import javax.annotation.PostConstruct;

@Component
public class Container {
    private AgentContainer container;
    private SenderData temperatureData;
    private SenderData energyData;
    private SenderData preasureData;
    private SenderData humidityData;
    private SenderData uvData;

    private StringBuilder actions;

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct method called in Container");
        System.setProperty("jade_maindetect", "false");

        Runtime runtime = jade.core.Runtime.instance();
        runtime.setCloseVM(true);
        System.out.println("Runtime has been created");

        Profile profile = new ProfileImpl(null, 1099, null);
        System.out.println("Default profile created");

        container = runtime.createMainContainer(profile);
        System.out.println("Container created" + profile.toString());

        initData();
        startAgents();
    }

    public void initData(){
        System.out.println("Init data method called in Container");
        this.temperatureData = new SenderData(ParameterTypes.TEMPERATURE, "30.5");
        this.energyData = new SenderData(ParameterTypes.ENERGY, "15.2");
        this.preasureData = new SenderData(ParameterTypes.PRESSURE, "1000");
        this.humidityData = new SenderData(ParameterTypes.HUMIDITY, "65.3");
        this.uvData = new SenderData(ParameterTypes.UV, "0.5");
        this.actions = new StringBuilder("");
    }
    
    public void startAgents() {
        try {
            AgentController reactorAgentController, receptorAgentController, temperatureAgentController, energyController, preasureController, humidityController, uvController;
            Object[] reactorArgs = {actions};
            Object[] receptorArgs = {AgentTypes.REACTOR.getValue()};
            Object[] temperatureSenderArgs = {AgentTypes.RECEPTOR.getValue(), temperatureData};
            Object[] energySenderArgs = {AgentTypes.RECEPTOR.getValue(), energyData};
            Object[] preasureSenderArgs = {AgentTypes.RECEPTOR.getValue(), preasureData};
            Object[] humiditySenderArgs = {AgentTypes.RECEPTOR.getValue(), humidityData};
            Object[] uvSenderArgs = {AgentTypes.RECEPTOR.getValue(), uvData};

            // Reactor
            reactorAgentController = container.createNewAgent(AgentTypes.REACTOR.getValue(), ReactorAgent.class.getName(), reactorArgs);

            // Receptors
            receptorAgentController = container.createNewAgent(AgentTypes.RECEPTOR.getValue(), ReceptorAgent.class.getName(), receptorArgs);

            // Senders
            temperatureAgentController = container.createNewAgent(AgentTypes.TEMPERATURE.getValue(), SenderAgent.class.getName(), temperatureSenderArgs);

            energyController = container.createNewAgent(AgentTypes.ENERGY.getValue(), SenderAgent.class.getName(), energySenderArgs);
            
            preasureController = container.createNewAgent(AgentTypes.PREASURE.getValue(), SenderAgent.class.getName(), preasureSenderArgs);

            humidityController = container.createNewAgent(AgentTypes.HUMIDITY.getValue(), SenderAgent.class.getName(), humiditySenderArgs);
            
            uvController = container.createNewAgent(AgentTypes.UV.getValue(), SenderAgent.class.getName(), uvSenderArgs);
            
            // Reactor
            reactorAgentController.start();

            // Receptor
            receptorAgentController.start();

            // Senders
            temperatureAgentController.start();
            energyController.start();
            preasureController.start();
            humidityController.start();
            uvController.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }

    public SenderData updateTemperature(SenderData dataToUpdate){
        this.temperatureData.setValue(dataToUpdate.getValue());

        try{
            AgentController existingSenderController = container.getAgent(AgentTypes.TEMPERATURE.getValue());

            if (existingSenderController != null) {
                existingSenderController.putO2AObject(dataToUpdate, true);
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return dataToUpdate;
    }

    public SenderData updateEnergy(SenderData dataToUpdate){
        System.out.println("CONTAINER" + dataToUpdate.getType() + ": Setting irrigation parameter");
        System.out.println("NEW VALUE: " + dataToUpdate.getValue());

        this.energyData.setValue(dataToUpdate.getValue());

        try{
            AgentController existingSenderController = container.getAgent(AgentTypes.ENERGY.getValue());

            if (existingSenderController != null) {
                existingSenderController.putO2AObject(dataToUpdate, true);
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return dataToUpdate;
    }

    public SenderData updatePreasure(SenderData dataToUpdate){
        System.out.println("CONTAINER" + dataToUpdate.getType() + ": Setting irrigation parameter");
        System.out.println("NEW VALUE: " + dataToUpdate.getValue());

        this.preasureData.setValue(dataToUpdate.getValue());

        try{
            AgentController existingSenderController = container.getAgent(AgentTypes.PREASURE.getValue());

            if (existingSenderController != null) {
                existingSenderController.putO2AObject(dataToUpdate, true);
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return dataToUpdate;
    }

    public SenderData updateHumidity(SenderData dataToUpdate){
        System.out.println("CONTAINER" + dataToUpdate.getType() + ": Setting irrigation parameter");
        System.out.println("NEW VALUE: " + dataToUpdate.getValue());

        this.humidityData.setValue(dataToUpdate.getValue());

        try{
            AgentController existingSenderController = container.getAgent(AgentTypes.HUMIDITY.getValue());

            if (existingSenderController != null) {
                existingSenderController.putO2AObject(dataToUpdate, true);
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return dataToUpdate;
    }

    public SenderData updateUV(SenderData dataToUpdate){
        System.out.println("CONTAINER" + dataToUpdate.getType() + ": Setting UV parameter");
        System.out.println("NEW VALUE: " + dataToUpdate.getValue());

        this.uvData.setValue(dataToUpdate.getValue());

        try{
            AgentController existingSenderController = container.getAgent(AgentTypes.UV.getValue());

            if (existingSenderController != null) {
                existingSenderController.putO2AObject(dataToUpdate, true);
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return dataToUpdate;
    }

    public String getAction(){
        return actions.toString();
    }
}