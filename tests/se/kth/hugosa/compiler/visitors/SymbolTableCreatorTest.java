package se.kth.hugosa.compiler.visitors;

import org.junit.Test;
import se.kth.hugosa.compiler.Indenter;
import se.kth.hugosa.compiler.ast.Program;
import se.kth.hugosa.compiler.parser.MiniJavaParser;
import se.kth.hugosa.compiler.table.ClassTable;

import java.io.FileInputStream;
import java.util.Map;

public class SymbolTableCreatorTest {

    @Test
    public void testCreateSymbolTable() throws Exception {
        MiniJavaParser parser = new MiniJavaParser(new FileInputStream("tests/minijava/symboltable/symboltable.mj"));
        Program program = parser.parse();
        SymbolTableCreator creator = new SymbolTableCreator();
        creator.visit(program);
        Errors errors = creator.getErrors();
        if (errors.hasErrors()) {
            for (Errors.MiniJavaError error : errors.getErrors()) {
                System.out.println(error);
            }
        } else {
            Map<String, ClassTable> classes = creator.getClasses();
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, ClassTable> entry : classes.entrySet()) {
                sb.append(entry.getKey() + " = " + entry.getValue() + "\n");
            }
            System.out.println(Indenter.indent(sb.toString()));
        }
    }
}
