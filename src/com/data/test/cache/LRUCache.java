package com.data.test.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by songyigui on 2017/5/9.
 */
public class LruCache {
    private int size;
    private Map<Object, Object> cache;

    public LruCache(final int size) {
        this.size = size;
        this.cache = new LinkedHashMap<Object, Object>(size, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Object, Object> eldest) {
                return size() > size;
            }
        };
    }

    public Object get(Object key) {
        return cache.get(key);
    }

    public void put(Object key, Object value) {
        cache.put(key, value);
    }
}
