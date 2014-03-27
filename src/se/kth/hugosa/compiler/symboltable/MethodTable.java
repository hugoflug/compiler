package se.kth.hugosa.compiler.symboltable;

import se.kth.hugosa.compiler.ast.ObjectType;
import se.kth.hugosa.compiler.ast.Type;

import java.util.*;

public class MethodTable {

    //params should be a list of (String, Type) tuples
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

    public boolean equals(MethodTable methodTable) {
        if (this.name != methodTable.name) {
            return false;
        }
        Iterator<Map.Entry<String, Type>> paramIt = params.entrySet().iterator();
        Iterator<Map.Entry<String, Type>> otherParamIt = params.entrySet().iterator();

        while (paramIt.hasNext() && otherParamIt.hasNext()) {
            Map.Entry<String, Type> param = paramIt.next();
            Map.Entry<String, Type> otherParam = paramIt.next();
            Type paramType = param.getValue();
            Type otherParamType = otherParam.getValue();
            if (paramType instanceof ObjectType && otherParamType instanceof ObjectType) {
                if (((ObjectType)paramType).getName() != ((ObjectType)otherParamType).getName()) {
                    return false;
                }
            } else if (!paramType.getClass().equals(otherParamType.getClass())) {
                return false;
            }
        }
        if (paramIt.hasNext() || otherParamIt.hasNext()) {
            return false;
        }
        return true;
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

