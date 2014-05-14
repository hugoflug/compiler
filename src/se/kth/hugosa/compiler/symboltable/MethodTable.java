package se.kth.hugosa.compiler.symboltable;

import se.kth.hugosa.compiler.ast.Type;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class MethodTable {
    private Map<String, Type> params;
    private Map<String, Type> locals;
    private Type type;
    private String name;

    public MethodTable(String name, Type type) {
        this.type = type;
        this.name = name;
        params = new LinkedHashMap<String, Type>();
        locals = new HashMap<String, Type>();
    }

    public String getName() {
        return name;
    }

    public Type getParamType(String name) {
        return params.get(name);
    }

    public Type getLocalType(String name) {
        return locals.get(name);
    }

    public Set<Map.Entry<String, Type>> getParams() {
        return params.entrySet();
    }

    public int getAmountOfVars() {
        return locals.size() + params.size();
    }

    public boolean hasParam(String name) {
        return params.containsKey(name);
    }

    public boolean hasLocal(String name) {
        return locals.containsKey(name);
    }

    public void addParam(String name, Type type) {
        params.put(name, type);
    }

    public void setLocal(String name, Type type) {
        locals.put(name, type);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MethodTable{\n");
        sb.append("params={\n");
        for (Map.Entry<String, Type> entry : params.entrySet()) {
            sb.append(entry.getKey() + " = " + entry.getValue() + "\n");
        }
        sb.append("}\n");
        sb.append("fields={\n");
        for (Map.Entry<String, Type> entry : locals.entrySet()) {
            sb.append(entry.getKey() + " = " + entry.getValue() + "\n");
        }
        sb.append("}\n");
        sb.append("}\n");
        return sb.toString();
    }

    public Type getType() {
        return type;
    }
}

