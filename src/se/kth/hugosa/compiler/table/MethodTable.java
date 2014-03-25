package se.kth.hugosa.compiler.table;

import se.kth.hugosa.compiler.ast.Type;

import java.util.HashMap;
import java.util.Map;

public class MethodTable {
    private Map<String, Type> params;
    private Map<String, Type> locals;
    private Type type;

    public MethodTable(Type type) {
        this.type = type;
        params = new HashMap<String, Type>();
        locals = new HashMap<String, Type>();
    }

    public Type getParamType(String name) {
        return params.get(name);
    }

    public Type getLocalType(String name) {
        return locals.get(name);
    }

    public boolean hasParam(String name) {
        return params.containsKey(name);
    }

    public boolean hasLocal(String name) {
        return locals.containsKey(name);
    }

    public void setParam(String name, Type type) {
        params.put(name, type);
    }

    public void setLocal(String name, Type type) {
        locals.put(name, type);
    }
}
