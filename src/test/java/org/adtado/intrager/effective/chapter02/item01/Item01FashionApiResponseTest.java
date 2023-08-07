package org.adtado.intrager.effective.chapter02.item01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Item01FashionApiResponseTest {

    @DisplayName("stockUp 팩토리 메서드로 담긴 재고를 생성한다")
    @Test
    void stockUpProductTest() {
        // given
        Item01FashionConstructor product = Item01TestProduct.getInstance().getTestProduct();

        // when
        Item01FashionApiResponse stockedUp = Item01FashionApiResponse.stockUp(product);

        // then
        assertThat(stockedUp.getType()).isEqualTo("Shirt");
        assertThat(stockedUp.getSize()).isEqualTo(ClothesSize.TOP_XL);
        assertThat(stockedUp.getBrand()).isEqualTo(FashionBrand.MUSINSA_STANDARD);
    }

    @DisplayName("생성자를 통해 재고를 생성한다")
    @Test
    void stockUpUsingConstructor() {
        // given
        Item01FashionConstructor product = Item01TestProduct.getInstance().getTestProduct();

        // when
        Item01FashionApiResponse stockedUp = new Item01FashionApiResponse(
                product.getType(),
                ClothesSize.valueOf(product.getSize()),
                FashionBrand.valueOf(product.getBrand())
        );

        // then
        assertThat(stockedUp.getType()).isEqualTo("Shirt");
        assertThat(stockedUp.getSize()).isEqualTo(ClothesSize.TOP_XL);
        assertThat(stockedUp.getBrand()).isEqualTo(FashionBrand.MUSINSA_STANDARD);
    }
}