package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

public class If extends Stmt {
    private Exp condition;
    private Stmt thenStmt, elseStmt;

    public If(Exp condition, Stmt thenStmt, Stmt elseStmt, int line, int column) {
        this.condition = condition;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
        setPosition(line, column);
    }

    public Stmt getThenStmt() {
        return thenStmt;
    }

    public Stmt getElseStmt() {
        return elseStmt;
    }

    public Exp getCondition() {  return condition; }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("If{\n");
        sb.append("condition=").append(condition).append("\n");
        sb.append("thenStmt=").append(thenStmt).append("\n");
        sb.append("elseStmt=").append(elseStmt).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
