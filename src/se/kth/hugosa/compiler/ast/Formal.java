package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

public class Formal extends SyntaxTreeNode {
    private Type type;
    private Identifier name;

    public Formal(Type type, Identifier name, int line, int column) {
        this.type = type;
        this.name = name;
        setPosition(line, column);
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

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Formal{\n");
        sb.append("type=").append(type).append("\n");
        sb.append("name=").append(name).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
