package CreationalDesignPatterns.Prototype.shapes;

import java.util.Objects;

public abstract class Shape {
    private int x;
    private int y;
    private String color;

    public Shape(){

    }

    public Shape(Shape target){
        if(target!=null){
            this.x = target.x;
            this.y = target.y;
            this.color = target.color;
        }
    }

    public abstract Shape clone();

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void setColor(String color){
        this.color=color;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Shape)) return false;
        Shape shape2=(Shape) obj;
        return shape2.x==x && shape2.y==y && shape2.color.equals(color);
    }
}
