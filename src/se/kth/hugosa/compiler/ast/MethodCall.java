package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.visitors.Visitor;

public class MethodCall {
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
}
