package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> mem = new HashMap<>();

    @Override
    public void add(String id, T model) {
        mem.putIfAbsent(id, model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        if (mem.containsKey(id)) {
            mem.replace(id, model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        if (!mem.containsKey(id)) {
            return false;
        }
        mem.remove(id);
        return true;
    }

    @Override
    public T findById(String id) {
        T result = null;
        if (mem.containsKey(id)) {
            result = mem.get(id);
        }
        return result;
    }
}