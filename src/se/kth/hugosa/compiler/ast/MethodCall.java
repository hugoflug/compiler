package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.typechecking.TypeVisitor;

public class MethodCall extends Exp {
    private Exp object;
    private Identifier methodName;
    private ExpList argumentList;

    public MethodCall(Exp object, Identifier methodName, ExpList argumentList) {
        this.object = object;
        this.methodName = methodName;
        this.argumentList = argumentList;
    }

    public Exp getObject() {
        return object;
    }

    public Identifier getMethodName() {
        return methodName;
    }

    public ExpList getArgumentList() {
        return argumentList;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MethodCall{\n");
        sb.append("object=").append(object).append("\n");
        sb.append("methodName=").append(methodName).append("\n");
        sb.append("argumentList=").append(argumentList).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
