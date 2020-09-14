package com.cache.demo.service.storage.impl;
import com.cache.demo.service.storage.Cache;

import java.util.*;


public class LFU<K, V> implements Cache<K, V> {

    private final int MAX_SIZE;
    private HashMap<K, V> storage;
    private HashMap<K, Long> keyFrequency;
    private TreeMap<Long, HashSet<K>> sortedFrequencies;

    public LFU(int maxSize) {
        MAX_SIZE = maxSize;
        storage = new HashMap<>();
        keyFrequency = new HashMap<>();
        sortedFrequencies = new TreeMap<>();
    }

    private void eviction() {
        Long minFrequency = sortedFrequencies.firstKey();
        K evictionKey = sortedFrequencies.get(minFrequency).iterator().next();
        delFromSortedFrequencies(evictionKey, minFrequency);
        keyFrequency.remove(evictionKey);
        storage.remove(evictionKey);
    }

    private void frequencyIncrement(K key) {
        Long frequency = keyFrequency.compute(key, (k, f) -> f + 1L);
        delFromSortedFrequencies(key, frequency - 1);
        sortedFrequencies.computeIfAbsent(frequency, keys -> new HashSet<>()).add(key);
    }

    private void delFromSortedFrequencies(K key, Long frequency) {
        if (sortedFrequencies.get(frequency).size() > 1) {
            sortedFrequencies.get(frequency).remove(key);
        } else {
            sortedFrequencies.remove(frequency);
        }
    }

    @Override
    public V put(K key, V value) {
        boolean exist = storage.containsKey(key);
        if (storage.size() == MAX_SIZE && !exist) {
            eviction();
        }
        V oldValue = storage.put(key, value);
        Long frequency = keyFrequency.computeIfAbsent(key, f -> 1L);
        sortedFrequencies.computeIfAbsent(frequency, keys -> new HashSet<>()).add(key);
        return oldValue;
    }

    @Override
    public V get(K key) {
        V value = storage.get(key);
        if (value != null) {
            frequencyIncrement(key);
        }
        return value;
    }



    public List<K> getAll() {

        List<K> valuesToMatch  = new ArrayList<K>((Collection<? extends K>) storage.values());
        return valuesToMatch;
    }

    @Override
    public boolean existkey(K key) {
        return storage.containsKey(key);
    }

}
