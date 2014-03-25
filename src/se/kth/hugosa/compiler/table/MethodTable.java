package se.kth.hugosa.compiler.table;

import se.kth.hugosa.compiler.ast.Type;

import java.util.HashMap;
import java.util.Map;

public class MethodTable {
    private Map<String, Type> params;
    private Map<String, Type> locals;

    public MethodTable() {
        params = new HashMap<String, Type>();
        locals = new HashMap<String, Type>();
    }

    public Type getParamType(String name) {
        return params.get(name);
    }

    public Type getLocalType(String name) {
        return locals.get(name);
    }
}
