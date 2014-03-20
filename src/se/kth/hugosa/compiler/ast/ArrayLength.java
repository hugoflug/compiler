package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class ArrayLength {
    private Exp array;

    public ArrayLength(Exp array) {
        this.array = array;
    }

    public Exp getArray() {
        return array;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
