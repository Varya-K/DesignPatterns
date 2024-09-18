package BehavioralDesignPatterns.Mediator.components;

import BehavioralDesignPatterns.Mediator.mediator.Mediator;

public interface Component {
    public void setMediator(Mediator mediator);
    String getName();
}
