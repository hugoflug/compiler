package se.kth.hugosa.compiler.ast;

import java.util.ArrayList;

public class VarDeclList {
    private ArrayList<VarDecl> varDeclList;

    private VarDeclList() {
        varDeclList = new ArrayList<VarDecl>();
    }

    public void addVarDecl(VarDecl varDecl) {
        varDeclList.add(varDecl);
    }

    public VarDecl get(int i) {
        return varDeclList.get(i);
    }

    public int size() {
        return varDeclList.size();
    }
}
