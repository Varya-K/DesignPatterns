package CreationalDesignPatterns.FactoryMethod.factory;
import CreationalDesignPatterns.FactoryMethod.buttons.Button;
/**
 * Базовый класс фабрики
 */

public abstract class Dialog {
    public void renderWindow(){
        //...остальной код диалога...

        Button okButton = createButton();
        okButton.render();
    }

    // Подклассы будут переопределять этот метод, чтобы
    // создавать конкретные объекты продуктов, разные для каждой фабрики

    public abstract Button createButton();
}
