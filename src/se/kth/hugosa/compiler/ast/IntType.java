package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class IntType extends Type {
    public void accept(Visitor v) {
        v.visit(this);
    }
}
