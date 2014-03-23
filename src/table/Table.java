package table;

import java.util.*;

public class Table<E> {
    private StackMap<String, E> stackMap;
    private Deque<Deque<String>> scopes;

    public Table() {
        stackMap = new StackMap<String, E>();
    }

    public boolean put(String key, E value) {
        scopes.getFirst().push(key);
        stackMap.insert(key, value);
        return true;
    }

    public E get(String key) {
        return stackMap.get(key);
    }

    public void beginScope() {
        scopes.push(new ArrayDeque<String>());
    }

    public void endScope() {
        Deque<String> endedScope = scopes.pop();
        for (String s : endedScope) {
            stackMap.pop(s);
        }
    }

    public Set<String> getKeys() {
        return stackMap.getKeys();
    }
}
