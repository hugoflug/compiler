package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.TypeVisitor;
import se.kth.hugosa.compiler.visitors.Visitor;

public class IfWithoutElse extends Stmt {
    private Exp condition;
    private Stmt thenStmt;

    public IfWithoutElse(Exp condition, Stmt thenStmt) {
        this.condition = condition;
        this.thenStmt = thenStmt;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public Stmt getThenStmt() {
        return thenStmt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IfWithoutElse{\n");
        sb.append("condition=").append(condition).append("\n");
        sb.append("thenStmt=").append(thenStmt).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
