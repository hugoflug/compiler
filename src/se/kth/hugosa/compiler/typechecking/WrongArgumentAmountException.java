package se.kth.hugosa.compiler.typechecking;

import se.kth.hugosa.compiler.CompilationException;

public class WrongArgumentAmountException extends CompilationException {
    private String methodName;
    private int args;
    private int expectedArgs;
    private int line;
    private int column;

    public WrongArgumentAmountException(String methodName, int line, int column) {
        this.methodName = methodName;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return "WrongArgumentAmountException (line " + line + ", column " + column + "): " + methodName + " was called with the wrong amount of arguments";
    }
}
