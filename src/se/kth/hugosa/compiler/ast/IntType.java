package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

public class IntType extends Type {
    public IntType() {

    }

    public IntType(int line, int column) {
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
        return "Int{}";
    }

    public String toPrettyString() {
        return "int";
    }
}
