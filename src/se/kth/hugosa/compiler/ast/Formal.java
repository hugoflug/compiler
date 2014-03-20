package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class Formal {
    private Type type;
    private Identifier name;

    public Formal(Type type, Identifier name) {
        this.type = type;
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public Identifier getName() {
        return name;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
