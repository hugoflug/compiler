package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public abstract class Type {
    public abstract void accept(Visitor v);
}
