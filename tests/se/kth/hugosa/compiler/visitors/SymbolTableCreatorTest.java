package se.kth.hugosa.compiler.visitors;

import org.junit.Assert;
import org.junit.Test;
import se.kth.hugosa.compiler.CompilationException;
import se.kth.hugosa.compiler.Errors;
import se.kth.hugosa.compiler.Indenter;
import se.kth.hugosa.compiler.ast.Program;
import se.kth.hugosa.compiler.parser.MiniJavaParser;
import se.kth.hugosa.compiler.symboltable.ClassTable;
import se.kth.hugosa.compiler.symboltable.SymbolTableCreator;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

public class SymbolTableCreatorTest {

    @Test
    public void testCreateSymbolTable() throws Exception {
        File dir = new File("tests/minijava/symboltable/positive");
        for (File file : dir.listFiles()) {
            MiniJavaParser parser = new MiniJavaParser(new FileInputStream(file));
            Program program = parser.parse();
            SymbolTableCreator creator = new SymbolTableCreator();
            Map<String, ClassTable> classes = null;
            try {
                classes = creator.createSymbolTable(program);
            } catch (CompilationException e) {
                for (Errors.MiniJavaError error : e.getErrors().getErrors()) {
                    throw new Exception();
                }
            }
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, ClassTable> entry : classes.entrySet()) {
                sb.append(entry.getKey() + " = " + entry.getValue() + "\n");
            }
          //System.out.println(Indenter.indent(sb.toString()));
        }
    }

    @Test
    public void testNegativeCreateSymbolTable() throws Exception {
        File dir = new File("tests/minijava/symboltable/negative");
        for (File file : dir.listFiles()) {
            MiniJavaParser parser = new MiniJavaParser(new FileInputStream(file));
            Program program = parser.parse();
            SymbolTableCreator creator = new SymbolTableCreator();
            try {
                creator.createSymbolTable(program);
                Assert.fail("Should have thrown CompilationException");
            } catch (CompilationException e) {}
        }
    }
}
