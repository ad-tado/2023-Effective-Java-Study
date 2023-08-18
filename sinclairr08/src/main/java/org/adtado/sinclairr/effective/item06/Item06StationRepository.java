package org.adtado.sinclairr.effective.item06;

import java.util.ArrayList;
import java.util.List;

public class Item06StationRepository {
    private static List<String> stationNames = init();
    private static List<String> init() {
        List<String> initialStationNames = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            initialStationNames.add(String.valueOf(i));
        }
        return initialStationNames;
    }

    public static void register(String name) {
        for (String stationName : stationNames) {
            if (stationName == name) {
                break;
            }
        }
        stationNames.add(name);
    }
}
