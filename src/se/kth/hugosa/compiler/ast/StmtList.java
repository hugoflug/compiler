package se.kth.hugosa.compiler.ast;

import java.util.ArrayList;

public class StmtList {
    private ArrayList<Stmt> stmtList;

    public StmtList() {
        stmtList = new ArrayList<Stmt>();
    }

    public void addStmt(Stmt statement) {
        stmtList.add(statement);
    }

    public Stmt get(int i) {
        return stmtList.get(i);
    }

    public int size() {
        return stmtList.size();
    }
}
