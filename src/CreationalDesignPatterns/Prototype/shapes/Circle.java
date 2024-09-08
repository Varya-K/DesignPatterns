package CreationalDesignPatterns.Prototype.shapes;

public class Circle extends Shape{
    private int radius;

    public Circle(){

    }

    public Circle (Circle target){
        super(target);
        if(target!=null){
            this.radius=target.radius;
        }
    }

    public void setRadius(int radius){
        this.radius=radius;
    }

    public int getRadius(){
        return radius;
    }

    @Override
    public Shape clone() {
        return new Circle(this);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Circle || !super.equals(obj))) return false;
        Circle shape2=(Circle) obj;
        return shape2.radius==radius;
    }
}
