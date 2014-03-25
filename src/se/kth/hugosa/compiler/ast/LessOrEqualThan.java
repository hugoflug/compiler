package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

public class LessOrEqualThan extends Exp {
    private Exp leftOp, rightOp;

    public LessOrEqualThan(Exp leftOp, Exp rightOp) {
        this.leftOp = leftOp;
        this.rightOp = rightOp;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LessOrEqualThan{\n");
        sb.append("leftOp=").append(leftOp).append("\n");
        sb.append("rightOp=").append(rightOp).append("\n");
        sb.append('}');
        return sb.toString();
    }
}