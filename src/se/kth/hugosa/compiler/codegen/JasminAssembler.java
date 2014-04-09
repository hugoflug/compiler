package se.kth.hugosa.compiler.codegen;

import se.kth.hugosa.compiler.ast.*;
import se.kth.hugosa.compiler.symboltable.MethodTable;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class JasminAssembler {
    private OutputStreamWriter writer;

    public JasminAssembler(OutputStream stream) throws IOException {
        writer = new OutputStreamWriter(stream);
    }

    public void append(String instruction) {

        try {
            writer.append(instruction + "\n");
            writer.flush();
        } catch (IOException e) {
            //temp
            throw new RuntimeException(e);
        }
    }

    public void newFile() {

    }

    public static String toTypeDescriptor(Type type) {
        if (type instanceof ObjectType) {
            String typeName = ((ObjectType)type).getName();
            return "L" + typeName + ";";
        } else if (type instanceof IntType) {
            return "I";
        } else if (type instanceof IntArrayType) {
            return "[I";
        } else if (type instanceof BooleanType) {
            return "I";
        }
        return null;
    }

    public static String toMethodDescriptor(String className, String methodName, FormalList formalList, Type returnType) {
        ArrayList<Type> types = new ArrayList<Type>();
        for (int i = 0; i < formalList.size(); i++) {
            Formal formal = formalList.get(i);
            types.add(formal.getType());
        }
        return toMethodDescriptor(className, methodName, types, returnType);
    }

    public static String toMethodDescriptor(String className, String methodName, List<Type> typeList, Type returnType) {
        StringBuilder sb = new StringBuilder();
        sb.append(className + "/" + methodName + "(");
        for (Type type : typeList) {
            sb.append(toTypeDescriptor(type));
        }
        sb.append(")" + returnType);
        return sb.toString();
    }
}