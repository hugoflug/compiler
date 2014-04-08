package se.kth.hugosa.compiler.codegen;

import se.kth.hugosa.compiler.ast.FormalList;
import se.kth.hugosa.compiler.ast.Type;
import se.kth.hugosa.compiler.symboltable.MethodTable;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class JasminAssembler {
    private OutputStreamWriter writer;

    public JasminAssembler(OutputStream stream) throws IOException {
        writer = new OutputStreamWriter(stream);
    }

    public void append(String instruction) {
        try {
            writer.append(instruction + "\n");
        } catch (IOException e) {
            //temp
            throw new RuntimeException(e);
        }
    }

    public void newFile() {

    }

    public static String toTypeDescriptor(Type type) {
        return null;
    }

    public static String toMethodDescriptor(String name, FormalList formals) {
        return null;
    }
}
