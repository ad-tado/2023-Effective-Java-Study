package org.adtado.intrager.effective.chapter02.item01.response;

public class Item01ProductApiResponse {
    private final String type;
    private final String size;
    private String createdAt;
    private String updatedAt;

    public String getType() {
        return type;
    }
    public String getSize() {
        return size;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public String getUpdatedAt() {
        return updatedAt;
    }

    public Item01ProductApiResponse(String type, String size, String createdAt, String updatedAt) {
        this.type = type;
        this.size = size;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
