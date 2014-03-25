package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

public class This extends Exp {
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        return "This{}";
    }
}
