package com.ai.demo.service.ai.agents.reactor;


import jade.core.Agent;

public class ReactorAgent extends Agent{
    private StringBuilder action;

    @Override
    protected void setup() {
        System.out.println("Hola! Soy el agente " + getLocalName() + " y estoy listo.");
       
        Object[] args = getArguments();

        if (args != null && args.length > 0) {
            action = (StringBuilder) args[0];
        }

        addBehaviour(new ReactorBehaviour(action));
    }
}
