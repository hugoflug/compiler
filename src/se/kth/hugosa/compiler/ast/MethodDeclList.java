package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

import java.util.ArrayList;

public class MethodDeclList {
    private ArrayList<MethodDecl> methodDeclList;

    public MethodDeclList() {
        methodDeclList = new ArrayList<MethodDecl>();
    }

    public void addMethodDecl(MethodDecl methodDecl) {
        methodDeclList.add(methodDecl);
    }

    public MethodDecl get(int i) {
        return methodDeclList.get(i);
    }

    public int size() {
        return methodDeclList.size();
    }
}
