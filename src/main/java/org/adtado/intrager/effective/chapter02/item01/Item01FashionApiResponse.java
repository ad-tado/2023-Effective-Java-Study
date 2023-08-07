package org.adtado.intrager.effective.chapter02.item01;

public class Item01FashionApiResponse {
    private final String type;
    private final ClothesSize size;
    private final FashionBrand brand;


    public String getType() {
        return type;
    }
    public ClothesSize getSize() {
        return size;
    }
    public FashionBrand getBrand() {
        return brand;
    }

    public Item01FashionApiResponse(String type, ClothesSize size, FashionBrand brand) {
        this.type = type;
        this.size = size;
        this.brand = brand;
    }

    public static Item01FashionApiResponse stockUp(Item01FashionConstructor inventory) {
        return new Item01FashionApiResponse(
                inventory.getType(),
                ClothesSize.valueOf(inventory.getSize()),
                FashionBrand.valueOf(inventory.getBrand())
        );
    }
}
