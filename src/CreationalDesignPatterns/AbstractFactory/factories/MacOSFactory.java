package CreationalDesignPatterns.AbstractFactory.factories;

import CreationalDesignPatterns.AbstractFactory.buttons.Button;
import CreationalDesignPatterns.AbstractFactory.checkboxes.Checkbox;
import CreationalDesignPatterns.AbstractFactory.buttons.MacOSButton;
import CreationalDesignPatterns.AbstractFactory.checkboxes.MacOSCheckbox;

public class MacOSFactory implements GUIFacroty{
    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}
