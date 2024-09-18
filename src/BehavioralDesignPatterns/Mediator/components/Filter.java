package BehavioralDesignPatterns.Mediator.components;

import BehavioralDesignPatterns.Mediator.mediator.Mediator;
import BehavioralDesignPatterns.Mediator.mediator.Note;

import javax.swing.*;
import java.util.ArrayList;

public class Filter extends JTextField implements Component{
    private Mediator mediator;
    private ListModel listModel;

    public Filter(){}

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public void setList(ListModel lsitModel){
        this.listModel=listModel;
    }

    private void searchElements(String s){
        if (listModel==null){
            return;
        }

        if(s.equals("")){
            mediator.setElementsList(listModel);
        }

        ArrayList<Note> notes = new ArrayList<>();
        for(int i=0; i< listModel.getSize();i++){
            notes.add((Note)listModel.getElementAt(i));
        }
        DefaultListModel<Note> listModel = new DefaultListModel<>();
        for(Note note:notes){
            if (note.getName().contains(s)){
                listModel.addElement(note);
            }
        }
        mediator.setElementsList(listModel);
    }

    @Override
    public String getName(){
        return "Filter";
    }
}
