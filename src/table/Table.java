package table;

import java.util.*;

public class Table<E> {
    private StackMap<String, E> stackMap;
    private Deque<Set<String>> scopes;

    public Table() {
        stackMap = new StackMap<String, E>();
        scopes = new ArrayDeque<Set<String>>();
    }

    public boolean put(String key, E value) {
        boolean newValue = scopes.getFirst().add(key);
        if (!newValue) {
            return false;
        }
        stackMap.insert(key, value);
        return true;
    }

    public E get(String key) {
        return stackMap.get(key);
    }

    public void beginScope() {
        scopes.push(new HashSet<String>());
    }

    public void endScope() {
        Set<String> endedScope = scopes.pop();
        for (String s : endedScope) {
            stackMap.pop(s);
        }
    }

    public Set<String> getKeys() {
        return stackMap.getKeys();
    }
}
