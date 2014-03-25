package se.kth.hugosa.compiler.ast;

import java.util.ArrayList;

public class VarDeclList {
    private ArrayList<VarDecl> varDeclList;

    public VarDeclList() {
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

    public void acceptAll(Visitor visitor) {
        for (VarDecl varDecl : varDeclList) {
            varDecl.accept(visitor);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("VarDeclList[\n");
        for (VarDecl varDecl : varDeclList) {
            sb.append(varDecl);
            sb.append("\n");
        }
        sb.append("]\n");
        return sb.toString();
    }
}
