package se.kth.hugosa.compiler.visitors;

import se.kth.hugosa.compiler.ast.*;
import se.kth.hugosa.compiler.table.Table;

public class SymbolTableCreator implements Visitor {
    private Table<Type> typeTable;

    public SymbolTableCreator() {
        typeTable = new Table<Type>();
    }

    @Override
    public void visit(And and) {

    }

    @Override
    public void visit(ArrayAssign arrayAssign) {

    }

    @Override
    public void visit(ArrayLength arrayLength) {

    }

    @Override
    public void visit(ArrayLookup arrayLookup) {

    }

    @Override
    public void visit(Assign assign) {

    }

    @Override
    public void visit(Block block) {

    }

    @Override
    public void visit(BooleanType booleanType) {

    }

    @Override
    public void visit(ClassDecl classDecl) {

    }

    @Override
    public void visit(Equal equal) {

    }

    @Override
    public void visit(False f) {

    }

    @Override
    public void visit(Formal formal) {

    }

    @Override
    public void visit(Identifier id) {

    }

    @Override
    public void visit(If i) {

    }

    @Override
    public void visit(IfWithoutElse ifWithoutElse) {

    }

    @Override
    public void visit(IntArrayType intArrayType) {

    }

    @Override
    public void visit(IntLit intLit) {

    }

    @Override
    public void visit(IntType intType) {

    }

    @Override
    public void visit(LessThan lessThan) {

    }

    @Override
    public void visit(LessOrEqualThan lessThan) {

    }

    @Override
    public void visit(MainClass main) {

    }

    @Override
    public void visit(MethodCall call) {

    }

    @Override
    public void visit(MethodDecl decl) {

    }

    @Override
    public void visit(Minus minus) {

    }

    @Override
    public void visit(MoreThan moreThan) {

    }

    @Override
    public void visit(MoreOrEqualThan moreOrEqualThan) {

    }

    @Override
    public void visit(Mult mult) {

    }

    @Override
    public void visit(NewObject object) {

    }

    @Override
    public void visit(NewArray array) {

    }

    @Override
    public void visit(Not not) {

    }

    @Override
    public void visit(NotEqual notEqual) {

    }

    @Override
    public void visit(ObjectType objectType) {

    }

    @Override
    public void visit(Or or) {

    }

    @Override
    public void visit(Plus plus) {

    }

    @Override
    public void visit(Program program) {
        program.getMainClass().accept(this);
        program.getClassDeclarations().acceptAll(this);
    }

    @Override
    public void visit(Syso syso) {

    }

    @Override
    public void visit(This t) {

    }

    @Override
    public void visit(True tru) {

    }

    @Override
    public void visit(Type type) {

    }

    @Override
    public void visit(VarDecl varDecl) {
        String name = varDecl.getId().getName();
        Type type = varDecl.getType();
        boolean old = typeTable.put(name, type);
        if (old) {
            //signal an error somehow
        }
    }

    @Override
    public void visit(While w) {

    }
}
