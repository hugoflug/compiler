package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

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


    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClassDecl{\n");
        sb.append("className=").append(className).append("\n");
        sb.append("varDeclarations=").append(varDeclarations).append("\n");
        sb.append("methodDeclarations=").append(methodDeclarations).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
