package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.TypeVisitor;
import se.kth.hugosa.compiler.visitors.Visitor;

public class Block extends Stmt {
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

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Block{\n");
        sb.append("stmtList=").append(stmtList).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
