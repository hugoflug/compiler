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
}
