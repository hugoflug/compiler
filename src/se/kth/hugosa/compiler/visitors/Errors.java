package se.kth.hugosa.compiler.visitors;

import se.kth.hugosa.compiler.ast.Type;

import java.util.ArrayList;
import java.util.List;

public class Errors {
    public class RedefinitionError {
        private String redeffedVar;
        private String scope;

        public RedefinitionError(String redeffedVar, String scope) {
            this.redeffedVar = redeffedVar;
            this.scope = scope;
        }

        @Override
        public String toString() {
            return redeffedVar + " already defined in " + scope;
        }
    }

    public class TypeError {
        private Type type;
        private String var;
        private Type shouldBe;

        public TypeError(Type type, Type shouldBe, String var) {
            this.type = type;
            this.var = var;
            this.shouldBe = shouldBe;
        }

        @Override
        public String toString() {
            return var + " is of type " + type + ". " + shouldBe + " expected";
        }
    }

    private List<Error> errors;

    public Errors() {
        errors = new ArrayList<Error>();
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<Error> getErrors() {
        return errors;
    }
}