package org.adtado.intrager.effective.chapter02.item01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class Item01ProductResponseTest {

    @DisplayName("from 팩토리 메서드가 재고를 생성한다")
    @Test
    void fromInstanceTest() {
        // given
        Item01ProductApiResponse product = Item01TestProduct.getInstance().getTestProduct();

        // when
        Item01ProductResponse stockedUp = Item01ProductResponse.from(product);

        // then
        assertThat(stockedUp.getType()).isEqualTo("Shirt");
        assertThat(stockedUp.getSize()).isEqualTo(ClothesSize.TOP_XL);
        assertThat(stockedUp.getCreatedDate()).isEqualTo(LocalDate.of(2023, 8, 7));
        assertThat(stockedUp.getUpdatedDate()).isEqualTo(LocalDate.of(2023, 8, 15));
    }

    @DisplayName("생성자를 통해 재고를 생성한다")
    @Test
    void constructorInstanceTest() {
        // given
        Item01ProductApiResponse product = Item01TestProduct.getInstance().getTestProduct();

        // when
        Item01ProductResponse stockedUp = new Item01ProductResponse(
                product.getType(),
                ClothesSize.valueOf(product.getSize()),
                LocalDate.parse(product.getCreatedAt()),
                LocalDate.parse(product.getUpdatedAt())
        );

        // then
        assertThat(stockedUp.getType()).isEqualTo("Shirt");
        assertThat(stockedUp.getSize()).isEqualTo(ClothesSize.TOP_XL);
        assertThat(stockedUp.getCreatedDate()).isEqualTo(LocalDate.of(2023, 8, 7));
        assertThat(stockedUp.getUpdatedDate()).isEqualTo(LocalDate.of(2023, 8, 15));
    }
}