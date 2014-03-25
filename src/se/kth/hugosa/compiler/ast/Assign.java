package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.TypeVisitor;
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

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Assign{\n");
        sb.append("assignee=").append(assignee).append("\n");
        sb.append("newValue=").append(newValue).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
