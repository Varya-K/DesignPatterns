package CreationalDesignPatterns.FactoryMethod;


import CreationalDesignPatterns.FactoryMethod.factory.Dialog;
import CreationalDesignPatterns.FactoryMethod.factory.HtmlDialog;
import CreationalDesignPatterns.FactoryMethod.factory.WindowsDialog;

public class Demo {
    private static Dialog dialog;
    public static void main(String[] args){
        try {
            configure();
            runBusinessLogic();
        }
        catch (UnknownOSException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    static private void configure() throws UnknownOSException{
        String osName = System.getProperty("os.name").toLowerCase();
        if(osName.contains("web")){
            dialog=new HtmlDialog();
        }
        else if (osName.contains("windows")){
            dialog=new WindowsDialog();
        }
        else{
            throw new UnknownOSException("Unknown operating system.");
        }

    }

    static void runBusinessLogic(){
        dialog.renderWindow();
    }

}

class UnknownOSException extends Exception{
    public UnknownOSException(String message){
        super(message);
    }
}