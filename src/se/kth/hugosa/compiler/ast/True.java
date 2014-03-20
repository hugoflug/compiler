package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class True extends Exp {
    public void accept(Visitor v) {
        v.visit(this);
    }
}
