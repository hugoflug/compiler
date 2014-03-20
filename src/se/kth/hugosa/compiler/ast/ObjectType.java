package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class ObjectType extends Type {
    private String name;

    public ObjectType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ObjectType{\n");
        sb.append("name='").append(name).append('\'').append("\n");
        sb.append('}');
        return sb.toString();
    }
}
