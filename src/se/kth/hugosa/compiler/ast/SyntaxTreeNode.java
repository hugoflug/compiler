package se.kth.hugosa.compiler.ast;

public class SyntaxTreeNode {
    private int line, column;

    protected void setPosition(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

}
