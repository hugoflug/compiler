package se.kth.hugosa.compiler.typechecking;

import se.kth.hugosa.compiler.ast.*;
import se.kth.hugosa.compiler.symboltable.ClassTable;

import java.util.Map;

public class TypeChecker implements TypeVisitor {
    private Map<String, ClassTable> classes;

    public TypeChecker(Map<String, ClassTable> classes) {
        this.classes = classes;
    }

    @Override
    public Type visit(And and) {
        return null;
    }

    @Override
    public Type visit(ArrayAssign arrayAssign) {
        return null;
    }

    @Override
    public Type visit(ArrayLength arrayLength) {
        return null;
    }

    @Override
    public Type visit(ArrayLookup arrayLookup) {
        return null;
    }

    @Override
    public Type visit(Assign assign) {
        return null;
    }

    @Override
    public Type visit(Block block) {
        return null;
    }

    @Override
    public Type visit(BooleanType booleanType) {
        return null;
    }

    @Override
    public Type visit(ClassDecl classDecl) {
        return null;
    }

    @Override
    public Type visit(Equal equal) {
        return null;
    }

    @Override
    public Type visit(False f) {
        return null;
    }

    @Override
    public Type visit(Formal formal) {
        return null;
    }

    @Override
    public Type visit(Identifier id) {
        return null;
    }

    @Override
    public Type visit(If i) {
        return null;
    }

    @Override
    public Type visit(IfWithoutElse ifWithoutElse) {
        return null;
    }

    @Override
    public Type visit(IntArrayType intArrayType) {
        return null;
    }

    @Override
    public Type visit(IntLit intLit) {
        return null;
    }

    @Override
    public Type visit(IntType intType) {
        return null;
    }

    @Override
    public Type visit(LessThan lessThan) {
        return null;
    }

    @Override
    public Type visit(LessOrEqualThan lessThan) {
        return null;
    }

    @Override
    public Type visit(MainClass main) {

        return null;
    }

    @Override
    public Type visit(MethodCall call) {
        return null;
    }

    @Override
    public Type visit(MethodDecl decl) {
        return null;
    }

    @Override
    public Type visit(Minus minus) {
        return null;
    }

    @Override
    public Type visit(MoreThan moreThan) {
        return null;
    }

    @Override
    public Type visit(MoreOrEqualThan moreOrEqualThan) {
        return null;
    }

    @Override
    public Type visit(Mult mult) {
        return null;
    }

    @Override
    public Type visit(NewObject object) {
        return null;
    }

    @Override
    public Type visit(NewArray array) {
        return null;
    }

    @Override
    public Type visit(Not not) {
        return null;
    }

    @Override
    public Type visit(NotEqual notEqual) {
        return null;
    }

    @Override
    public Type visit(ObjectType objectType) {
        return null;
    }

    @Override
    public Type visit(Or or) {
        return null;
    }

    @Override
    public Type visit(Plus plus) {
        return null;
    }

    @Override
    public Type visit(Program program) {
        return null;
    }

    @Override
    public Type visit(Syso syso) {
        return null;
    }

    @Override
    public Type visit(This t) {
        return null;
    }

    @Override
    public Type visit(True tru) {
        return null;
    }

    @Override
    public Type visit(Type type) {
        return null;
    }

    @Override
    public Type visit(VarDecl varDecl) {
        return null;
    }

    @Override
    public Type visit(While w) {
        return null;
    }
}
