package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class IntLit extends Exp {
    private int value;

    public IntLit(int value) {
        this.value = value;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
