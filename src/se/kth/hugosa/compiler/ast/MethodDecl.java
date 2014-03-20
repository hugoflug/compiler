package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class MethodDecl {
    private Type type;
    private Identifier name;
    private FormalList argumentList;
    private VarDeclList varDeclarations;
    private StmtList statements;
    private Exp returnValue;

    public MethodDecl(Type type, Identifier name, FormalList argumentList, VarDeclList varDeclarations, StmtList statements, Exp returnValue) {
        this.type = type;
        this.name = name;
        this.argumentList = argumentList;
        this.varDeclarations = varDeclarations;
        this.statements = statements;
        this.returnValue = returnValue;
    }

    public Type getType() {
        return type;
    }

    public Identifier getName() {
        return name;
    }

    public FormalList getArgumentList() {
        return argumentList;
    }

    public VarDeclList getVarDeclarations() {
        return varDeclarations;
    }

    public StmtList getStatements() {
        return statements;
    }

    public Exp getReturnValue() {
        return returnValue;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MethodDecl{\n");
        sb.append("type=").append(type).append("\n");
        sb.append("name=").append(name).append("\n");
        sb.append("argumentList=").append(argumentList).append("\n");
        sb.append("varDeclarations=").append(varDeclarations).append("\n");
        sb.append("statements=").append(statements).append("\n");
        sb.append("returnValue=").append(returnValue).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
