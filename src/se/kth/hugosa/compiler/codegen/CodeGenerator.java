package se.kth.hugosa.compiler.codegen;

import se.kth.hugosa.compiler.ast.*;
import se.kth.hugosa.compiler.symboltable.ClassTable;
import se.kth.hugosa.compiler.symboltable.MethodTable;
import se.kth.hugosa.compiler.typechecking.TypeChecker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CodeGenerator implements Visitor {
    private JasminAssembler assembler;
    private String sourceFile;
    private Map<String, Integer> localVars;
    private LabelGenerator labelGen;
    private Map<String, ClassTable> symbolTable;
    private ClassTable currentClass;
    private MethodTable currentMethod;
    private TypeChecker typeChecker;
    private Program program;
    private StackDepthCalculator stackDepthCalc;

    public CodeGenerator(String sourceFile, Program program, Map<String, ClassTable> symbolTable, String outDirectory) throws IOException {
        this.sourceFile = sourceFile;
        assembler = new JasminAssembler(outDirectory);
        localVars = new HashMap<String, Integer>();
        this.symbolTable = symbolTable;
        labelGen = new LabelGenerator();
        typeChecker = new TypeChecker(symbolTable);
        stackDepthCalc = new StackDepthCalculator();
        this.program = program;
    }

    public void generateCode() {
        program.accept(this);
    }

    private void setCurrentClass(ClassTable currentClass) {
        this.currentClass = currentClass;
        typeChecker.setCurrentClass(currentClass);
    }

    private void endCurrentClass() {
        setCurrentClass(null);
    }

    private void setCurrentMethod(MethodTable currentMethod) {
        this.currentMethod = currentMethod;
        typeChecker.setCurrentMethod(currentMethod);
    }

    private void endCurrentMethod() {
        localVars.clear();
        setCurrentMethod(null);
    }

    private String escape(String s) {
        return "'" + s + "'";
    }

    /*
        ;eval left operator
        ifeq end
        ;eval right operator
        end:
     */
    @Override
    public void visit(And and) {
        and.getLeftOp().accept(this);
        String end = labelGen.getLabel();
        assembler.append("dup");
        assembler.append("ifeq " + end);
        assembler.append("pop");
        and.getRightOp().accept(this);
        assembler.append(end + ":");
    }

    @Override
    public void visit(ArrayAssign arrayAssign) {
        arrayAssign.getId().accept(this);
        arrayAssign.getIndex().accept(this);
        arrayAssign.getNewValue().accept(this);
        assembler.append("iastore");
    }

    @Override
    public void visit(ArrayLength arrayLength) {
        arrayLength.getArray().accept(this);
        assembler.append("arraylength");
    }

    @Override
    public void visit(ArrayLookup arrayLookup) {
        arrayLookup.getArray().accept(this);
        arrayLookup.getIndex().accept(this);
        assembler.append("iaload");
    }

    @Override
    public void visit(Assign assign) {
        assign.getNewValue().accept(this);

        String assigneeName = assign.getAssignee().getName();

        Type type = currentMethod.getLocalType(assigneeName);
        if (type == null) {
            type = currentMethod.getParamType(assigneeName);
        }

        if (type != null) {
            int assigneeNo = localVars.get(assigneeName);
            if (type instanceof IntType || type instanceof BooleanType) {
                assembler.append("istore " + assigneeNo);
            } else {
                assembler.append("astore " + assigneeNo);
            }
            return;
        } else {
            type = currentClass.getType(assigneeName);
            String fieldDescriptor = currentClass.getName() + "/" + assigneeName;
            String typeDescriptor = JasminAssembler.toTypeDescriptor(type);
            assembler.append("aload_0");
            assembler.append("swap");
            assembler.append("putfield " + escape(fieldDescriptor) + " " + escape(typeDescriptor));
        }
    }

    @Override
    public void visit(Block block) {
        StmtList stmts = block.getStmtList();
        for (int i = 0; i < stmts.size(); i++) {
            stmts.get(i).accept(this);
        }
    }

    @Override
    public void visit(BooleanType booleanType) {

    }

    @Override
    public void visit(ClassDecl classDecl) {
        setCurrentClass(symbolTable.get(classDecl.getClassName().getName()));

        assembler.newFile(currentClass.getName() + ".s");
        assembler.append(".source " + escape(sourceFile));
        assembler.append(".class public " + escape(classDecl.getClassName().getName()));
        assembler.append(".super java/lang/Object");

        VarDeclList varDecls = classDecl.getVarDeclarations();
        for (int i = 0; i < varDecls.size(); i++) {
            VarDecl decl = varDecls.get(i);
            String name = decl.getId().getName();
            String jasminType = JasminAssembler.toTypeDescriptor(decl.getType());
            assembler.append(".field public " + escape(name) + " " + escape(jasminType));
        }

        assembler.append(".method public <init>()V");
        assembler.append(".limit stack 1");
        assembler.append(".limit locals 1");
        assembler.append("aload_0");
        assembler.append("invokespecial java/lang/Object/<init>()V");
        assembler.append("return");
        assembler.append(".end method");

        MethodDeclList methodDecls = classDecl.getMethodDeclarations();
        for (int i = 0; i < methodDecls.size(); i++) {
            methodDecls.get(i).accept(this);
        }

        endCurrentClass();
    }

    /*
        <COMPARE> setTrue
        iconst_0
        goto after
        setTrue: iconst_1
        after:
    */
    private void compare(String comparison) {
        String setTrue = labelGen.getLabel();
        String after = labelGen.getLabel();
        assembler.append(comparison + " " + setTrue);
        assembler.append("iconst_0 ");
        assembler.append("goto " + after);
        assembler.append(setTrue + ":");
        assembler.append("iconst_1");
        assembler.append(after + ":");
    }

    @Override
    public void visit(Equal equal) {
        equal.getLeftOp().accept(this);
        equal.getRightOp().accept(this);

        Type operandType = equal.getRightOp().accept(typeChecker);
        if (operandType instanceof ObjectType || operandType instanceof IntArrayType) {
            compare("if_acmpeq");
        } else {
            compare("if_icmpeq");
        }
    }

    @Override
    public void visit(False f) {
        assembler.append("iconst_0");
    }

    @Override
    public void visit(Formal formal) {
        localVars.put(formal.getName().getName(), localVars.size() + 1);
    }

    @Override
    public void visit(Identifier id) {
        String name = id.getName();
        Type type = currentMethod.getLocalType(name);

        if (type == null) {
            type = currentMethod.getParamType(name);
        }

        if (type != null) {
            int varNo = localVars.get(name);
            if (type instanceof IntType || type instanceof BooleanType) {
                assembler.append("iload " + varNo);
            } else {
                assembler.append("aload " + varNo);
            }
            return;
        }

        type = currentClass.getType(name);
        String fieldDescriptor = currentClass.getName() + "/" + name;
        String typeDescriptor = JasminAssembler.toTypeDescriptor(type);
        assembler.append("aload_0");
        assembler.append("getfield " + escape(fieldDescriptor) + " " + escape(typeDescriptor));
    }

    /*
        ifeq label
        ;then statements
        goto after
        label:
        ;else statements
        after:
    */
    @Override
    public void visit(If i) {
        i.getCondition().accept(this);
        String label = labelGen.getLabel();
        String after = labelGen.getLabel();
        assembler.append("ifeq " + label);
        i.getThenStmt().accept(this);
        assembler.append("goto " + after);
        assembler.append(label + ":");
        i.getElseStmt().accept(this);
        assembler.append(after + ":");
    }

    /*
        ifeq label
        ;statements
        label:
    */
    @Override
    public void visit(IfWithoutElse ifWithoutElse) {
        ifWithoutElse.getCondition().accept(this);
        String label = labelGen.getLabel();
        assembler.append("ifeq " + label);
        ifWithoutElse.getThenStmt().accept(this);
        assembler.append(label + ":");
    }

    @Override
    public void visit(IntArrayType intArrayType) {

    }

    @Override
    public void visit(IntLit intLit) {
        assembler.append("ldc " + intLit.getValue());
    }

    @Override
    public void visit(IntType intType) {

    }

    @Override
    public void visit(LessThan lessThan) {
        lessThan.getLeftOp().accept(this);
        lessThan.getRightOp().accept(this);
        compare("if_icmplt");
    }

    @Override
    public void visit(LessOrEqualThan lessOrEqualThan) {
        lessOrEqualThan.getLeftOp().accept(this);
        lessOrEqualThan.getRightOp().accept(this);
        compare("if_icmple");
    }

    @Override
    public void visit(MainClass main) {
        setCurrentClass(symbolTable.get(main.getName().getName()));

        assembler.newFile(currentClass.getName() + ".s");
        assembler.append(".source " + escape(sourceFile));
        assembler.append(".class public " + escape(main.getName().getName()));
        assembler.append(".super java/lang/Object");
        assembler.append(".method public static main([Ljava/lang/String;)V");

        setCurrentMethod(currentClass.getMethod("main"));

        int maxStack = stackDepthCalc.calcMaxStackDepth(main.getStatements()) + 1;
        assembler.append(".limit stack " + maxStack);
        assembler.append(".limit locals " + (currentMethod.getAmountOfVars() + 1));

        main.getVarDeclarations().acceptAll(this);
        main.getStatements().acceptAll(this);

        endCurrentMethod();

        assembler.append("return");
        assembler.append(".end method");

        endCurrentClass();
    }

    @Override
    public void visit(MethodCall call) {
        call.getObject().accept(this);
        call.getArgumentList().acceptAll(this);

        ObjectType type = (ObjectType)(call.getObject().accept(typeChecker));
        String typeName = type.getName();
        String methodName = call.getMethodName().getName();

        Type returnType = symbolTable.get(typeName).getMethod(methodName).getType();
        ArrayList<Type> typeList = new ArrayList<Type>();

        ExpList expList = call.getArgumentList();
        for (int i = 0; i < expList.size(); i++) {
            typeList.add(expList.get(i).accept(typeChecker));
        }

        String methodDesc = JasminAssembler.toMethodDescriptor(typeName, methodName,
                typeList, returnType);

        assembler.append("invokevirtual " + escape(methodDesc));
    }

    @Override
    public void visit(MethodDecl decl) {
        String name = decl.getName().getName();
        setCurrentMethod(currentClass.getMethod(name));

        String methodDescriptor = JasminAssembler.toMethodDescriptor(currentMethod.getName(),
                decl.getArgumentList(), currentMethod.getType());
        assembler.append(".method public " + escape(methodDescriptor));
        int maxStack = stackDepthCalc.calcMaxStackDepth(decl) + 1;
        assembler.append(".limit stack " + maxStack);
        assembler.append(".limit locals " + (currentMethod.getAmountOfVars() + 1));
        decl.getArgumentList().acceptAll(this);
        decl.getVarDeclarations().acceptAll(this);
        decl.getStatements().acceptAll(this);
        decl.getReturnValue().accept(this);
        Type type = decl.getType();
        if (type instanceof IntType || type instanceof BooleanType) {
            assembler.append("ireturn");
        } else {
            assembler.append("areturn");
        }

        assembler.append(".end method");
        endCurrentMethod();
    }

    @Override
    public void visit(Minus minus) {
        minus.getLeftOp().accept(this);
        minus.getRightOp().accept(this);
        assembler.append("isub");
    }

    @Override
    public void visit(MoreThan moreThan) {
        moreThan.getLeftOp().accept(this);
        moreThan.getRightOp().accept(this);
        compare("if_icmpgt");
    }

    @Override
    public void visit(MoreOrEqualThan moreOrEqualThan) {
        moreOrEqualThan.getLeftOp().accept(this);
        moreOrEqualThan.getRightOp().accept(this);
        compare("if_icmpge");
    }

    @Override
    public void visit(Mult mult) {
        mult.getLeftOp().accept(this);
        mult.getRightOp().accept(this);
        assembler.append("imul");
    }

    @Override
    public void visit(NewObject object) {
        String typeName = object.getName().getName();
        assembler.append("new " + escape(typeName));
        assembler.append("dup");
        assembler.append("invokespecial " + escape(typeName + "/<init>()V"));
    }

    @Override
    public void visit(NewArray array) {
        array.getArraySize().accept(this);
        assembler.append("newarray int");
    }

    @Override
    public void visit(Not not) {
        not.getExpression().accept(this);
        assembler.append("iconst_1");
        assembler.append("ixor");
    }

    @Override
    public void visit(NotEqual notEqual) {
        notEqual.getLeftOp().accept(this);
        notEqual.getRightOp().accept(this);
        Type operandType = notEqual.getRightOp().accept(typeChecker);
        if (operandType instanceof ObjectType || operandType instanceof IntArrayType) {
            compare("if_acmpne");
        } else {
            compare("if_icmpne");
        }
    }

    @Override
    public void visit(ObjectType objectType) {

    }

    @Override
    public void visit(Or or) {
        or.getLeftOp().accept(this);
        String end = labelGen.getLabel();
        assembler.append("dup");
        assembler.append("ifne " + end);
        assembler.append("pop");
        or.getRightOp().accept(this);
        assembler.append(end + ":");
    }

    @Override
    public void visit(Parens parens) {
        parens.getExp().accept(this);
    }

    @Override
    public void visit(Plus plus) {
        plus.getLeftOp().accept(this);
        plus.getRightOp().accept(this);
        assembler.append("iadd");
    }

    @Override
    public void visit(Program program) {
        program.getMainClass().accept(this);
        ClassDeclList decls = program.getClassDeclarations();
        for (int i = 0; i < decls.size(); i++) {
            decls.get(i).accept(this);
        }
    }

    /*
    ifeq falze
    lcd "true"
    goto after
    falze:
    ldc "false"
    after:
    */
    @Override
    public void visit(Syso syso) {
        assembler.append("getstatic java/lang/System/out Ljava/io/PrintStream;");
        Exp printee = syso.getPrintee();
        printee.accept(this);

        Type type = printee.accept(typeChecker);
        if (type instanceof IntType) {
            assembler.append("invokevirtual java/io/PrintStream/println(I)V");
        } else {
            String falze = labelGen.getLabel();
            String after = labelGen.getLabel();
            assembler.append("ifeq " + falze);
            assembler.append("ldc \"true\"");
            assembler.append("goto " + after); //or false
            assembler.append(falze + ":");
            assembler.append("ldc \"false\"");
            assembler.append(after + ":");
            assembler.append("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
        }
    }

    @Override
    public void visit(This t) {
        assembler.append("aload_0");
    }

    @Override
    public void visit(True tru) {
        assembler.append("iconst_1");
    }

    @Override
    public void visit(Type type) {

    }

    @Override
    public void visit(VarDecl varDecl) {
        int newNo = localVars.size() + 1;
        Type type = varDecl.getType();
        String name = varDecl.getId().getName();
        localVars.put(name, newNo);
    }

    /*
        start:
        ;condition
        ifeq after
        ;statements
        goto start
        after:
     */
    @Override
    public void visit(While w) {
        String start = labelGen.getLabel();
        String after = labelGen.getLabel();
        assembler.append(start + ":");
        w.getCondition().accept(this);
        assembler.append("ifeq " + after);
        w.getStatement().accept(this);
        assembler.append("goto " + start);
        assembler.append(after + ":");
    }
}
