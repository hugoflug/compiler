package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

public class ArrayLength extends Exp {
    private Exp array;

    public ArrayLength(Exp array, int line, int column) {
        this.array = array;
        setPosition(line, column);
    }

    public Exp getArray() {
        return array;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ArrayLength{\n");
        sb.append("array=").append(array).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
