package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

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

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Minus{\n");
        sb.append("leftOp=").append(leftOp).append("\n");
        sb.append("rightOp=").append(rightOp).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
