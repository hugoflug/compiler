package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

public class NewArray extends Exp {
    private Exp arraySize;

    public NewArray(Exp arraySize, int line, int column) {
        this.arraySize = arraySize;
        setPosition(line, column);
    }

    public Exp getArraySize() {
        return arraySize;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NewArray{\n");
        sb.append("arraySize=").append(arraySize).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
