package br.com.fga.services;

import jade.wrapper.AgentController;

public interface AgentService {

    AgentController createAgent(String nickname, String className, Object[] args);

    AgentController createRmaAgent(Object[] args);

}
