package org.adtado.intrager.effective.chapter02.item01;

public class Item01FashionConstructor {
    private final String type;
    private final String size;
    private final String brand;

    public String getType() {
        return type;
    }


    public String getSize() {
        return size;
    }
    public String getBrand() {
        return brand;
    }


    public Item01FashionConstructor(String type, String size, String brand) {
        this.type = type;
        this.size = size;
        this.brand = brand;
    }
}
