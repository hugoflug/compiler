package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class Syso extends Stmt {
    private Exp printee;

    public Syso(Exp printee) {
        this.printee = printee;
    }

    public Exp getPrintee() {
        return printee;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Syso{\n");
        sb.append("printee=").append(printee).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
