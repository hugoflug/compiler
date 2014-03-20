package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class IntArrayType extends Type {
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        return "This{}";
    }
}
