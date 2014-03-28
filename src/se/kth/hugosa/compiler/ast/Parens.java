package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

public class Parens extends Exp {
    private Exp exp;

    public Parens(Exp exp, int line, int column) {
        this.exp = exp;
        setPosition(line, column);
    }

    public Exp getExp() {
        return exp;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Parens{\n");
        sb.append("exp=").append(exp).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
