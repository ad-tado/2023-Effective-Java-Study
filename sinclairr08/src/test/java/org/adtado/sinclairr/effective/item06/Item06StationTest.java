package org.adtado.sinclairr.effective.item06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Item06StationTest {
    Random random = new Random();
    List<String> visitStationNames = List.of(
            "도라산",
            "임진강",
            "운천",
            "문산",
            "파주",
            "월롱",
            "금촌",
            "금릉",
            "운정",
            "야당"
    );

    String getRandomName() {
        return visitStationNames.get(random.nextInt(10));
    }

    @DisplayName("불필요한 객체 생성")
    @Test
    void create_useless_object() {
        Instant start = Instant.now();
        for (int i = 0; i < 10; i++) {
            Item06Station item06Station = new Item06Station(getRandomName());
            item06Station.visit();
        }
        Instant end = Instant.now();

        System.out.println(Duration.between(start, end).toMillis());
    }

    @DisplayName("객체를 캐싱해서 재활용")
    @Test
    void reuse_object() {
        Instant start = Instant.now();
        Item06StationCache item06StationCache = new Item06StationCache(visitStationNames);
        for (int i = 0; i < 10; i++) {
            Item06Station item06Station =  item06StationCache.from(getRandomName());
            item06Station.visit();
        }
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
    }
}
