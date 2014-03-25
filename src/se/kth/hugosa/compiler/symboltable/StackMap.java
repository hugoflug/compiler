package se.kth.hugosa.compiler.symboltable;

import java.util.*;

public class StackMap<K, V> {
    private HashMap<K, Deque<V>> hashMap;

    public StackMap() {
        hashMap = new HashMap<K, Deque<V>>();
    }

    public void insert(K key, V value) {
        Deque<V> stack = hashMap.get(key);
        if (stack == null) {
            stack = new ArrayDeque<V>();
            stack.push(value);
            hashMap.put(key, stack);
        } else {
            stack.push(value);
        }
    }

    public V get(K key) {
        Deque<V> stack = hashMap.get(key);
        if (stack == null) {
            throw new NoSuchElementException();
        } else {
            return stack.getFirst();
        }
    }

    public void pop(K key) throws NoSuchElementException {
        Deque<V> stack = hashMap.get(key);
        if (stack != null) {
            stack.pop();
        } else {
            throw new NoSuchElementException();
        }
    }

    public Set<K> getKeys() {
        return hashMap.keySet();
    }
}
