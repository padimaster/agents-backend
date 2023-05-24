package com.ai.demo.service.ai.agents.receptor;

import jade.core.Agent;


public class ReceptorAgent extends Agent {
    private String receiverName;

    @Override
    protected void setup() {
        System.out.println("Hola! Soy el agente " + getLocalName() + " y estoy listo.");

        Object[] args = getArguments();

        if (args != null && args.length > 0) {
            receiverName = (String) args[0];
        }

        long period = 10; // 5 seconds

        addBehaviour(new ReceptorBehaviour(this, period, receiverName));
    }
}
