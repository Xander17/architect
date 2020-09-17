package ru.geekbrains.atmclient.db;

import ru.geekbrains.atmclient.model.Identify;

import java.util.HashMap;
import java.util.Map;

public class IdentifyMap<K, V extends Identify<K>> {
    private Map<K, V> cache;

    public IdentifyMap() {
        cache = new HashMap<>();
    }

    public V get(K id) {
        return cache.get(id);
    }

    public void add(V object) {
        cache.put(object.getId(), object);
    }

    public void delete(V object) {
        cache.remove(object.getId());
    }

    public boolean contains(K id) {
        return cache.containsKey(id);
    }
}
