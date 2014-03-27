package se.kth.hugosa.compiler.typechecking;

import se.kth.hugosa.compiler.CompilationException;
import se.kth.hugosa.compiler.ast.*;
import se.kth.hugosa.compiler.symboltable.ClassTable;
import se.kth.hugosa.compiler.symboltable.MethodTable;

import java.util.Map;

public class TypeChecker implements TypeVisitor {
    private Map<String, ClassTable> classes;
    private ClassTable currentClass;
    private MethodTable currentMethod;

    public TypeChecker(Map<String, ClassTable> classes) {
        this.classes = classes;
    }

    public void typeCheck(Program program) throws CompilationException {
        visit(program);
    }

    private boolean assertType(Exp exp, Type expectedType) {
        Type actualType = exp.accept(this);
        return assertType(actualType, expectedType, exp.getLine(), exp.getColumn());
    }

    private boolean assertType(Type actualType, Type expectedType, int line, int column) {
        if (!(actualType.getClass().equals(expectedType.getClass()))) {
            throw new WrongTypeException(actualType, expectedType, line, column);
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
                throw new UndefinedVariableException(id, arrayAssign.getLine(), arrayAssign.getColumn());
            }
        }


        assertType(arrayType, new IntArrayType(), arrayAssign.getLine(), arrayAssign.getColumn());
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
        assertType(assigneeType, newValueType, assign.getLine(), assign.getColumn());
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
        assertType(leftOpType, rightOpType, equal.getLine(), equal.getColumn());
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
            if (idType == null) {
                idType = currentMethod.getParamType(name);
                if (idType == null) {
                    idType = currentClass.getType(name);
                    if (idType == null) {
                        throw new UndefinedVariableException(name, id.getLine(), id.getColumn());
                    } else {
                        return idType;
                    }
                } else {
                    return idType;
                }
            } else  {
                return idType;
            }
        }
        idType = currentClass.getType(name);
        if (idType == null) {
            throw new UndefinedVariableException(name, id.getLine(), id.getColumn());
        }
        return idType;
    }

    @Override
    public Type visit(If i) {
        assertType(i.getCondition(), new BooleanType());
        i.getThenStmt().accept(this);
        i.getElseStmt().accept(this);
        return null;
    }

    @Override
    public Type visit(IfWithoutElse ifWithoutElse) {
        assertType(ifWithoutElse.getCondition(), new BooleanType());
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

        String typeName = ((ObjectType)object.accept(this)).getName();
        if (!isObject) {
            throw new WrongTypeException(object.accept(this), new ObjectType(typeName), object.getLine(), object.getColumn());
        }

        ClassTable classTable = classes.get(typeName);
        if (classTable == null) {
        }

        String methodName = call.getMethodName().getName();
        MethodTable method = classTable.getMethod(methodName);
        if (method == null) {
            throw new UndefinedVariableException(methodName, call.getMethodName().getLine(), call.getMethodName().getColumn());
        }

        ExpList callParams = call.getArgumentList();
        int i = 0;
        for (Map.Entry<String, Type> param : method.getParams()) {
            if (i == callParams.size()) {
                throw new WrongArgumentAmountException(methodName, call.getLine(), call.getColumn());
            }

            Type callType = callParams.get(i).accept(this);
            Type methodType = param.getValue();
            assertType(methodType, callType, call.getLine(), call.getColumn());
            i++;
        }
        if (i < callParams.size()) {
            throw new WrongArgumentAmountException(methodName, call.getLine(), call.getColumn());
        }
        return method.getType();
    }

    @Override
    public Type visit(MethodDecl decl) {
        currentMethod = currentClass.getMethod(decl.getName().getName());

        VarDeclList varDecls = decl.getVarDeclarations();
        for (int i = 0; i < varDecls.size(); i++) {
            varDecls.get(i).accept(this);
        }

        StmtList statements = decl.getStatements();
        for (int i = 0; i < statements.size(); i++) {
            statements.get(i).accept(this);
        }

        assertType(decl.getReturnValue(), decl.getType());

        currentMethod = null;
        return null;
    }

    @Override
    public Type visit(Minus minus) {
        assertType(minus.getLeftOp(), new IntType());
        assertType(minus.getRightOp(), new IntType());
        return new IntType();
    }

    @Override
    public Type visit(MoreThan moreThan) {
        assertType(moreThan.getLeftOp(), new IntType());
        assertType(moreThan.getRightOp(), new IntType());
        return new BooleanType();
    }

    @Override
    public Type visit(MoreOrEqualThan moreOrEqualThan) {
        assertType(moreOrEqualThan.getLeftOp(), new IntType());
        assertType(moreOrEqualThan.getRightOp(), new IntType());
        return new BooleanType();
    }

    @Override
    public Type visit(Mult mult) {
        assertType(mult.getLeftOp(), new IntType());
        assertType(mult.getRightOp(), new IntType());
        return new IntType();
    }

    @Override
    public Type visit(NewObject object) {
        String name = object.getName().getName();
        if (classes.get(name) == null) {
            throw new UndefinedVariableException(name, object.getLine(), object.getColumn());
        }

        return new ObjectType(name);
    }

    @Override
    public Type visit(NewArray array) {
        assertType(array.getArraySize(), new IntType());

        return new IntArrayType();
    }

    @Override
    public Type visit(Not not) {
        assertType(not.getExpression(), new BooleanType());

        return new BooleanType();
    }

    @Override
    public Type visit(NotEqual notEqual) {
        Type leftOpType = notEqual.getLeftOp().accept(this);
        Type rightOpType = notEqual.getRightOp().accept(this);
        assertType(leftOpType, rightOpType, notEqual.getLine(), notEqual.getColumn());

        return new BooleanType();
    }

    @Override
    public Type visit(ObjectType objectType) {
        return objectType;
    }

    @Override
    public Type visit(Or or) {
        assertType(or.getLeftOp(), new BooleanType());
        assertType(or.getRightOp(), new BooleanType());
        return new BooleanType();
    }

    @Override
    public Type visit(Plus plus) {
        assertType(plus.getLeftOp(), new IntType());
        assertType(plus.getRightOp(), new IntType());
        return new IntType();
    }

    @Override
    public Type visit(Program program) {
        program.getMainClass().accept(this);
        ClassDeclList classDecls = program.getClassDeclarations();
        for (int i = 0; i < classDecls.size(); i++) {
            classDecls.get(i).accept(this);
        }
        return null;
    }

    @Override
    public Type visit(Syso syso) {
        return null;
    }

    @Override
    public Type visit(This t) {
        if (currentClass == null) {
            throw new UndefinedVariableException("this", t.getLine(), t.getColumn());
        }
        return new ObjectType(currentClass.getName());
    }

    @Override
    public Type visit(True tru) {
        return new BooleanType();
    }

    @Override
    public Type visit(Type type) {
        return type;
    }

    @Override
    public Type visit(VarDecl varDecl) {
        return null;
    }

    @Override
    public Type visit(While w) {
        assertType(w.getCondition(), new BooleanType());
        w.getStatement().accept(this);
        return null;
    }
}
