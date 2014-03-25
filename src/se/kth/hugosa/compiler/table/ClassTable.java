package se.kth.hugosa.compiler.table;

import se.kth.hugosa.compiler.ast.Type;

import java.util.HashMap;
import java.util.Map;

public class ClassTable {
    private Map<String, MethodTable> methods;
    private Map<String, Type> fields;

    public ClassTable() {
        methods = new HashMap<String, MethodTable>();
        fields = new HashMap<String, Type>();
    }

    public MethodTable getMethod(String name) {
        return methods.get(name);
    }

    public Type getType(String fieldName) {
        return fields.get(fieldName);
    }

    public void setMethod(String name, MethodTable method) {
        methods.put(name, method);
    }

    public void setType(String fieldName, Type type) {
        fields.put(fieldName, type);
    }
}
