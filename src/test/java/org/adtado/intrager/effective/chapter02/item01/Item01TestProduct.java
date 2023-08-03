package org.adtado.intrager.effective.chapter02.item01;

public class Item01TestProduct {
    private static final Item01TestProduct instance = new Item01TestProduct();
    private final Item01FashionConstructor TEST_PRODUCT;

    private Item01TestProduct() {
        TEST_PRODUCT = new Item01FashionConstructor(
                "Shirt",
                "TOP_XL",
                "MUSINSA_STANDARD"
        );
    }

    public static Item01TestProduct getInstance() {
        return instance;
    }

    public Item01FashionConstructor getTestProduct() {
        return TEST_PRODUCT;
    }
}
