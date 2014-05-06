package se.kth.hugosa.compiler.typechecking;

import org.junit.Assert;
import org.junit.Test;
import se.kth.hugosa.compiler.CompilationException;
import se.kth.hugosa.compiler.ast.Program;
import se.kth.hugosa.compiler.parser.MiniJavaParser;
import se.kth.hugosa.compiler.symboltable.ClassTable;
import se.kth.hugosa.compiler.symboltable.SymbolTableCreator;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

public class TypeCheckerTest {
    @Test
    public void testTypeCheck() throws Exception {
        File dir = new File("tests/minijava/typecheck/positive");
        for (File file : dir.listFiles()) {
            System.out.println(file);
            MiniJavaParser parser = new MiniJavaParser(new FileInputStream(file));
            Program program = parser.parse();
            SymbolTableCreator creator = new SymbolTableCreator();
            Map<String, ClassTable> classes = null;
            classes = creator.createSymbolTable(program);

            TypeChecker typeChecker = new TypeChecker(classes);
            typeChecker.typeCheck(program);
        }
    }

    @Test
    public void testTypeCheckNegative() throws Exception {
        File dir = new File("tests/minijava/typecheck/negative");
        for (File file : dir.listFiles()) {
            System.out.println(file);
            MiniJavaParser parser = new MiniJavaParser(new FileInputStream(file));
            Program program = parser.parse();
            SymbolTableCreator creator = new SymbolTableCreator();
            Map<String, ClassTable> classes = null;
            classes = creator.createSymbolTable(program);

            try {
                TypeChecker typeChecker = new TypeChecker(classes);
                typeChecker.typeCheck(program);
                Assert.fail("Should have thrown CompilationException");
            } catch (CompilationException e) {

            }
        }
    }
}
