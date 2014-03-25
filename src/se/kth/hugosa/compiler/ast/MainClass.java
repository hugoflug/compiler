package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.TypeVisitor;
import se.kth.hugosa.compiler.visitors.Visitor;

public class MainClass {
    private Identifier name;
    private Identifier stdArgsName;
    private VarDeclList varDeclarations;
    private StmtList statements;

    public MainClass(Identifier name, Identifier stdArgsName, VarDeclList varDeclarations, StmtList statements) {
        this.name = name;
        this.stdArgsName = stdArgsName;
        this.varDeclarations = varDeclarations;
        this.statements = statements;
    }

    public Identifier getName() {
        return name;
    }

    public Identifier getStdArgsName() {
        return stdArgsName;
    }

    public VarDeclList getVarDeclarations() {
        return varDeclarations;
    }

    public StmtList getStatements() {
        return statements;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MainClass{\n");
        sb.append("name=").append(name).append("\n");
        sb.append("stdArgsName=").append(stdArgsName).append("\n");
        sb.append("varDeclarations=").append(varDeclarations).append("\n");
        sb.append("statements=").append(statements).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
