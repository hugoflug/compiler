package se.kth.hugosa.compiler.typechecking;

import se.kth.hugosa.compiler.CompilationException;

public class UndefinedNameException extends CompilationException {
    private String var;
    private int line, column;

    public UndefinedNameException(String var, int line, int column) {
        this.var = var;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return "UndefinedNameException (line " + line + ", column " + column + "): " + var + " is undefined";
    }
}
