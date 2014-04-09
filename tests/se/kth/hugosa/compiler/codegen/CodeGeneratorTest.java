package se.kth.hugosa.compiler.codegen;

import org.junit.Test;
import se.kth.hugosa.compiler.ast.Program;
import se.kth.hugosa.compiler.parser.MiniJavaParser;
import se.kth.hugosa.compiler.symboltable.ClassTable;
import se.kth.hugosa.compiler.symboltable.SymbolTableCreator;
import se.kth.hugosa.compiler.typechecking.TypeChecker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;

public class CodeGeneratorTest {
    @Test
    public void testGenerateCode() throws Exception {
        File dir = new File("tests/minijava/codegen");
        for (File file : dir.listFiles()) {
            if (!file.isDirectory()) {
                System.out.println(file);
                MiniJavaParser parser = new MiniJavaParser(new FileInputStream(file));
                Program program = parser.parse();
                SymbolTableCreator creator = new SymbolTableCreator();
                Map<String, ClassTable> classes = null;
                classes = creator.createSymbolTable(program);

                TypeChecker typeChecker = new TypeChecker(classes);
                typeChecker.typeCheck(program);

                FileOutputStream outStream = new FileOutputStream("tests/minijava/codegen/out/" + file.getName());
                CodeGenerator gen = new CodeGenerator(file.getName(), program, classes, outStream);
                gen.generateCode();
            }
        }
    }
}
