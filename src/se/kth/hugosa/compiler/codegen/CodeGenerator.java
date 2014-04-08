package se.kth.hugosa.compiler.codegen;

import se.kth.hugosa.compiler.ast.*;

import java.io.IOException;
import java.io.OutputStream;

public class CodeGenerator implements Visitor {
    private JasminAssembler assembler;
    private String sourceFile;

    public CodeGenerator(String sourceFile, OutputStream outStream) throws IOException {
        this.assembler = new JasminAssembler(outStream);
        this.sourceFile = sourceFile;
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

    }

    @Override
    public void visit(Block block) {

    }

    @Override
    public void visit(BooleanType booleanType) {

    }

    @Override
    public void visit(ClassDecl classDecl) {
        assembler.append(".source " + sourceFile);
        assembler.append(".class public " + classDecl.getClassName());
        assembler.append(".super java/lang/Object");
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
    public void visit(Parens parens) {

    }

    @Override
    public void visit(Plus plus) {

    }

    @Override
    public void visit(Program program) {

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

    }

    @Override
    public void visit(While w) {

    }
}
