package CreationalDesignPatterns.Prototype.shapes;

public class Rectangle extends Shape{
    private int width;
    private int height;

    public Rectangle(){

    }

    public Rectangle(Rectangle target){
        super(target);
        if(target!=null){
            this.height=target.height;
            this.width=target.width;
        }
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public Shape clone() {
        return new Rectangle(this);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Rectangle || !super.equals(obj))) return false;
        Rectangle shape2=(Rectangle) obj;
        return shape2.height==height && shape2.width==width;
    }
}
