package BehavioralDesignPatterns.Visitor.shapes;

import BehavioralDesignPatterns.Visitor.visitor.Visitor;

public class Rectangle implements Shape{
    private int id;
    private int x,y;
    private int width, height;

    public Rectangle(int id, int x, int y, int width, int height){
        this.id=id;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    @Override
    public void move(int x, int y) {
        //move shape
    }

    @Override
    public void draw() {
        //draw shape
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitRectangle(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
