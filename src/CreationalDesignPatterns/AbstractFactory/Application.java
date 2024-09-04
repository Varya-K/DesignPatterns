package CreationalDesignPatterns.AbstractFactory;

import CreationalDesignPatterns.AbstractFactory.factories.GUIFacroty;
import CreationalDesignPatterns.AbstractFactory.buttons.Button;
import CreationalDesignPatterns.AbstractFactory.checkboxes.Checkbox;

public class Application {
    private Button button;
    private Checkbox checkbox;
    public Application(GUIFacroty facroty){
        button = facroty.createButton();
        checkbox = facroty.createCheckbox();
    }

    public void print(){
        button.paint();
        checkbox.print();
    }
}
