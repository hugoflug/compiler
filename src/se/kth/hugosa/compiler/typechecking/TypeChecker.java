package se.kth.hugosa.compiler.typechecking;

import se.kth.hugosa.compiler.CompilationException;
import se.kth.hugosa.compiler.ast.*;
import se.kth.hugosa.compiler.symboltable.ClassTable;
import se.kth.hugosa.compiler.symboltable.MethodTable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TypeChecker implements TypeVisitor {
    private Map<String, ClassTable> classes;
    private ClassTable currentClass;
    private MethodTable currentMethod;

    public TypeChecker(Map<String, ClassTable> classes) {
        this.classes = classes;
    }

    public void setCurrentClass(ClassTable currentClass) {
        this.currentClass = currentClass;
    }

    public void setCurrentMethod(MethodTable currentMethod) {
        this.currentMethod = currentMethod;
    }

    public void typeCheck(Program program) throws CompilationException {
        visit(program);
    }

    private void assertTypes(Exp exp, List<Type> expectedTypes) {
        for (Type type : expectedTypes) {
            try {
                assertType(exp, type);
                return;
            } catch (WrongTypeException e) {}
        }
        throw new WrongTypeException(exp.accept(this), expectedTypes, exp.getLine(), exp.getColumn());
    }

    private void assertType(Exp exp, Type expectedType) {
        Type actualType = exp.accept(this);
        assertType(actualType, expectedType, exp.getLine(), exp.getColumn());
    }

    private void assertType(Type actualType, Type expectedType, int line, int column) {
        if (actualType instanceof ObjectType && expectedType instanceof ObjectType) {
            String actualTypeName = ((ObjectType)actualType).getName();
            String expectedTypeName = ((ObjectType)expectedType).getName();
            if (!actualTypeName.equals(expectedTypeName)) {
                throw new WrongTypeException(actualType, expectedType, line, column);
            } else {
                return;
            }
        }

        if (!(actualType.getClass().equals(expectedType.getClass()))) {
            throw new WrongTypeException(actualType, expectedType, line, column);
        }
    }

    private void assertTypeExistence(Type type, int line, int column) {
        if (type instanceof ObjectType) {
            String typeName = ((ObjectType)type).getName();
            if (!classes.containsKey(typeName)) {
                throw new UndefinedVariableException(typeName, line, column);
            }
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
        assertType(arrayAssign.getId(), new IntArrayType());
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
        assertType(newValueType, assigneeType, assign.getLine(), assign.getColumn());
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
        assertTypeExistence(formal.getType(), formal.getLine(), formal.getColumn());
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
                    }
                }
            }
            return idType;
        }

        //not sure if necessary
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

        VarDeclList varDecls = main.getVarDeclarations();
        for (int i = 0; i < varDecls.size(); i++) {
            varDecls.get(i).accept(this);
        }

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

        Type objectType = object.accept(this);
        if (!(objectType instanceof ObjectType)) {
            throw new WrongTypeException(object.accept(this), new ObjectType(""), object.getLine(), object.getColumn());
        }

        String typeName = ((ObjectType)objectType).getName();
        ClassTable classTable = classes.get(typeName);
        if (classTable == null) {
            throw new UndefinedVariableException(typeName, call.getMethodName().getLine(), call.getMethodName().getColumn());
        }

        String methodName = call.getMethodName().getName();
        MethodTable method = classTable.getMethod(methodName);
        if (method == null) {
            throw new UndefinedVariableException(methodName, call.getMethodName().getLine(), call.getMethodName().getColumn());
        }

        ExpList callParams = call.getArgumentList();
        Set<Map.Entry<String, Type>> methodParams = method.getParams();

        if (callParams.size() != methodParams.size()) {
            throw new WrongArgumentAmountException(methodName, call.getLine(), call.getColumn());
        }

        int i = 0;
        for (Map.Entry<String, Type> param : methodParams) {
            Type methodType = param.getValue();
            assertType(callParams.get(i), methodType);
            i++;
        }

        return method.getType();
    }

    @Override
    public Type visit(MethodDecl decl) {
        currentMethod = currentClass.getMethod(decl.getName().getName());

        FormalList formals = decl.getArgumentList();
        for (int i = 0; i < formals.size(); i++) {
            formals.get(i).accept(this);
        }

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
    public Type visit(Parens parens) {
        return parens.getExp().accept(this);
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
        assertTypes(syso.getPrintee(), Arrays.asList(new IntType(), new BooleanType()));
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
        assertTypeExistence(varDecl.getType(), varDecl.getLine(), varDecl.getColumn());
        return null;
    }

    @Override
    public Type visit(While w) {
        assertType(w.getCondition(), new BooleanType());
        w.getStatement().accept(this);
        return null;
    }
}
