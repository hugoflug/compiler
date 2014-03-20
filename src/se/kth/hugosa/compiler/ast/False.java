package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class False extends Exp {
    @Override
    public void accept(Visitor v) {

    }

    @Override
    public String toString() {
        return "False{}";
    }
}
