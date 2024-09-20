package BehavioralDesignPatterns.Visitor.visitor;

import BehavioralDesignPatterns.Visitor.shapes.*;

public interface Visitor {
    String visitDot(Dot dot);
    String visitCircle(Circle circle);
    String visitRectangle(Rectangle rectangle);
    String visitCompoundGraphic(CompoundShape cg);
}
