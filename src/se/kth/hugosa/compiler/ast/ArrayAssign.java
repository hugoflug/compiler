package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.TypeVisitor;
import se.kth.hugosa.compiler.visitors.Visitor;

public class ArrayAssign extends Stmt {
    private Identifier id;
    private Exp index, newValue;

    public ArrayAssign(Identifier id, Exp index, Exp newValue) {
        this.id = id;
        this.index = index;
        this.newValue = newValue;
    }

    public Identifier getId() {
        return id;
    }

    public Exp getIndex() {
        return index;
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
        final StringBuilder sb = new StringBuilder("ArrayAssign{\n");
        sb.append("id=").append(id).append("\n");
        sb.append("index=").append(index).append("\n");
        sb.append("newValue=").append(newValue).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
