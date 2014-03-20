package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class Assign extends Stmt {
    private Identifier assignee;
    private Exp newValue;

    public Assign(Identifier assignee, Exp newValue) {
        this.assignee = assignee;
        this.newValue = newValue;
    }

    public Identifier getAssignee() {
        return assignee;
    }

    public Exp getNewValue() {
        return newValue;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
