package se.kth.hugosa.compiler.symboltable;

import se.kth.hugosa.compiler.CompilationException;

public class RedefinitionException extends CompilationException {
    private String redeffedVar;
    private String scope;
    private int line = -1, column = -1;

    public RedefinitionException(String redeffedVar, String scope) {
        this.redeffedVar = redeffedVar;
        this.scope = scope;
    }

    public RedefinitionException(String redeffedVar) {
        this.redeffedVar = redeffedVar;
        this.scope = null;
    }

    public RedefinitionException(String redeffedVar, String scope, int line, int column) {
        this.redeffedVar = redeffedVar;
        this.scope = scope;
        this.line = line;
        this.column = column;
    }

    public RedefinitionException(String redeffedVar, int line, int column) {
        this.redeffedVar = redeffedVar;
        this.scope = null;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();

       sb.append("RedefinitionException ");

       if (line != -1) {
           sb.append("(line " + line + ", column " + column + ")");
       }
       sb.append(": " + redeffedVar + " already defined");

       if (scope != null) {
           sb.append(" in " + scope);
       }

       return sb.toString();
    }
}
