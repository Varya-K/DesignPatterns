package CreationalDesignPatterns.FactoryMethod.factory;

import CreationalDesignPatterns.FactoryMethod.buttons.Button;
import CreationalDesignPatterns.FactoryMethod.buttons.HtmlButton;


public class HtmlDialog extends Dialog {
    @Override
    public Button createButton() {
        return new HtmlButton();
    }
}
