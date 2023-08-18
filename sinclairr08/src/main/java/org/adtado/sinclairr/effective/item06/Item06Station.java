package org.adtado.sinclairr.effective.item06;

public class Item06Station {
    private final String name;

    public Item06Station(String name) {
        this.name = name;

        // Very heavy logic
        Item06StationRepository.register(name);
    }

    public String getName() {
        return name;
    }

    public String visit() {
        return this.name + "방문" + "\n";
    }
}
