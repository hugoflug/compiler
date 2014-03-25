package se.kth.hugosa.compiler;

public class CompilationException extends Exception {
    private Errors errors;

    public CompilationException(Errors errors) {
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
