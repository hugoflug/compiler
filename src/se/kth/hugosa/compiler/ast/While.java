package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

public class While extends Stmt {
    private Exp condition;
    private Stmt statement;

    public While(Exp condition, Stmt statement, int line, int column) {
        this.condition = condition;
        this.statement = statement;
        setPosition(line, column);
    }

    public Exp getCondition() {
        return condition;
    }

    public Stmt getStatement() {
        return statement;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("While{\n");
        sb.append("condition=").append(condition).append("\n");
        sb.append("statement=").append(statement).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
