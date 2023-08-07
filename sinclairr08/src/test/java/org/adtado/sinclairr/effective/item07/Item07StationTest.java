package org.adtado.sinclairr.effective.item07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class Item07StationTest {
    @DisplayName("메모리 누수가 일어나는 캐시 테스트")
    @Test
    void hashmap_test() throws Exception{
        Map<Item07Station, Integer> cache = new HashMap<>();
        List<Item07Station> item07Stations = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Item07Station item07Station = new Item07Station(i * i * i * 8 + 6731);
            item07Stations.add(item07Station);
            cache.put(item07Station, i);
        }

        for (int i = 1; i < 10; i++) {
            item07Stations.remove(0);
        }

        System.gc();
        Thread.sleep(100);
        assertThat(cache.size()).isEqualTo(10);
    }

    @DisplayName("약한 참조로 메모리 누수를 지우는 캐시 테스트")
    @Test
    void weak_hashmap_test() throws Exception {
        Map<Item07Station, Integer> cache = new WeakHashMap<>();
        List<Item07Station> item07Stations = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Item07Station item07Station = new Item07Station(i * i * i * 8 + 6731);
            item07Stations.add(item07Station);
            cache.put(item07Station, i);
        }

        for (int i = 1; i < 10; i++) {
            item07Stations.remove(0);
        }

        System.gc();
        Thread.sleep(100);
        assertThat(cache.size()).isEqualTo(1);
    }

    // 왜 이 경우에는 gc가 다 쓴 객체에 대한 캐시 항목을 수거하지 않는 지 잘 모르겠습니다...
    @DisplayName("약한 참조로 메모리 누수를 지우는 캐시 테스트, 역으로 수행한 경우에는 실패")
    @Test
    void weak_hashmap_inverse_test() throws Exception {
        Map<Integer, Item07Station> cache = new WeakHashMap<>();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Integer number = Integer.valueOf(i * i * i * 8 + 6731);
            numbers.add(number);
            cache.put(number, new Item07Station(number));
        }

        for (int i = 1; i < 10; i++) {
            numbers.remove(0);
        }

        System.gc();
        Thread.sleep(10000);
        assertThat(cache.size()).isNotEqualTo(1);
    }

}
