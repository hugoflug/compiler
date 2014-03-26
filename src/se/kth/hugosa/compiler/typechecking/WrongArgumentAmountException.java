package se.kth.hugosa.compiler.typechecking;

import se.kth.hugosa.compiler.CompilationException;

public class WrongArgumentAmountException extends CompilationException {
    private String methodName;
    private int args;
    private int expectedArgs;

    public WrongArgumentAmountException(String methodName) {
        this.methodName = methodName;
        this.args = args;
        this.expectedArgs = expectedArgs;
    }

    @Override
    public String toString() {
        return methodName + " was called with the wrong amount of arguments";
    }
}
