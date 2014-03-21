package mjc;

import se.kth.hugosa.compiler.parser.MiniJavaParser;

import java.io.FileInputStream;
import java.io.InputStream;

public class JVMMain {
    public static void main(String[] args) {
        try {
            MiniJavaParser parser = new MiniJavaParser(new FileInputStream(args[0]));
            parser.parse();
        } catch (Exception e) {
            System.exit(1);
        }
        System.exit(0);
    }
}
