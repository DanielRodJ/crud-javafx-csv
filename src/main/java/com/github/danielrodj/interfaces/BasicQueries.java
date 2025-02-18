package com.github.danielrodj.interfaces;

import java.util.Collections;
import java.util.Map;

public abstract interface BasicQueries<T> {

    T get(int id);

    Map<Integer, T> getAll();

    int insert(T entity);

    int update(T entity);

    int delete(T entity);
    
    default boolean exist(Integer id){
        return get(id) != null;
    }

    default int getLastId() {
        if (getAll().isEmpty()) {
            return 0;
        } else {
            return Collections.max(getAll().keySet());
        }
    }

    default int getNewestId() {
        return getLastId() + 1;
    }
}
