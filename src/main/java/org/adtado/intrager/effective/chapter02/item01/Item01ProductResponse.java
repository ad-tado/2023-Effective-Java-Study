package org.adtado.intrager.effective.chapter02.item01;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class Item01ProductResponse {
    private final String type;
    private final ClothesSize size;
    private final LocalDate createdDate;
    private final LocalDate updatedDate;


    public String getType() {
        return type;
    }
    public ClothesSize getSize() {
        return size;
    }
    public LocalDate getCreatedDate() {
        return createdDate;
    }
    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public Item01ProductResponse(String type, ClothesSize size, LocalDate createdDate, LocalDate updatedDate) {
        this.type = type;
        this.size = size;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public static Item01ProductResponse from(Item01ProductApiResponse apiResponse) {
        return new Item01ProductResponse(
                apiResponse.getType(),
                ClothesSize.valueOf(apiResponse.getSize()),
                LocalDate.parse(apiResponse.getCreatedAt()),
                LocalDate.parse(apiResponse.getUpdatedAt())
        );
    }
}
