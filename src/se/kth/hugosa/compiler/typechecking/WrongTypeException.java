package se.kth.hugosa.compiler.typechecking;

import se.kth.hugosa.compiler.CompilationException;
import se.kth.hugosa.compiler.ast.Type;

public class WrongTypeException extends CompilationException {
    private Type type;
    private String var;
    private Type shouldBe;
    private int line, column;

    public WrongTypeException(Type type, Type shouldBe, int line, int column) {
        this.type = type;
        this.shouldBe = shouldBe;
        this.var = null;
        this.line = line;
    }

    public WrongTypeException(Type type, Type shouldBe, String var, int line, int column) {
        this.type = type;
        this.var = var;
        this.shouldBe = shouldBe;
        this.column = column;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WrongTypeException (line " + line + ", column " + column + "): ");
        if (var == null) {
            sb.append("Actual type: " + type.toPrettyString() + ". " + shouldBe.toPrettyString() + " expected");
        } else {
            sb.append(var + "is of type: " + type.toPrettyString() + ". " + shouldBe.toPrettyString() + " expected");
        }
        return sb.toString();
    }
}