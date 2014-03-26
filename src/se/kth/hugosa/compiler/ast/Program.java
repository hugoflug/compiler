package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

public class Program extends SyntaxTreeNode {
    private MainClass mainClass;
    private ClassDeclList classDeclarations;

    public Program(MainClass mainClass, ClassDeclList classDeclarations, int line, int column) {
        this.mainClass = mainClass;
        this.classDeclarations = classDeclarations;
        setPosition(line, column);
    }

    public MainClass getMainClass() {
        return mainClass;
    }

    public ClassDeclList getClassDeclarations() {
        return classDeclarations;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Program{\n");
        sb.append("mainClass=").append(mainClass).append("\n");
        sb.append("classDeclarations=").append(classDeclarations).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
