package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public abstract class Exp {
    public abstract void accept(Visitor v);
}
