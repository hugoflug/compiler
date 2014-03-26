package se.kth.hugosa.compiler.symboltable;

import org.junit.Assert;
import org.junit.Test;
import se.kth.hugosa.compiler.CompilationException;
import se.kth.hugosa.compiler.Indenter;
import se.kth.hugosa.compiler.ast.Program;
import se.kth.hugosa.compiler.parser.MiniJavaParser;

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
            Map<String, ClassTable> classes = creator.createSymbolTable(program);
            StringBuilder sb = new StringBuilder();
            System.out.println(file.getName());
            System.out.println("==============");
            for (Map.Entry<String, ClassTable> entry : classes.entrySet()) {
                sb.append(entry.getKey() + " = " + entry.getValue() + "\n");
            }
            System.out.println(Indenter.indent(sb.toString()));
            System.out.println("\n\n");
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
