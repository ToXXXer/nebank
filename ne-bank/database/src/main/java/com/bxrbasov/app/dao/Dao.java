package com.bxrbasov.app.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K,V> {
    Optional<V> get(K key);
    Optional<V> update(V value);
    boolean delete(K key);
    Optional<V> save(V value);
    List<V> getAll();
}
