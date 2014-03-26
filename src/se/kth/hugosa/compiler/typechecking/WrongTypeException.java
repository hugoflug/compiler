package se.kth.hugosa.compiler.typechecking;

import se.kth.hugosa.compiler.CompilationException;
import se.kth.hugosa.compiler.ast.Type;

public class WrongTypeException extends CompilationException {
    private Type type;
    private String var;
    private Type shouldBe;

    public WrongTypeException(Type type, Type shouldBe) {
        this.type = type;
        this.shouldBe = shouldBe;
        this.var = null;
    }

    public WrongTypeException(Type type, Type shouldBe, String var) {
        this.type = type;
        this.var = var;
        this.shouldBe = shouldBe;
    }

    @Override
    public String toString() {
        if (var == null) {
            return "Actual type: " + type + ". " + shouldBe + " expected";
        } else {
            return var + "is of type: " + type + ". " + shouldBe + " expected";
        }
    }
}