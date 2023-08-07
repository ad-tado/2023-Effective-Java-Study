package org.adtado.verticalcode.effective.chap2.item7;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class MyCache<T> {
    private final Map<String, WeakReference<T>> cache;

    public MyCache() {
        cache = new HashMap<>();
    }

    public T get(String key) {
        T value = null;
        WeakReference<T> weakReference = cache.get(key);
        if(weakReference != null) {
            value = weakReference.get();
            if(value == null) {
                cache.remove(key);
            }
        }
        return value;
    }

    public void put(String key, T value) {
        cache.put(key, new WeakReference<>(value));
    }
}
