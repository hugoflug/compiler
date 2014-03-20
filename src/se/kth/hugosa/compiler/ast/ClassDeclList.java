package se.kth.hugosa.compiler.ast;

import java.util.ArrayList;

public class ClassDeclList {
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
}
