package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class NewArray extends Exp {
    private Exp arraySize;

    public NewArray(Exp arraySize) {
        this.arraySize = arraySize;
    }

    public Exp getArraySize() {
        return arraySize;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NewArray{\n");
        sb.append("arraySize=").append(arraySize).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
