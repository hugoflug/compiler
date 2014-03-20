package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class NewObject extends Exp {
    private Identifier name;

    public NewObject(Identifier name) {
        this.name = name;
    }

    public Identifier getName() {
        return name;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
