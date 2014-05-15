package se.kth.hugosa.compiler;

import se.kth.hugosa.compiler.ast.Program;
import se.kth.hugosa.compiler.codegen.CodeGenerator;
import se.kth.hugosa.compiler.parser.MiniJavaParser;
import se.kth.hugosa.compiler.symboltable.ClassTable;
import se.kth.hugosa.compiler.symboltable.SymbolTableCreator;
import se.kth.hugosa.compiler.typechecking.TypeChecker;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            MiniJavaParser parser = new MiniJavaParser(new FileInputStream(args[0]));
            Program program = parser.parse();
            SymbolTableCreator creator = new SymbolTableCreator();
            Map<String, ClassTable> classes = creator.createSymbolTable(program);
            TypeChecker typeChecker = new TypeChecker(classes);
            typeChecker.typeCheck(program);
            CodeGenerator gen = new CodeGenerator(args[0], program, classes, ".");
            gen.generateCode();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
        System.exit(0);
    }
}
