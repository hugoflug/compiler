package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.TypeVisitor;
import se.kth.hugosa.compiler.visitors.Visitor;

public class IntLit extends Exp {
    private int value;

    public IntLit(int value) {
        this.value = value;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IntLit{\n");
        sb.append("value=").append(value).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
