package se.kth.hugosa.compiler.typechecking;

import se.kth.hugosa.compiler.CompilationException;

public class UndefinedVariableException extends CompilationException {
    private String var;

    public UndefinedVariableException(String var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return var + " is undefined";
    }
}
