package se.kth.hugosa.compiler.codegen;

public class LabelGenerator {
    private int counter;
    public LabelGenerator() {
        counter = 0;
    }

    public String getLabel() {
        String val = "l" + counter;
        counter++;
        return val;
    }
}
