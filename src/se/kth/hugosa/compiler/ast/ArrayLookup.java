package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class ArrayLookup {
    private Exp array, index;

    public ArrayLookup(Exp array, Exp index) {
        this.array = array;
        this.index = index;
    }

    public Exp getArray() {
        return array;
    }

    public Exp getIndex() {
        return index;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
