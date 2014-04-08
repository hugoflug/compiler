package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

public class IntLit extends Exp {
    private int value;

    public IntLit(int value, int line, int column) {
        this.value = value;
        setPosition(line, column);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public int getValue() {
        return value;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IntLit{\n");
        sb.append("value=").append(value).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
