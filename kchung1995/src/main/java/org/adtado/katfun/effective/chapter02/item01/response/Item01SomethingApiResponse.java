package org.adtado.katfun.effective.chapter02.item01.response;

public class Item01SomethingApiResponse {
    private final String name;
    private final String koreanName;
    private final String userCategory;
    private final String createdAt;
    private final String updatedAt;


    public String getName() {
        return name;
    }

    public String getKoreanName() {
        return koreanName;
    }

    public String getUserCategory() {
        return userCategory;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Item01SomethingApiResponse(String name, String koreanName, String userCategory, String createdAt, String updatedAt) {
        this.name = name;
        this.koreanName = koreanName;
        this.userCategory = userCategory;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}

