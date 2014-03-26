package se.kth.hugosa.compiler.typechecking;

import se.kth.hugosa.compiler.Errors;
import se.kth.hugosa.compiler.ast.*;
import se.kth.hugosa.compiler.symboltable.ClassTable;
import se.kth.hugosa.compiler.symboltable.MethodTable;

import java.util.Map;

public class TypeChecker implements TypeVisitor {
    private Map<String, ClassTable> classes;
    private ClassTable currentClass;
    private MethodTable currentMethod;
    private Errors errors;

    public TypeChecker(Map<String, ClassTable> classes) {
        this.classes = classes;
        errors = new Errors();
    }

    private boolean assertType(Exp exp, Type expectedType) {
        Type actualType = exp.accept(this);
        return assertType(actualType, expectedType);
    }

    private boolean assertType(Type actualType, Type expectedType) {
        if (!(actualType.getClass().equals(expectedType.getClass()))) {
            errors.addError(new Errors.TypeError(actualType, expectedType));
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Type visit(And and) {
        assertType(and.getLeftOp(), new BooleanType());
        assertType(and.getRightOp(), new BooleanType());
        return new BooleanType();
    }

    @Override
    public Type visit(ArrayAssign arrayAssign) {
        String id = arrayAssign.getId().getName();
        Type arrayType = currentMethod.getLocalType(id);
        if (arrayType == null) {
            arrayType = currentClass.getType(id);
            if (arrayType == null) {
                errors.addError(new Errors.UndefinedError(id));
            }
        }

        assertType(arrayType, new IntArrayType());
        assertType(arrayAssign.getIndex(), new IntType());
        assertType(arrayAssign.getNewValue(), new IntType());

        return null;
    }

    @Override
    public Type visit(ArrayLength arrayLength) {
        assertType(arrayLength.getArray(), new IntArrayType());
        return new IntType();
    }

    @Override
    public Type visit(ArrayLookup arrayLookup) {
        assertType(arrayLookup.getArray(), new IntArrayType());
        assertType(arrayLookup.getIndex(), new IntType());
        return new IntType();
    }

    @Override
    public Type visit(Assign assign) {
        Type assigneeType = assign.getAssignee().accept(this);
        Type newValueType = assign.getNewValue().accept(this);
        assertType(assigneeType, newValueType);
        return null;
    }

    @Override
    public Type visit(Block block) {
        StmtList stmtList = block.getStmtList();
        for (int i = 0; i < stmtList.size(); i++) {
            stmtList.get(i).accept(this);
        }
        return null;
    }

    @Override
    public Type visit(BooleanType booleanType) {
        return new BooleanType();
    }

    @Override
    public Type visit(ClassDecl classDecl) {
        currentClass = classes.get(classDecl.getClassName().getName());

        VarDeclList varDecls = classDecl.getVarDeclarations();
        for (int i = 0; i < varDecls.size(); i++) {
            varDecls.get(i).accept(this);
        }
        MethodDeclList methodDecls = classDecl.getMethodDeclarations();
        for (int i = 0; i < methodDecls.size(); i++) {
            methodDecls.get(i).accept(this);
        }

        currentClass = null;
        return null;
    }

    @Override
    public Type visit(Equal equal) {
        Type leftOpType = equal.getLeftOp().accept(this);
        Type rightOpType = equal.getRightOp().accept(this);
        assertType(leftOpType, rightOpType);
        return new BooleanType();
    }

    @Override
    public Type visit(False f) {
        return new BooleanType();
    }

    @Override
    public Type visit(Formal formal) {
        return formal.getType();
    }

    @Override
    public Type visit(Identifier id) {
        String name = id.getName();
        Type idType = null;
        if (currentMethod != null) {
            idType = currentMethod.getLocalType(name);
            if (idType != null) {
                return idType;
            }
        }
        idType = currentClass.getType(name);
        if (idType == null) {
            errors.addError(new Errors.UndefinedError(name));
        }
        return idType;
    }

    @Override
    public Type visit(If i) {
        assertType(i.getCondition().accept(this), new BooleanType());
        i.getThenStmt().accept(this);
        i.getElseStmt().accept(this);
        return null;
    }

    @Override
    public Type visit(IfWithoutElse ifWithoutElse) {
        assertType(ifWithoutElse.getCondition().accept(this), new BooleanType());
        ifWithoutElse.getThenStmt().accept(this);
        return null;
    }

    @Override
    public Type visit(IntArrayType intArrayType) {
        return new IntArrayType();
    }

    @Override
    public Type visit(IntLit intLit) {
        return new IntType();
    }

    @Override
    public Type visit(IntType intType) {
        return new IntType();
    }

    @Override
    public Type visit(LessThan lessThan) {
        assertType(lessThan.getLeftOp(), new IntType());
        assertType(lessThan.getRightOp(), new IntType());
        return new BooleanType();
    }

    @Override
    public Type visit(LessOrEqualThan lessThan) {
        assertType(lessThan.getLeftOp(), new IntType());
        assertType(lessThan.getRightOp(), new IntType());
        return new BooleanType();
    }

    @Override
    public Type visit(MainClass main) {
        String className = main.getName().getName();
        currentClass = classes.get(className);

        currentMethod = currentClass.getMethod("main");
        StmtList statements = main.getStatements();
        for (int i = 0; i < statements.size(); i++) {
            statements.get(i).accept(this);
        }
        currentMethod = null;
        currentClass = null;
        return null;
    }

    @Override
    public Type visit(MethodCall call) {
        Exp object = call.getObject();
        boolean isObject = assertType(object, new ObjectType(""));

        if (isObject) {
            String typeName = ((ObjectType)object.accept(this)).getName();
            ClassTable classTable = classes.get(typeName);
            if (classTable == null) {
                errors.addError(new Errors.UndefinedError(typeName));
            } else {
                String methodName = call.getMethodName().getName();
                MethodTable method = classTable.getMethod(methodName);
                if (method == null) {
                    errors.addError(new Errors.UndefinedError(methodName));
                } else {
                    ExpList params = call.getArgumentList();
                    for (int i = 0; i < params.size(); i++) {
                        Exp exp = params.get(i);
                        Type paramType = exp.accept(this);

                    }
                }
            }
        }
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
