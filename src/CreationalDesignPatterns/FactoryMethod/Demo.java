package CreationalDesignPatterns.FactoryMethod;

import CreationalDesignPatterns.FactoryMethod.factory.Dialog;
import CreationalDesignPatterns.FactoryMethod.factory.HtmlDialog;
import CreationalDesignPatterns.FactoryMethod.factory.WindowsDialog;

public class Demo {
    private static Dialog dialog;
    public static void main(String[] args){
        configure();
        runBusinessLogic();
    }

    static private void configure(){
        if(System.getProperty("os.name").equals("Windows 11")){
            dialog = new WindowsDialog();
        }
        else{
            dialog = new HtmlDialog();
        }
    }

    static void runBusinessLogic(){
        dialog.renderWindow();
    }

}
