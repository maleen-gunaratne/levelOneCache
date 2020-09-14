package com.cache.demo.service.storage;

import java.util.List;

public interface Cache<K, V> {

    V get(K key);

    V put(K key, V value);


    List<K> getAll();

    boolean existkey(K key);
}
