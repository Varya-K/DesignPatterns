package BehavioralDesignPatterns.Memento.shapes;

import java.awt.*;

public class Rectangle extends BaseShape{
    private int width;
    private int height;

    public Rectangle(int x, int y, int width, int height, Color color){
        super(x,y,color);
        this.height=height;
        this.width=width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.drawRect(x,y,getWidth()-1, getHeight()-1);
    }
}
