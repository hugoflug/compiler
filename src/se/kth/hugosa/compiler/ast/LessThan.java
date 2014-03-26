package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

public class LessThan extends Exp {
    private Exp leftOp, rightOp;

    public LessThan(Exp leftOp, Exp rightOp, int line, int column) {
        this.leftOp = leftOp;
        this.rightOp = rightOp;
        setPosition(line, column);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public Exp getLeftOp() {
        return leftOp;
    }

    public Exp getRightOp() {
        return rightOp;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LessThan{\n");
        sb.append("leftOp=").append(leftOp).append("\n");
        sb.append("rightOp=").append(rightOp).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
