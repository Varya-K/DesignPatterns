package CreationalDesignPatterns.AbstractFactory.checkboxes;

public class WindowsCheckbox implements Checkbox{
    @Override
    public void print() {
        System.out.println("You have creates WindowsCheckbox");
    }
}
