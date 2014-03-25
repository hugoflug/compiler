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

    @Override
    public Type visit(And and) {
        return null;
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
        if (!(arrayType instanceof IntArrayType)) {
            errors.addError(new Errors.TypeError(arrayType, new IntArrayType(), id));
        }

        Exp index = arrayAssign.getIndex();
        Type indexType = index.accept(this);
        if (!(indexType instanceof IntType)) {
            errors.addError(new Errors.TypeError(indexType, new IntType(), index.toString()));
        }

        Exp newValue = arrayAssign.getNewValue();
        Type newValueType = newValue.accept(this);

        if (!(newValueType instanceof IntType)) {
            errors.addError(new Errors.TypeError(indexType, new IntType(), newValue.toString()));
        }

        return null;
    }

    @Override
    public Type visit(ArrayLength arrayLength) {
        Exp array = arrayLength.getArray();
        Type arrayType = array.accept(this);

        if (!(arrayType instanceof IntArrayType)) {
            errors.addError(new Errors.TypeError(arrayType, new IntArrayType(), array.toString()));
        }

        return null;
    }

    @Override
    public Type visit(ArrayLookup arrayLookup) {
        Exp array = arrayLookup.getArray();
        Exp index = arrayLookup.getIndex();



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
