package org.adtado.katfun.effective.chapter02.item01.stub;

import org.adtado.katfun.effective.chapter02.item01.response.Item01SomethingApiResponse;

public class Item01SomethingStub {
    private static final Item01SomethingStub instance = new Item01SomethingStub();
    private final Item01SomethingApiResponse FIRST_STUB;

    private Item01SomethingStub() {
        FIRST_STUB = new Item01SomethingApiResponse(
                "Kuyho",
                "정규호",
                "KATFUN",
                "2023-07-30",
                "2023-07-31"
        );
    }

    public static Item01SomethingStub getInstance() {
        return instance;
    }

    public Item01SomethingApiResponse getFirstStub() {
        return FIRST_STUB;
    }
}
