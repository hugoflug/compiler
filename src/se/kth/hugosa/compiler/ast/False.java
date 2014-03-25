package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

public class False extends Exp {
    @Override
    public void accept(Visitor v) {

    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        return "False{}";
    }
}
