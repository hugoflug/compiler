package se.kth.hugosa.compiler.symboltable;

import se.kth.hugosa.compiler.CompilationException;

public class RedefinitionException extends CompilationException {
    private String redeffedVar;
    private String scope;

    public RedefinitionException(String redeffedVar, String scope) {
        this.redeffedVar = redeffedVar;
        this.scope = scope;
    }

    public RedefinitionException(String redeffedVar) {
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
