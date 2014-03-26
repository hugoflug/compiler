package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

public class IfWithoutElse extends Stmt {
    private Exp condition;
    private Stmt thenStmt;

    public IfWithoutElse(Exp condition, Stmt thenStmt, int line, int column) {
        this.condition = condition;
        this.thenStmt = thenStmt;
        setPosition(line, column);
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

    public Exp getCondition() {  return condition; }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IfWithoutElse{\n");
        sb.append("condition=").append(condition).append("\n");
        sb.append("thenStmt=").append(thenStmt).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
