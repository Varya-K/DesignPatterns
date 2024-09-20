package BehavioralDesignPatterns.Memento.commands;

import BehavioralDesignPatterns.Memento.editor.Editor;
import BehavioralDesignPatterns.Memento.shapes.Shape;

import java.awt.*;

public class ColorCommand implements Command{
    private Editor editor;
    private Color color;

    public ColorCommand(Editor editor, Color color){
        this.color=color;
        this.editor=editor;
    }

    @Override
    public String getName() {
        return "Colorize: "+color.toString();
    }

    @Override
    public void execute() {
        for(Shape child: editor.getShapes().getSelected()){
            child.setColor(color);
        }
    }
}
