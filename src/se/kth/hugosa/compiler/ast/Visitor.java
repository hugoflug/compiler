package se.kth.hugosa.compiler.ast;

import se.kth.hugosa.compiler.ast.*;

public interface Visitor {
    public void visit(And and);
    public void visit(ArrayAssign arrayAssign);
    public void visit(ArrayLength arrayLength);
    public void visit(ArrayLookup arrayLookup);
    public void visit(Assign assign);
    public void visit(Block block);
    public void visit(BooleanType booleanType);
    public void visit(ClassDecl classDecl);
    public void visit(Equal equal);
    public void visit(False f);
    public void visit(Formal formal);
    public void visit(Identifier id);
    public void visit(If i);
    public void visit(IfWithoutElse ifWithoutElse);
    public void visit(IntArrayType intArrayType);
    public void visit(IntLit intLit);
    public void visit(IntType intType);
    public void visit(LessThan lessThan);
    public void visit(LessOrEqualThan lessThan);
    public void visit(MainClass main);
    public void visit(MethodCall call);
    public void visit(MethodDecl decl);
    public void visit(Minus minus);
    public void visit(MoreThan moreThan);
    public void visit(MoreOrEqualThan moreOrEqualThan);
    public void visit(Mult mult);
    public void visit(NewObject object);
    public void visit(NewArray array);
    public void visit(Not not);
    public void visit(NotEqual notEqual);
    public void visit(ObjectType objectType);
    public void visit(Or or);
    public void visit(Plus plus);
    public void visit(Program program);
    public void visit(Syso syso);
    public void visit(This t);
    public void visit(True tru);
    public void visit(Type type);
    public void visit(VarDecl varDecl);
    public void visit(While w);
}
