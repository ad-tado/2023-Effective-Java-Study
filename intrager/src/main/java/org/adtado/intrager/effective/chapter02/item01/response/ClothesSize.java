package org.adtado.intrager.effective.chapter02.item01.response;

public enum ClothesSize {
    TOP_S("SMALL"),
    TOP_M("MEDIUM"),
    TOP_L("LARGE"),
    TOP_XL("EXTRA_LARGE");

    private final String size;

    ClothesSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
