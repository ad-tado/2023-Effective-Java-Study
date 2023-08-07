package org.adtado.intrager.effective.chapter02.item01;

import java.time.LocalDate;

public class Item01ProductResponse {
    private final String type;
    private final ClothesSize size;
    private final FashionBrand brand;
    private final LocalDate createdDate;


    public String getType() {
        return type;
    }
    public ClothesSize getSize() {
        return size;
    }
    public FashionBrand getBrand() {
        return brand;
    }

    public Item01ProductResponse(String type, ClothesSize size, FashionBrand brand) {
        this.type = type;
        this.size = size;
        this.brand = brand;
    }

    public static Item01ProductResponse from(Item01FashionConstructor inventory) {
        return new Item01ProductResponse(
                inventory.getType(),
                ClothesSize.valueOf(inventory.getSize()),
                FashionBrand.valueOf(inventory.getBrand())
        );
    }
}
