package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class ArrayLength extends Exp {
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
