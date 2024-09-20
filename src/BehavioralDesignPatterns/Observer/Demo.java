package BehavioralDesignPatterns.Observer;

import BehavioralDesignPatterns.Observer.editor.Editor;
import BehavioralDesignPatterns.Observer.listeners.EmailNotificationListener;
import BehavioralDesignPatterns.Observer.listeners.LogOpenListener;

public class Demo {
    public static void main(String[] args){
        Editor editor = new Editor();
        editor.events.subscribe("open", new LogOpenListener("logfile.txt"));
        editor.events.subscribe("save", new EmailNotificationListener("admin@example.com"));

        try{
            editor.openFile("text.txt");
            editor.saveFile();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
