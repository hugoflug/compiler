package se.kth.hugosa.compiler.codegen;

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
}
