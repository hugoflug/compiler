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

    public boolean hasMethod(String name) {
        return methods.containsKey(name);
    }

    public boolean hasField(String name) {
        return fields.containsKey(name);
    }

    public MethodTable getMethod(String name) {
        return methods.get(name);
    }

    public Type getType(String fieldName) {
        return fields.get(fieldName);
    }

    public boolean setMethod(String name, MethodTable method) {
        return methods.put(name, method) == null;
    }

    public boolean setType(String fieldName, Type type) {
        return fields.put(fieldName, type) == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ClassTable{\n");
        sb.append("methods={\n");
        for (Map.Entry<String, MethodTable> entry : methods.entrySet()) {
            sb.append(entry.getKey() + " = " + entry.getValue() + "\n");
        }
        sb.append("}\n");
        sb.append("fields={\n");
        for (Map.Entry<String, Type> entry : fields.entrySet()) {
            sb.append(entry.getKey() + " = " + entry.getValue() + "\n");
        }
        sb.append("}\n");
        sb.append("}");
        return sb.toString();
    }
}
