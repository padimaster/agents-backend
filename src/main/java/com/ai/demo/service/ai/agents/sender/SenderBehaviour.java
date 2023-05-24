package com.ai.demo.service.ai.agents.sender;

import java.io.IOException;

import com.ai.demo.model.IrrigationParameters;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

public class SenderBehaviour extends TickerBehaviour {
    private String receiverName;
    private SenderData data;

    public SenderBehaviour(Agent agent, long period, String receiverName, SenderData data) {
        super(agent, period);
        this.receiverName = receiverName;
        this.data = data;
    }

    @Override
    protected void onTick() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID(this.receiverName, AID.ISLOCALNAME));
        msg.setSender(myAgent.getAID());
        msg.setLanguage("Java");
        try {
            msg.setContentObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        myAgent.send(msg);

    }
}