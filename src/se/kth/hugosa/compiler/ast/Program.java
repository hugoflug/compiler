package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class Program {
    private MainClass mainClass;
    private ClassDeclList classDeclarations;

    public Program(MainClass mainClass, ClassDeclList classDeclarations) {
        this.mainClass = mainClass;
        this.classDeclarations = classDeclarations;
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
}
