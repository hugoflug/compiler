package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

public class Assign extends Stmt {
    private Identifier assignee;
    private Exp newValue;

    public Assign(Identifier assignee, Exp newValue, int line, int column) {
        this.assignee = assignee;
        this.newValue = newValue;
        setPosition(line, column);
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
