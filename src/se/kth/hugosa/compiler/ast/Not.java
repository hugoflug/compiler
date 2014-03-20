package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class Not extends Exp {
    private Exp expression;

    public Not(Exp expression) {
        this.expression = expression;
    }

    public Exp getExpression() {
        return expression;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Not{\n");
        sb.append("expression=").append(expression).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
