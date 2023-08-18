package org.adtado.sinclairr.effective.item07;

public class Item07Station {
    private static final String HEADER = "아무호선";
    private static final String FOOTER = "역";
    private String name;
    private final Integer id;

    public Item07Station(Integer id) {
        this.id = id;
        this.name = HEADER + id.toString() + FOOTER;
    }
}
