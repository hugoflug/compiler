package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

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

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NewObject{\n");
        sb.append("name=").append(name).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
