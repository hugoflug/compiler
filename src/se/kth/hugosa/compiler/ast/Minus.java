package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class Minus extends Exp {
    private Exp leftOp, rightOp;

    public Minus(Exp leftOp, Exp rightOp) {
        this.leftOp = leftOp;
        this.rightOp = rightOp;
    }

    public Exp getLeftOp() {
        return leftOp;
    }

    public Exp getRightOp() {
        return rightOp;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
