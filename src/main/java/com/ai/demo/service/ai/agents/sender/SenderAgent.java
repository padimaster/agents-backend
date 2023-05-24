package com.ai.demo.service.ai.agents.sender;

import jade.core.Agent;

public class SenderAgent extends Agent {
    private SenderData data;
    private String receiverName;

    @Override
    protected void setup() {
        System.out.println("Hola! Soy el agente " + getLocalName() + " y estoy listo.");

        Object[] args = getArguments();

        if (args != null && args.length > 0) {
            receiverName = (String) args[0];
            data = (SenderData) args[1];
        }

        long period = 1000; // 5 seconds

        addBehaviour(new SenderBehaviour(this, period, receiverName, data));
    }
}