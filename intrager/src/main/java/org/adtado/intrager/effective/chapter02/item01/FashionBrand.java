package org.adtado.intrager.effective.chapter02.item01;

public enum FashionBrand {
    MUSINSA_STANDARD("무신사스탠다드"),
    COVERNAT("커버낫"),
    MARKGONZALES("마크곤잘레스"),
    DRAW_FIT("드로우핏");

    private final String brand;

    FashionBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }
}
