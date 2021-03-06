package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

public class ArrayLookup extends Exp {
    private Exp array, index;

    public ArrayLookup(Exp array, Exp index, int line, int column) {
        this.array = array;
        this.index = index;
        setPosition(line, column);
    }

    public Exp getArray() {
        return array;
    }

    public Exp getIndex() {
        return index;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ArrayLookup{\n");
        sb.append("array=").append(array).append("\n");
        sb.append("index=").append(index).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
