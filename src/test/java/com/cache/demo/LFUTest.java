package com.cache.demo;

import com.cache.demo.service.storage.Cache;
import com.cache.demo.service.storage.impl.LFU;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LFUTest {
    private int maxSize;
    private Cache<Integer, Integer> cache;

    @BeforeEach
    void init() {
        maxSize = 4;
        cache = new LFU<>(maxSize);
        for(int i = 0; i < maxSize; i++) {
            cache.put(i, i);
        }
    }

    @Test
    void put() {
        Integer key = 1;
        Integer value = 111;
        Integer oldValue = cache.put(key, value);
        assertEquals(value, cache.get(key));
        assertEquals(oldValue, Integer.valueOf(1));
    }

    @Test
    void get() {
        Integer key = 1;
        assertEquals(Integer.valueOf(key), key);
    }


    @Test
    void lfu() {
        Integer key = maxSize / 2;
        for (int i = 0; i < maxSize; i++) {
            if(i != key) {
                cache.get(i);
                cache.get(i);
            }
        }
        cache.get(key);
        cache.put(maxSize + 1, maxSize + 1);
        assertNull(cache.get(key));
        cache.put(maxSize + 2, maxSize + 2);
        assertNull(cache.get(maxSize + 1));
    }
}