package mjc;

import se.kth.hugosa.compiler.ast.Program;
import se.kth.hugosa.compiler.parser.MiniJavaParser;
import se.kth.hugosa.compiler.symboltable.ClassTable;
import se.kth.hugosa.compiler.symboltable.SymbolTableCreator;
import se.kth.hugosa.compiler.typechecking.TypeChecker;
import sun.awt.Symbol;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class JVMMain {
    public static void main(String[] args) throws IOException {
        try {
            MiniJavaParser parser = new MiniJavaParser(new FileInputStream(args[0]));
            Program program = parser.parse();
            SymbolTableCreator creator = new SymbolTableCreator();
            Map<String, ClassTable> classes = creator.createSymbolTable(program);
            TypeChecker typeChecker = new TypeChecker(classes);
            typeChecker.typeCheck(program);
            if (args[1].equals("-S")) {
                for (String className : classes.keySet()) {
                    File f = new File(className + ".s");
                    f.createNewFile();
                }
            }
        } catch (Exception e) {
            System.exit(1);
        }
        System.exit(0);
    }
}
