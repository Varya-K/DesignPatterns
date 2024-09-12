package StructuralDesignPatterns.Composite.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompoundShape extends BaseShape{
    protected List<Shape> children = new ArrayList<>();

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

    public void remove (Shape child){
        children.remove(child);
    }

    public void remove (Shape... components){
        children.removeAll(Arrays.asList(components));
    }

    public void clear(){
        children.clear();
    }

    @Override
    public int getX() {
        return getCoordinate(0);
    }

    @Override
    public int getY() {
        return getCoordinate(1);
    }

    private int getCoordinate(int dimension){
        if(children.isEmpty()) return 0;
        int coordinate = Integer.MAX_VALUE;
        for(Shape child:children){
            int childrenCoordinate = dimension==0?child.getX(): child.getY();
            coordinate=Math.min(coordinate, childrenCoordinate);
        }
        return coordinate;

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
        int maxSize=0;
        int coordinate = dimension==0? getX(): getY();
        for(Shape child: children){
            int childCoordinate = dimension==0? child.getX(): child.getY();
            int childSize = dimension==0? child.getWidth(): child.getHeight();
            int childRelativeSize = childCoordinate-coordinate+childSize;
            maxSize=Math.max(maxSize, childRelativeSize);
        }
        return maxSize;
    }

    @Override
    public void move(int x, int y) {
        for(Shape child:children){
            child.move(x,y);
        }
    }

    @Override
    public boolean isInsideBounds(int x, int y) {
        for(Shape child:children){
            if(child.isInsideBounds(x,y)){
                return true;
            }
        }
        return false;
    }

    public void unSelect(){
        super.unSelect();
        for(Shape child: children){
            child.unSelect();
        }
    }

    public boolean selectChildAt(int x, int y){
        for(Shape child: children){
            if(child.isInsideBounds(x,y)){
                child.select();
                return true;
            }
        }
        return false;
    }

    @Override
    public void paint(Graphics graphics) {
        if(isSelected()){
            enableSelectionStyle(graphics);
            graphics.drawRect(getX()-1,getY()-1,getWidth()+1, getHeight()+1);
            disableSelectionStyle(graphics);
        }
        for(Shape child: children){
            child.paint(graphics);
        }
    }
}
