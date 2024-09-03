package CreationalDesignPatterns.FactoryMethod.factory;

import CreationalDesignPatterns.FactoryMethod.buttons.Button;
import CreationalDesignPatterns.FactoryMethod.buttons.WindowsButton;


public class WindowsDialog extends Dialog{
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
