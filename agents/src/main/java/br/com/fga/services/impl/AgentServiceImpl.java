package br.com.fga.services.impl;

import br.com.fga.exceptions.AgentException;
import br.com.fga.services.AgentService;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class AgentServiceImpl implements AgentService {

    private static final AgentService INSTANCE = new AgentServiceImpl();

    private final ContainerController containerController;

    private AgentServiceImpl() {
        Runtime runtime = Runtime.instance();
        Profile profile = new ProfileImpl();

        this.containerController = runtime.createMainContainer(profile);
    }

    public AgentController createAgent(String nickname, String className, Object[] args) {
        try {
            return this.containerController
                .createNewAgent(nickname, className, args);
        } catch (StaleProxyException e) {
            throw new AgentException(e.getMessage());
        }
    }

    public AgentController createRmaAgent(Object[] args) {
        return this.createAgent("rma", "jade.tools.rma.rma", args);
    }

    public static AgentService getInstance() {
        return INSTANCE;
    }

}
