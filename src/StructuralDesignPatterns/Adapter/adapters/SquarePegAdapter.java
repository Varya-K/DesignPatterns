package StructuralDesignPatterns.Adapter.adapters;

import StructuralDesignPatterns.Adapter.round.RoundPeg;
import StructuralDesignPatterns.Adapter.square.SquarePeg;

public class SquarePegAdapter extends RoundPeg {
    private SquarePeg peg;

    public SquarePegAdapter(SquarePeg peg){
        this.peg=peg;
    }

    @Override
    public double getRadius() {
        return Math.sqrt(Math.pow((peg.getWidth()/2),2)*2);
    }
}
