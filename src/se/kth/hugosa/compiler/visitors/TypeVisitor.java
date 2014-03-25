package se.kth.hugosa.compiler.visitors;

import se.kth.hugosa.compiler.ast.*;

public interface TypeVisitor {
    public Type visit(And and);
    public Type visit(ArrayAssign arrayAssign);
    public Type visit(ArrayLength arrayLength);
    public Type visit(ArrayLookup arrayLookup);
    public Type visit(Assign assign);
    public Type visit(Block block);
    public Type visit(BooleanType booleanType);
    public Type visit(ClassDecl classDecl);
    public Type visit(Equal equal);
    public Type visit(False f);
    public Type visit(Formal formal);
    public Type visit(Identifier id);
    public Type visit(If i);
    public Type visit(IfWithoutElse ifWithoutElse);
    public Type visit(IntArrayType intArrayType);
    public Type visit(IntLit intLit);
    public Type visit(IntType intType);
    public Type visit(LessThan lessThan);
    public Type visit(LessOrEqualThan lessThan);
    public Type visit(MainClass main);
    public Type visit(MethodCall call);
    public Type visit(MethodDecl decl);
    public Type visit(Minus minus);
    public Type visit(MoreThan moreThan);
    public Type visit(MoreOrEqualThan moreOrEqualThan);
    public Type visit(Mult mult);
    public Type visit(NewObject object);
    public Type visit(NewArray array);
    public Type visit(Not not);
    public Type visit(NotEqual notEqual);
    public Type visit(ObjectType objectType);
    public Type visit(Or or);
    public Type visit(Plus plus);
    public Type visit(Program program);
    public Type visit(Syso syso);
    public Type visit(This t);
    public Type visit(True tru);
    public Type visit(Type type);
    public Type visit(VarDecl varDecl);
    public Type visit(While w);
}

