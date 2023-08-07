package org.adtado.katfun.effective.chapter02.item01;

import org.adtado.katfun.effective.chapter02.item01.response.Item01SomethingApiResponse;
import org.adtado.katfun.effective.chapter02.item01.response.Item01UserCategory;

import java.time.LocalDate;

public class Item01SomethingResponse {
    private final String name;
    private final Item01UserCategory category;
    private final LocalDate createdDate;
    private final LocalDate updatedDate;

    public String getName() {
        return name;
    }

    public Item01UserCategory getCategory() {
        return category;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public Item01SomethingResponse(String name, Item01UserCategory category, LocalDate createdDate, LocalDate updatedDate) {
        this.name = name;
        this.category = category;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public static Item01SomethingResponse from(Item01SomethingApiResponse apiResponse) {
        return new Item01SomethingResponse(
                apiResponse.getName() + ", " + apiResponse.getKoreanName(),
                Item01UserCategory.valueOf(apiResponse.getUserCategory()),
                LocalDate.parse(apiResponse.getCreatedAt()),
                LocalDate.parse(apiResponse.getUpdatedAt())
        );
    }
}
