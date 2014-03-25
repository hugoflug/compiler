package se.kth.hugosa.compiler.ast;

import java.util.ArrayList;

public class ExpList {
    private ArrayList<Exp> expList;

    public ExpList() {
        expList = new ArrayList<Exp>();
    }

    public void addExp(Exp exp) {
        expList.add(exp);
    }

    public Exp get(int i) {
        return expList.get(i);
    }

    public int size() {
        return expList.size();
    }

    public void acceptAll(Visitor visitor) {
        for (Exp exp : expList) {
            exp.accept(visitor);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ExpList[\n");
        for (Exp exp : expList) {
            sb.append(exp);
            sb.append("\n");
        }
        sb.append("]\n");
        return sb.toString();
    }
}
