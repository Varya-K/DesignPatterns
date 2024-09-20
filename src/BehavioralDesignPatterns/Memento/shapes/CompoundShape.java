package BehavioralDesignPatterns.Memento.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompoundShape extends BaseShape{
    private List<Shape> children = new ArrayList<>();

    public CompoundShape(Shape... components){
        super(0,0, Color.BLACK);
        add(components);
    }

    public void add(Shape component){
        children.add(component);
    }

    public void add(Shape... components){
        children.addAll(Arrays.asList(components));
    }

    public void remove(Shape component){
        children.remove(component);
    }

    public void remove(Shape... components){
        children.removeAll(Arrays.asList(components));
    }

    public void clear(){
        children.clear();
    }

    public int getX(){
        return getCoordinate(0);
    }

    @Override
    public int getY() {
        return getCoordinate(1);
    }

    private int getCoordinate(int dimension){
        if(children.size()==0){
            return 0;
        }
        int coordiante = Integer.MAX_VALUE;
        for(var child: children){
            int childCoordinate = dimension==0? child.getX(): child.getY();
            coordiante=Math.min(coordiante,childCoordinate);
        }
        return coordiante;
    }

    @Override
    public int getWidth() {
        return getSize(0);
    }

    @Override
    public int getHeight() {
        return getSize(1);
    }

    private int getSize(int dimension){
        int size = 0;
        int coordinate = dimension==0? getX(): getY();
        for(var child: children){
            int childSize = (dimension==0 ? child.getX()+ child.getWidth(): child.getY()+child.getHeight())-coordinate;
            size=Math.max(size,childSize);
        }
        return size;
    }

    @Override
    public void drag() {
        for(var child: children){
            child.drag();
        }
    }

    @Override
    public void drop() {
        for(var child: children){
            child.drop();
        }
    }

    @Override
    public void moveTo(int x, int y) {
        for(var child: children){
            child.moveTo(x,y);
        }
    }

    @Override
    public void moveBy(int x, int y) {
        for(var child: children){
            child.moveBy(x,y);
        }
    }

    @Override
    public boolean isInsideBounds(int x, int y) {
        for(var child:children){
            if (child.isInsideBounds(x,y)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        for(var child: children){
            child.setColor(color);
        }
    }

    @Override
    public void unSelect() {
        super.unSelect();
        for(var child: children){
            child.drag();
        }
    }

    public Shape getChildAt(int x, int y){
        for(var child: children){
            if(child.isInsideBounds(x,y)){
                return child;
            }
        }
        return null;
    }

    public boolean selectChildAt(int x, int y){
        Shape child = getChildAt(x,y);
        if(child!=null){
            child.select();
            return true;
        }
        return false;
    }

    public List<Shape> getSelected(){
        List<Shape> selected = new ArrayList<>();
        for(var child: children){
            if(child.isSelected()){
                selected.add(child);
            }
        }
        return selected;
    }

    @Override
    public void paint(Graphics graphics) {
        if(isSelected()){
            enableSelectionStyle(graphics);
            graphics.drawRect(getX()-1, getY()-1, getWidth()+1, getHeight()+1);
            disableSelectionStyle(graphics);
        }

        for(var child:children){
            child.paint(graphics);
        }
    }
}
