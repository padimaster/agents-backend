package com.ai.demo.model;

import com.ai.demo.service.ai.agents.sender.SenderData;

import jade.util.leap.Serializable;
import lombok.Data;

@Data
public class IrrigationParameters implements Serializable{
    private String temperature;
    private String energy;
    private String humidity;
    private String pressure;
    private String uv;

    public IrrigationParameters setParameter (SenderData data){
        String value = data.getValue();
        //System.out.println("Updating " + data.getType() + " " + value);
        switch (data.getType()){
            case TEMPERATURE:
                this.temperature = value;
                break;
            case ENERGY:
                this.energy = value;
                break;
            case HUMIDITY:
                this.humidity = value;
                break;
            case PRESSURE:
                this.pressure = value;
                break;
            case UV:
                this.uv = value;
                break;
            default:
                break;
        }

        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"temperature\": \"").append(this.temperature).append("\",\n");
        sb.append("  \"energy\": \"").append(this.energy).append("\",\n");
        sb.append("  \"humidity\": \"").append(this.humidity).append("\",\n");
        sb.append("  \"preasure\": \"").append(this.pressure).append("\",\n");
        sb.append("  \"uv\": \"").append(this.uv).append("\"\n");
        sb.append("}");

        return sb.toString();
    }
}
