package se.kth.hugosa.compiler.codegen;

import se.kth.hugosa.compiler.ast.*;

public class StackDepthCalculator implements Visitor {
    private int stackDepth;
    private int maxStackDepth;

    public StackDepthCalculator() {
        maxStackDepth = stackDepth = 0;
    }

    public int calcMaxStackDepth(MethodDecl methodDecl) {
        stackDepth = maxStackDepth = 0;
        methodDecl.accept(this);
        return maxStackDepth;
    }

    public int calcMaxStackDepth(StmtList statements) {
        stackDepth = maxStackDepth = 0;
        statements.acceptAll(this);
        return maxStackDepth;
    }

    public void changeStackDepth(int delta) {
        stackDepth += delta;
        if (stackDepth > maxStackDepth) {
            maxStackDepth = stackDepth;
        }
    }

    @Override
    public void visit(And and) {
        and.getLeftOp().accept(this);
        changeStackDepth(+1);
        changeStackDepth(-2);
        and.getRightOp().accept(this);
    }

    @Override
    public void visit(ArrayAssign arrayAssign) {
        arrayAssign.getId().accept(this);
        arrayAssign.getIndex().accept(this);
        arrayAssign.getNewValue().accept(this);
        changeStackDepth(-3);
    }

    @Override
    public void visit(ArrayLength arrayLength) {
        arrayLength.getArray().accept(this);
    }

    @Override
    public void visit(ArrayLookup arrayLookup) {
        arrayLookup.getArray().accept(this);
        arrayLookup.getIndex().accept(this);
        changeStackDepth(-1);
    }

    @Override
    public void visit(Assign assign) {
        assign.getNewValue().accept(this);
        changeStackDepth(+1);
        changeStackDepth(-2);
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

    }

    @Override
    public void visit(Equal equal) {
        equal.getLeftOp().accept(this);
        equal.getRightOp().accept(this);
        changeStackDepth(-2);
        changeStackDepth(+1);
    }

    @Override
    public void visit(False f) {
        changeStackDepth(+1);
    }

    @Override
    public void visit(Formal formal) {

    }

    @Override
    public void visit(Identifier id) {
        changeStackDepth(+1);
    }

    @Override
    public void visit(If i) {
        i.getCondition().accept(this);
        changeStackDepth(-1);
        i.getThenStmt().accept(this);
        i.getElseStmt().accept(this);
    }

    @Override
    public void visit(IfWithoutElse ifWithoutElse) {
        ifWithoutElse.getCondition().accept(this);
        changeStackDepth(-1);
        ifWithoutElse.getThenStmt().accept(this);
    }

    @Override
    public void visit(IntArrayType intArrayType) {

    }

    @Override
    public void visit(IntLit intLit) {
        changeStackDepth(+1);
    }

    @Override
    public void visit(IntType intType) {

    }

    @Override
    public void visit(LessThan lessThan) {
        lessThan.getLeftOp().accept(this);
        lessThan.getRightOp().accept(this);
        changeStackDepth(-2);
        changeStackDepth(+1);
    }

    @Override
    public void visit(LessOrEqualThan lessThan) {
        lessThan.getLeftOp().accept(this);
        lessThan.getRightOp().accept(this);
        changeStackDepth(-2);
        changeStackDepth(+1);
    }

    @Override
    public void visit(MainClass main) {

    }

    @Override
    public void visit(MethodCall call) {
        call.getObject().accept(this);
        call.getArgumentList().acceptAll(this);
        changeStackDepth(-1 - call.getArgumentList().size());
        changeStackDepth(+1);
    }

    @Override
    public void visit(MethodDecl decl) {
        decl.getStatements().acceptAll(this);
        decl.getReturnValue().accept(this);
        changeStackDepth(-1);
    }

    @Override
    public void visit(Minus minus) {
        minus.getLeftOp().accept(this);
        minus.getRightOp().accept(this);
        changeStackDepth(-1);
    }

    @Override
    public void visit(MoreThan moreThan) {
        moreThan.getLeftOp().accept(this);
        moreThan.getRightOp().accept(this);
        changeStackDepth(-2);
        changeStackDepth(+1);
    }

    @Override
    public void visit(MoreOrEqualThan moreOrEqualThan) {
        moreOrEqualThan.getLeftOp().accept(this);
        moreOrEqualThan.getRightOp().accept(this);
        changeStackDepth(-2);
        changeStackDepth(+1);
    }

    @Override
    public void visit(Mult mult) {
        mult.getLeftOp().accept(this);
        mult.getRightOp().accept(this);
        changeStackDepth(-1);
    }

    @Override
    public void visit(NewObject object) {
        changeStackDepth(+2);
        changeStackDepth(-1);
    }

    @Override
    public void visit(NewArray array) {
        array.getArraySize().accept(this);
    }

    @Override
    public void visit(Not not) {
        not.getExpression().accept(this);
        changeStackDepth(+1);
        changeStackDepth(-1);
    }

    @Override
    public void visit(NotEqual notEqual) {
        notEqual.getLeftOp().accept(this);
        notEqual.getRightOp().accept(this);
        changeStackDepth(-2);
        changeStackDepth(+1);
    }

    @Override
    public void visit(ObjectType objectType) {

    }

    @Override
    public void visit(Or or) {
        or.getLeftOp().accept(this);
        changeStackDepth(+1);
        changeStackDepth(-2);
        or.getRightOp().accept(this);
    }

    @Override
    public void visit(Parens parens) {
        parens.getExp().accept(this);
    }

    @Override
    public void visit(Plus plus) {
        plus.getLeftOp().accept(this);
        plus.getRightOp().accept(this);
        changeStackDepth(-1);
    }

    @Override
    public void visit(Program program) {

    }

    @Override
    public void visit(Syso syso) {
        changeStackDepth(+1);
        syso.getPrintee().accept(this);
        changeStackDepth(-2);
    }

    @Override
    public void visit(This t) {
        changeStackDepth(+1);
    }

    @Override
    public void visit(True tru) {
        changeStackDepth(+1);
    }

    @Override
    public void visit(Type type) {

    }

    @Override
    public void visit(VarDecl varDecl) {

    }

    @Override
    public void visit(While w) {
        w.getCondition().accept(this);
        changeStackDepth(-1);
        w.getStatement().accept(this);
    }
}
