package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

public abstract class Type extends SyntaxTreeNode {
    public abstract void accept(Visitor v);
    public abstract Type accept(TypeVisitor v);
}
