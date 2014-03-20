package se.kth.hugosa.compiler.ast;

import java.util.ArrayList;

public class FormalList {
    private ArrayList<Formal> formalList;

    public FormalList() {
        formalList = new ArrayList<Formal>();
    }

    public void addFormal(Formal formal) {
        formalList.add(formal);
    }

    public Formal get(int i) {
        return formalList.get(i);
    }

    public int size() {
        return formalList.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("FormalList[\n");
        for (Formal formal : formalList) {
            sb.append(formal);
            sb.append("\n");
        }
        sb.append("]\n");
        return sb.toString();
    }
}
