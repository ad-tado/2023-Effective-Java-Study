package org.adtado.intrager.effective.chapter02.item07;

import org.adtado.intrager.effective.chapter02.item07.Item07CacheKey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class Item07CacheKeyTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @DisplayName("메모리 누수가 일어나는 캐시 테스트")
    @Test
    void memory_leak_test() throws Exception {
        Map<Item07CacheKey, Integer> cache = new HashMap<>();
        List<Item07CacheKey> cacheKeys = new ArrayList<>();
        for(int i = 1; i < 11; i++) {
            Item07CacheKey key = new Item07CacheKey(i * 2);
            cacheKeys.add(key);
            cache.put(key, i);
        }

        for(int i = 1; i < 10; i++) {
            cacheKeys.remove(0);
        }

        System.gc();
        Thread.sleep(100);
        assertThat(cache.size()).isEqualTo(10);
    }

    @DisplayName("약한 참조로 메모리 누수를 방지하는 테스트")
    @Test
    void prevent_memory_leak_with_weak_hashmap_test() throws Exception {
        Map<Item07CacheKey, Integer> cache = new WeakHashMap<>();
        List<Item07CacheKey> cacheKeys = new ArrayList<>();

        for(int i = 1; i < 11; i++) {
            Item07CacheKey key = new Item07CacheKey(i * 2);
            cacheKeys.add(key);
            cache.put(key, i);
        }

        for(int i = 1; i < 11; i++) {
            cacheKeys.remove(0);
        }

        System.gc();
        Thread.sleep(100);
        assertThat(cache.size()).isEqualTo(0);
    }

    @DisplayName("약한 참조를 역으로 수행했지만 메모리 누수를 방어하지 못하는 테스트")
    @Test
    void weak_hashmap_inverse_fail_test() throws Exception {
        Map<Integer, Item07CacheKey> cache = new WeakHashMap<>();
        List<Integer> numbers = new ArrayList<>();
        for(int i = 1; i < 11; i++) {
            Integer number = i * 2;
            numbers.add(number);
            cache.put(number, new Item07CacheKey(i * 2));
        }

        for(int i = 1; i < 11; i++) {
            numbers.remove(0);
        }

        logger.debug("cache key set : {}", cache.keySet());
        System.gc();
        Thread.sleep(100);
        assertThat(cache.size()).isEqualTo(10);
    }
}