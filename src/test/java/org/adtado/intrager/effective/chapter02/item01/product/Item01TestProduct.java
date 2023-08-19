package org.adtado.intrager.effective.chapter02.item01;

import org.adtado.intrager.effective.chapter02.item01.response.Item01ProductApiResponse;

public class Item01TestProduct {
    private static final Item01TestProduct instance = new Item01TestProduct();
    private final Item01ProductApiResponse TEST_PRODUCT;

    private Item01TestProduct() {
        TEST_PRODUCT = new Item01ProductApiResponse(
                "Shirt",
                "TOP_XL",
                "2023-08-07",
                "2023-08-15"
        );
    }

    public static Item01TestProduct getInstance() {
        return instance;
    }

    public Item01ProductApiResponse getTestProduct() {
        return TEST_PRODUCT;
    }
}
