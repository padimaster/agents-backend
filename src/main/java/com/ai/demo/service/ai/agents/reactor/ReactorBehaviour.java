package com.ai.demo.service.ai.agents.reactor;

import static org.mockito.ArgumentMatchers.doubleThat;

import com.ai.demo.model.IrrigationParameters;
import com.ai.demo.service.ai.agents.type.ParameterTypes;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class ReactorBehaviour extends  CyclicBehaviour{
    IrrigationParameters parameters;
    StringBuilder action;

    public ReactorBehaviour(StringBuilder action){
        this.action = action;
    }

    @Override
    public void action() {
        ACLMessage acl = this.myAgent.blockingReceive();

        try {
            parameters = (IrrigationParameters) acl.getContentObject();

            if(action == null){
                System.err.println("NULL VALUE");
                return;
            }

            System.out.println("Data: " + parameters.toString());

            action.setLength(0);
            action.append(calculateIrrigationFrequency());

            // System.out.println("ACTION FROM REACTOR");
            // System.out.println("CONDITIONS: " + parameters.toString());
            // System.out.println("ACTION: " + action.toString());

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String calculateIrrigationFrequency() {
    int frequency = 0;

     // Define los umbrales para cada parámetro
    double temperatureThreshold = 30.0;
    double energyThreshold = 50.0;
    double humidityThreshold = 60.0;
    double pressureThreshold = 1000.0;
    double uvThreshold = 5.0;

    double temperature, energy, humidity, pressure, uv;

    try{
        temperature = Double.parseDouble(parameters.getTemperature());
        energy = Double.parseDouble(parameters.getEnergy());
        humidity = Double.parseDouble(parameters.getHumidity());
        pressure = Double.parseDouble(parameters.getPressure());
        uv = Double.parseDouble(parameters.getUv());
    } catch(Exception e){
        return "No se puede calcular la frecuencia de riego";
    }

    // Comprueba cada parámetro y ajusta la frecuencia de riego en consecuencia
    if (temperature > temperatureThreshold) {
        frequency += 1;
    }
    if (energy < energyThreshold) {
        frequency += 1;
    }
    if (humidity < humidityThreshold) {
        frequency += 1;
    }
    if (pressure < pressureThreshold) {
        frequency += 1;
    }
    if (uv > uvThreshold) {
        frequency += 1;
    } 

    return "Regar " + frequency + " veces al día.";
}
}
