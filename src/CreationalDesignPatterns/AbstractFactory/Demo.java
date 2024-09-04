package CreationalDesignPatterns.AbstractFactory;

import CreationalDesignPatterns.AbstractFactory.factories.*;

public class Demo {
    private static Application configureApplication() throws UnknownOSException{
        GUIFacroty factory;
        String osName = System.getProperty("os.name").toLowerCase();

        if(osName.contains("mac")){
            factory=new MacOSFactory();
        }
        else if (osName.contains("windows")){
            factory=new WindowsFactory();
        }
        else{
            throw new UnknownOSException("Unknown operating system.");
        }
        return new Application(factory);

    }
    public static void main(String[] args){
        try {
            Application app = configureApplication();
            app.print();
        }
        catch (UnknownOSException e){
            System.out.println("Error: "+e.getMessage());
        }

    }
}

class UnknownOSException extends Exception{
    public UnknownOSException(String message){
        super(message);
    }
}
