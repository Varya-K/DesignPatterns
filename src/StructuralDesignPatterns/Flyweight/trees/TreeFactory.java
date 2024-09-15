package StructuralDesignPatterns.Flyweight.trees;

import java.awt.*;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;

public class TreeFactory {
    static Map<String,TreeType> treeTypes = new HashMap<>();
    public static TreeType getTreeType(String name, Color color, String otherTreeData){
        TreeType result = treeTypes.get(name);
        if(result==null){
            result = new TreeType(name,color,otherTreeData);
            treeTypes.put(name, result);
        }
        return result;
    }
}
