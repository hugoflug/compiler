package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class ClassDecl {
    private Identifier className;
    private VarDeclList varDeclarations;
    private MethodDeclList methodDeclarations;

    public ClassDecl(Identifier className, VarDeclList varDeclarations, MethodDeclList methodDeclarations) {
        this.className = className;
        this.varDeclarations = varDeclarations;
        this.methodDeclarations = methodDeclarations;
    }

    public Identifier getClassName() {
        return className;
    }

    public VarDeclList getVarDeclarations() {
        return varDeclarations;
    }

    public MethodDeclList getMethodDeclarations() {
        return methodDeclarations;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
