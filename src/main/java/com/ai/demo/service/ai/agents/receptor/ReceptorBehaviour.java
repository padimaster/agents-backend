package com.ai.demo.service.ai.agents.receptor;

import java.io.IOException;

import com.ai.demo.model.IrrigationParameters;
import com.ai.demo.service.ai.agents.sender.SenderData;
import com.ai.demo.service.ai.agents.type.ParameterTypes;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class ReceptorBehaviour extends TickerBehaviour{
    private String receiverName;
    private IrrigationParameters parameters;

    public ReceptorBehaviour(Agent agent, long period, String receiverName) {
        super(agent, period);
        this.receiverName = receiverName;
        this.parameters = new IrrigationParameters();
    }

    @Override
    protected void onTick() {
        ACLMessage acl = this.myAgent.blockingReceive();
        SenderData senderData;
        try {
            senderData = (SenderData) acl.getContentObject();

            if(senderData.getValue() == null){
                System.err.println("NULL VALUE");
                return;
            }
            System.out.println("SETTING PARAMETER: " + senderData.getType() + " " + senderData.getValue());
            parameters.setParameter(senderData);
        } catch (UnreadableException e) {
            e.printStackTrace();
        }

        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID(this.receiverName, AID.ISLOCALNAME));
        msg.setSender(myAgent.getAID());
        msg.setLanguage("Java");
        try {
            msg.setContentObject(this.parameters);
        } catch (IOException e) {
            e.printStackTrace();
        }
        myAgent.send(msg);
    }
    
}
