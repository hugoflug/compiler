package mjc;

import se.kth.hugosa.compiler.Main;
import se.kth.hugosa.compiler.ast.Program;
import se.kth.hugosa.compiler.codegen.CodeGenerator;
import se.kth.hugosa.compiler.parser.MiniJavaParser;
import se.kth.hugosa.compiler.symboltable.ClassTable;
import se.kth.hugosa.compiler.symboltable.SymbolTableCreator;
import se.kth.hugosa.compiler.typechecking.TypeChecker;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class JVMMain {
    public static void main(String[] args) throws IOException {
        Main.main(args);
    }
}
