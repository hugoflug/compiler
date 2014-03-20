package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class If extends Stmt {
    private Exp condition;
    private Stmt thenStmt, elseStmt;

    public If(Exp condition, Stmt thenStmt, Stmt elseStmt) {
        this.condition = condition;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }

    public void accept(Visitor v) {
        v.visit(this);
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