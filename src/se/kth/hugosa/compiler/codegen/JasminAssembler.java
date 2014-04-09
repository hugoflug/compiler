package se.kth.hugosa.compiler.codegen;

import se.kth.hugosa.compiler.ast.*;
import se.kth.hugosa.compiler.symboltable.MethodTable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JasminAssembler {
    private OutputStreamWriter writer;
    private String outDir;

    public JasminAssembler(String directory) throws IOException {
        outDir = directory;
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

    public void newFile(String filename) {
        File outFile = new File(outDir + "/" + filename);
        try {
            writer = new OutputStreamWriter(new FileOutputStream(outFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        throw new IllegalArgumentException();
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
        sb.append(")" + toTypeDescriptor(returnType));
        return sb.toString();
    }
}