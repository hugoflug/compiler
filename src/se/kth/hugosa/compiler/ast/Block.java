package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

public class Block extends Stmt {
    private StmtList stmtList;

    public Block(StmtList stmtList, int line, int column) {
        this.stmtList = stmtList;
        setPosition(line, column);
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
