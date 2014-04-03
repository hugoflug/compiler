package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

public class BooleanType extends Type {
    public BooleanType() {}

    public BooleanType(int line, int column) {
        setPosition(line, column);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        return "BooleanType{}";
    }

    public String toPrettyString() {
        return "boolean";
    }
}
