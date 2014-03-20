package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class And {
    private Exp leftOp, rightOp;

    public Exp getLeftOp() {
        return leftOp;
    }

    public Exp getRightOp() {
        return rightOp;
    }

    public And(Exp leftOp, Exp rightOp) {
        this.leftOp = leftOp;
        this.rightOp = rightOp;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
