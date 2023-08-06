package org.adtado.sinclairr.effective.item06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Item06StationCache {
    List<Item06Station> stations = new ArrayList<>();
    Map<String, Integer> indices = new HashMap<>();

    public Item06StationCache(List<String> names) {
        int i = 0;
        for (String name : names) {
            this.stations.add(new Item06Station(name));
            this.indices.put(name, i++);
        }
    }

    public Item06Station from(String name) {
        return this.stations.get(indices.get(name));
    }
}
