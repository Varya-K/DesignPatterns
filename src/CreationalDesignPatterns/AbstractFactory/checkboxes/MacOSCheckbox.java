package CreationalDesignPatterns.AbstractFactory.checkboxes;

public class MacOSCheckbox implements Checkbox{
    @Override
    public void print() {
        System.out.println("You have created MacOSCheckbox");
    }
}
