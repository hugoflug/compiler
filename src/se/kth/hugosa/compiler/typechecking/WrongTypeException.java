package se.kth.hugosa.compiler.typechecking;

import se.kth.hugosa.compiler.CompilationException;
import se.kth.hugosa.compiler.ast.Type;

import java.util.List;

public class WrongTypeException extends CompilationException {
    private Type type;
    private String var;
    private Type shouldBe;
    private int line, column;
    private List<Type> acceptedTypes;

    public WrongTypeException(Type type, Type shouldBe, int line, int column) {
        this.type = type;
        this.shouldBe = shouldBe;
        this.var = null;
        this.line = line;
        this.column = column;
    }

    public WrongTypeException(Type type, List<Type> acceptedTypes, int line, int column) {
        this.type = type;
        this.acceptedTypes = acceptedTypes;
        this.var = null;
        this.line = line;
        this.column = column;
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
        if (var == null && acceptedTypes == null) {
            sb.append("Actual type: " + type.toPrettyString() + ". " + shouldBe.toPrettyString() + " expected");
        } else if (acceptedTypes != null) {
            sb.append("Actual type: " + type.toPrettyString() + ". One of: ");
            for (Type type : acceptedTypes) {
                sb.append(type.toPrettyString() + ", ");
            }
            sb.deleteCharAt(sb.length() - 2);
            sb.append("expected");
        } else {
            sb.append(var + "is of type: " + type.toPrettyString() + ". " + shouldBe.toPrettyString() + " expected");
        }
        return sb.toString();
    }
}