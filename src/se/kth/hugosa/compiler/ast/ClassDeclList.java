package se.kth.hugosa.compiler.ast;

import java.util.ArrayList;

public class ClassDeclList extends SyntaxTreeNode {
    private ArrayList<ClassDecl> classDeclarations;

    public ClassDeclList() {
        classDeclarations = new ArrayList<ClassDecl>();
    }

    public void addClassDecl(ClassDecl classDecl) {
        classDeclarations.add(classDecl);
    }

    public ClassDecl get(int i) {
        return classDeclarations.get(i);
    }

    public int size() {
        return classDeclarations.size();
    }

    public void acceptAll(Visitor visitor) {
        for (ClassDecl classDecl : classDeclarations) {
            classDecl.accept(visitor);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ClassDeclList[\n");
        for (ClassDecl classDecl : classDeclarations) {
            sb.append(classDecl);
            sb.append("\n");
        }
        sb.append("]\n");
        return sb.toString();
    }
}
