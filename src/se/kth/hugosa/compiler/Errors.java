package se.kth.hugosa.compiler;

import se.kth.hugosa.compiler.ast.Type;

import java.util.ArrayList;
import java.util.List;

public class Errors {
    public interface MiniJavaError {}

    public static class RedefinitionError implements MiniJavaError {
        private String redeffedVar;
        private String scope;

        public RedefinitionError(String redeffedVar, String scope) {
            this.redeffedVar = redeffedVar;
            this.scope = scope;
        }

        public RedefinitionError(String redeffedVar) {
            this.redeffedVar = redeffedVar;
            this.scope = null;
        }

        @Override
        public String toString() {
           if (scope != null) {
               return redeffedVar + " already defined in " + scope;
           } else {
               return redeffedVar + " already defined";
           }
        }
    }

    public static class TypeError implements MiniJavaError {
        private Type type;
        private String var;
        private Type shouldBe;

        public TypeError(Type type, Type shouldBe) {
            this.type = type;
            this.shouldBe = shouldBe;
            this.var = null;
        }

        public TypeError(Type type, Type shouldBe, String var) {
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

    public static class UndefinedError implements MiniJavaError {
        private String var;

        public UndefinedError(String var) {
            this.var = var;
        }

        @Override
        public String toString() {
            return var + " is undefined";
        }
    }

    public static class WrongArgumentNumberError implements MiniJavaError {
        private String methodName;
        private int args;
        private int expectedArgs;

        public WrongArgumentNumberError(String methodName) {
            this.methodName = methodName;
            this.args = args;
            this.expectedArgs = expectedArgs;
        }

        @Override
        public String toString() {
            return methodName + " was called with the wrong amount of arguments";
        }
    }


    private List<MiniJavaError> errors;

    public Errors() {
        errors = new ArrayList<MiniJavaError>();
    }

    public void addError(MiniJavaError error) {
        errors.add(error);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<MiniJavaError> getErrors() {
        return errors;
    }
}
