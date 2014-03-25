package se.kth.hugosa.compiler.visitors;

import se.kth.hugosa.compiler.ast.*;
import se.kth.hugosa.compiler.table.ClassTable;
import se.kth.hugosa.compiler.table.MethodTable;
import se.kth.hugosa.compiler.table.Table;

import java.util.HashMap;
import java.util.Map;

public class SymbolTableCreator implements Visitor {
    private Map<String, ClassTable> classes;
    private ClassTable currentClass;
    private MethodTable currentMethod;
    private Errors errors;

    public Map<String, ClassTable> getClasses() {
        return classes;
    }

    public Errors getErrors() {
        return errors;
    }

    public SymbolTableCreator() {
        classes = new HashMap<String, ClassTable>();
        errors = new Errors();
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
        block.getStmtList().acceptAll(this);
    }

    @Override
    public void visit(BooleanType booleanType) {

    }

    @Override
    public void visit(ClassDecl classDecl) {
        String className = classDecl.getClassName().getName();
        currentClass = new ClassTable(className);
        if (classes.containsKey(className)) {
            errors.addError(new Errors.RedefinitionError(className));
        } else {
            classes.put(className, currentClass);
        }
        classDecl.getVarDeclarations().acceptAll(this);
        classDecl.getMethodDeclarations().acceptAll(this);
        currentClass = null;
    }

    @Override
    public void visit(Equal equal) {

    }

    @Override
    public void visit(False f) {

    }

    @Override
    public void visit(Formal formal) {
        Type type = formal.getType();
        String name = formal.getName().getName();
        if (currentMethod.hasParam(name)) {
            errors.addError(new Errors.RedefinitionError(name, currentMethod.getName()));
        } else {
            currentMethod.setParam(name, type);
        }
    }

    @Override
    public void visit(Identifier id) {

    }

    @Override
    public void visit(If i) {
        i.getThenStmt().accept(this);
        i.getElseStmt().accept(this);
    }

    @Override
    public void visit(IfWithoutElse ifWithoutElse) {
        ifWithoutElse.getThenStmt().accept(this);
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
        String className = main.getName().getName();
        currentClass = new ClassTable(className);

        if (classes.containsKey(className)) {
            errors.addError(new Errors.RedefinitionError(className));
        } else {
            classes.put(main.getName().getName(), currentClass);
        }

        currentMethod = new MethodTable("main", null);

        if (currentClass.hasMethod("main")) {
            errors.addError(new Errors.RedefinitionError("main", className));
        } else {
            currentClass.setMethod("main", currentMethod);
        }

        main.getVarDeclarations().acceptAll(this);
        main.getStatements().acceptAll(this);
        currentMethod = null;
        currentClass = null;
    }

    @Override
    public void visit(MethodCall call) {

    }

    @Override
    public void visit(MethodDecl decl) {
        String name = decl.getName().getName();
        currentMethod = new MethodTable(name, decl.getType());
        currentClass.setMethod(name, currentMethod);
        decl.getArgumentList().acceptAll(this);
        decl.getVarDeclarations().acceptAll(this);
        decl.getStatements().acceptAll(this);
        currentMethod = null;
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
        Type type = varDecl.getType();
        String name = varDecl.getId().getName();

        if (currentMethod != null) {
            if (currentMethod.hasLocal(name) || currentMethod.hasParam(name)) {
                errors.addError(new Errors.RedefinitionError(name, currentMethod.getName()));
            } else {
                currentMethod.setLocal(name, type);
            }
        } else {
            if (currentClass.hasField(name)) {
                errors.addError(new Errors.RedefinitionError(name, currentClass.getName()));
            } else {
                currentClass.setType(name, type);
            }
        }
    }

    @Override
    public void visit(While w) {
        w.getStatement().accept(this);
    }
}
