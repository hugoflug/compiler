package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class Block {
    private StmtList stmtList;

    public Block(StmtList stmtList) {
        this.stmtList = stmtList;
    }

    public StmtList getStmtList() {
        return stmtList;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
