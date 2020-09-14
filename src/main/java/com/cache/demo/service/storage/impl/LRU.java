package com.cache.demo.service.storage.impl;
import com.cache.demo.service.storage.Cache;

import java.util.*;


public class LRU<K, V> extends LinkedHashMap<K, V> implements Cache<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private static final boolean ACCESS_ORDER = true;
    private final int MAX_SIZE;

    public LRU(int maxSize) {
        super(maxSize, LOAD_FACTOR, ACCESS_ORDER);
        MAX_SIZE = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest)
    {
        return size() > MAX_SIZE ;
    }


    public List<K> getAll() {
        List<K> valuesToMatch  = new ArrayList<K>((Collection<? extends K>) values());
        return valuesToMatch;
    }

    @Override
    public boolean existkey(K key) {

        return containsKey(key);
    }

}
