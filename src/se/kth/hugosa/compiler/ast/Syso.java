package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

public class Syso extends Stmt {
    private Exp printee;

    public Syso(Exp printee, int line, int column) {
        this.printee = printee;
        setPosition(line, column);
    }

    public Exp getPrintee() {
        return printee;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Syso{\n");
        sb.append("printee=").append(printee).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
