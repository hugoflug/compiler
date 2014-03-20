package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class While extends Stmt {
    private Exp condition;
    private Stmt statement;

    public While(Exp condition, Stmt statement) {
        this.condition = condition;
        this.statement = statement;
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
}
