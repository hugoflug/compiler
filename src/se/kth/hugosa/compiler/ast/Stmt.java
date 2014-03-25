package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.TypeVisitor;
import se.kth.hugosa.compiler.visitors.Visitor;

public abstract class Stmt {
    public abstract void accept(Visitor v);
    public abstract Type accept(TypeVisitor v);
}
