package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

public class VarDecl extends SyntaxTreeNode {
    private Type type;
    private Identifier id;

    public VarDecl(Type type, Identifier id, int line, int column) {
        this.type = type;
        this.id = id;
        setPosition(line, column);
    }

    public Type getType() {
        return type;
    }

    public Identifier getId() {
        return id;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VarDecl{\n");
        sb.append("type=").append(type).append("\n");
        sb.append("id=").append(id).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
