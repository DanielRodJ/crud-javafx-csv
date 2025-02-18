package com.github.danielrodj.database;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.danielrodj.interfaces.BasicQueries;

public abstract class BasicRepository<T> implements BasicQueries<T> {

    private final FileHandler fileHandler;
    private final CacheManager<T> cacheManager;

    protected BasicRepository(String csvFile, String header) {
        this.fileHandler = new FileHandler(csvFile, header);
        this.cacheManager = new CacheManager<>();
    }

    protected abstract int getId(T entity);

    protected abstract T parse(String[] values);

    protected abstract String format(T entity);

    private void loadCache() {
        if (cacheManager.getCache().isEmpty()) {
            try {
                List<String[]> lines = fileHandler.readAll();
                for (String[] values : lines) {
                    int id = Integer.parseInt(values[0]);
                    cacheManager.addToCache(id, parse(values));
                }
            } catch (IOException e) {
                throw new RuntimeException("Error loading data from CSV.", e);
            }
        }
    }

    @Override
    public T get(int id) {
        loadCache();
        return cacheManager.getCache().get(id);
    }

    @Override
    public Map<Integer, T> getAll() {
        loadCache();
        return cacheManager.getCache();
    }

    @Override
    public int insert(T entity) {
        loadCache();
        cacheManager.addToCache(getId(entity), entity);
        try {
            fileHandler.appendLine(format(entity));
            return 1;
        } catch (IOException e) {
            throw new RuntimeException("Error inserting data into CSV.", e);
        }
    }

    @Override
    public int update(T entity) {
        loadCache();
        if (!cacheManager.containsInCache(getId(entity))) {
            return 0;
        }
        cacheManager.addToCache(getId(entity), entity);
        saveCache();
        return 1;
    }

    @Override
    public int delete(T entity) {
        loadCache();
        if (!cacheManager.containsInCache(getId(entity))) {
            return 0;
        }
        cacheManager.removeFromCache(getId(entity));
        saveCache();
        return 1;
    }

    private void saveCache() {
        try {
            List<String> lines = cacheManager.getCache().values().stream()
                    .map(this::format)
                    .collect(Collectors.toList());
            fileHandler.writeAll(lines);
        } catch (IOException e) {
            throw new RuntimeException("Error saving data to CSV.", e);
        }
    }
}