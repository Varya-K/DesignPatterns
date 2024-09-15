package StructuralDesignPatterns.Flyweight.forest;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import StructuralDesignPatterns.Flyweight.trees.*;

public class Forest extends JFrame {
    private ArrayList<Tree> trees = new ArrayList<>();

    public void platTree(int x, int y, String name, Color color, String otherTreeData){
        TreeType type = TreeFactory.getTreeType(name, color, otherTreeData);
        Tree tree = new Tree(x,y,type);
        trees.add(tree);
    }

    @Override
    public void paint(Graphics g) {
        for(Tree tree: trees){
            tree.draw(g);
        }
    }
}
