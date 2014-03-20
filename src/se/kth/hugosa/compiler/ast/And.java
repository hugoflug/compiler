package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class And extends Exp {
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("And{\n");
        sb.append("leftOp=").append(leftOp).append("\n");
        sb.append("rightOp=").append(rightOp).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
