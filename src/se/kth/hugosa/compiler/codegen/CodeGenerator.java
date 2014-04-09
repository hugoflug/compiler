package se.kth.hugosa.compiler.codegen;

import se.kth.hugosa.compiler.ast.*;
import se.kth.hugosa.compiler.symboltable.ClassTable;
import se.kth.hugosa.compiler.symboltable.MethodTable;

import java.io.IOException;
import java.io.OutputStream;
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

    public CodeGenerator(String sourceFile, Map<String, ClassTable> symbolTable, OutputStream outStream) throws IOException {
        this.sourceFile = sourceFile;
        assembler = new JasminAssembler(outStream);
        localVars = new HashMap<String, Integer>();
        this.symbolTable = symbolTable;
        labelGen = new LabelGenerator();
    }

    @Override
    public void visit(And and) {
        and.getLeftOp().accept(this);
        and.getRightOp().accept(this);
        assembler.append("iand");
    }

    @Override
    public void visit(ArrayAssign arrayAssign) {
        arrayAssign.getId().accept(this);
        arrayAssign.getIndex().accept(this);
        arrayAssign.getNewValue().accept(this);
        assembler.append("aastore");
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
        assembler.append("aaload");
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
            if (type instanceof IntType) {
                assembler.append("istore " + assigneeNo);
            } else {
                assembler.append("astore " + assigneeNo);
            }
            return;
        } else {
            type = currentClass.getType(assigneeName);
            String fieldDescriptor = currentClass + "/" + assigneeName;
            String typeDescriptor = JasminAssembler.toTypeDescriptor(type);
            assembler.append("putfield " + fieldDescriptor + " " + typeDescriptor);
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
        currentClass = symbolTable.get(classDecl.getClassName().getName());

        assembler.newFile();
        assembler.append(".source " + sourceFile);
        assembler.append(".class public " + classDecl.getClassName());
        assembler.append(".super java/lang/Object");

        VarDeclList varDecls = classDecl.getVarDeclarations();
        for (int i = 0; i < varDecls.size(); i++) {
            VarDecl decl = varDecls.get(i);
            String name = decl.getId().getName();
            String jasminType = JasminAssembler.toTypeDescriptor(decl.getType());
            assembler.append(".field public " + name + " " + jasminType);
        }

        MethodDeclList methodDecls = classDecl.getMethodDeclarations();
        for (int i = 0; i < methodDecls.size(); i++) {
            methodDecls.get(i).accept(this);
        }

        currentClass = null;
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
        assembler.append(comparison + setTrue);
        assembler.append("iconst_0 ");
        assembler.append("goto " + after);
        assembler.append(setTrue + ": iconst_1");
        assembler.append(after + ":");
    }

    @Override
    public void visit(Equal equal) {
        equal.getLeftOp().accept(this);
        equal.getRightOp().accept(this);
        compare("if_icmpeq");
    }

    @Override
    public void visit(False f) {
        assembler.append("iconst_0");
    }

    @Override
    public void visit(Formal formal) {

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
            if (type instanceof IntType) {
                assembler.append("iload " + varNo);
            } else {
                assembler.append("aload " + varNo);
            }
            return;
        }

        type = currentClass.getType(name);
        String fieldDescriptor = currentClass.getName() + "/" + name;
        String typeDescriptor = JasminAssembler.toTypeDescriptor(type);
        assembler.append("getfield " + fieldDescriptor + " " + typeDescriptor);
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
        currentClass = symbolTable.get(main.getName().getName());

        assembler.newFile();
        assembler.append(".source " + sourceFile);
        assembler.append(".class public " + main.getName());
        assembler.append(".super java/lang/Object");
        assembler.append(".method public static main([Ljava/lang/String;)V");

        currentMethod = currentClass.getMethod("main");

        main.getVarDeclarations().acceptAll(this);
        main.getStatements().acceptAll(this);

        currentMethod = null;

        assembler.append("return");
        assembler.append(".end method");

        currentClass = null;
    }

    @Override
    public void visit(MethodCall call) {

    }

    @Override
    public void visit(MethodDecl decl) {
        String name = decl.getName().getName();
        currentMethod = currentClass.getMethod(name);

        String typeDescriptor = "";
        assembler.append(".method public " + name + typeDescriptor);
        assembler.append(".limit stack 100");
        assembler.append(".limit locals 100");
        decl.getVarDeclarations().acceptAll(this);
        decl.getStatements().acceptAll(this);
        decl.getReturnValue().accept(this);
        Type type = decl.getType();
        if (type instanceof IntType) {
            assembler.append("ireturn");
        } else {
            assembler.append("areturn");
        }

        currentMethod = null;
    }

    @Override
    public void visit(Minus minus) {
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
        assembler.append("imul");
    }

    @Override
    public void visit(NewObject object) {
        assembler.append("new " + object.getName());
    }

    @Override
    public void visit(NewArray array) {
        array.getArraySize().accept(this);
        assembler.append("newarray int");
    }

    @Override
    public void visit(Not not) {
        assembler.append("ineg");
    }

    @Override
    public void visit(NotEqual notEqual) {
        notEqual.getLeftOp().accept(this);
        notEqual.getRightOp().accept(this);
        compare("if_icmpne");
    }

    @Override
    public void visit(ObjectType objectType) {

    }

    @Override
    public void visit(Or or) {
        assembler.append("ior");
    }

    @Override
    public void visit(Parens parens) {
        parens.getExp().accept(this);
    }

    @Override
    public void visit(Plus plus) {
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

    @Override
    public void visit(Syso syso) {
        assembler.append("getstatic java/lang/System/out Ljava/io/PrintStream;");
        syso.getPrintee().accept(this);
        assembler.append("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
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
        if (type instanceof IntType) {
            assembler.append("istore " + newNo);
        } else {
            assembler.append("astore " + newNo);
        }
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
        assembler.append(start);
        w.getCondition().accept(this);
        assembler.append("ifeq " + after);
        w.getStatement().accept(this);
        assembler.append("goto " + start);
        assembler.append(after + ":");
    }
}
