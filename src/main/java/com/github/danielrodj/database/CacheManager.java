package com.github.danielrodj.database;

import java.util.HashMap;
import java.util.Map;

public class CacheManager<Entity> {

    private final Map<Integer, Entity> cache = new HashMap<>();

    public Map<Integer, Entity> getCache() {
        return cache;
    }

    public void addToCache(int id, Entity entity) {
        cache.put(id, entity);
    }

    public void removeFromCache(int id) {
        cache.remove(id);
    }

    public boolean containsInCache(int id) {
        return cache.containsKey(id);
    }
}