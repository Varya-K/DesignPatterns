package BehavioralDesignPatterns.Mediator.components;

import BehavioralDesignPatterns.Mediator.mediator.Mediator;
import BehavioralDesignPatterns.Mediator.mediator.Note;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddButton extends JButton implements Component{
    private Mediator mediator;

    public AddButton(){
        super("Add");
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent event) {
        mediator.addNewNote(new Note());
    }

    @Override
    public String getName(){
        return "AddButton";
    }
}
