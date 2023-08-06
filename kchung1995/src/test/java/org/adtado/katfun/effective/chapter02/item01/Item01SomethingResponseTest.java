package org.adtado.katfun.effective.chapter02.item01;

import org.adtado.katfun.effective.chapter02.item01.response.Item01SomethingApiResponse;
import org.adtado.katfun.effective.chapter02.item01.response.Item01UserCategory;
import org.adtado.katfun.effective.chapter02.item01.stub.Item01SomethingStub;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class Item01SomethingResponseTest {

    @DisplayName("from 팩토리 메소드가 인스턴스를 생성한다")
    @Test
    void fromInstanceTest() {
        // given
        Item01SomethingApiResponse stub = Item01SomethingStub.getInstance().getFirstStub();

        // when
        Item01SomethingResponse result = Item01SomethingResponse.from(stub);

        // then
        assertThat(result.getName()).isEqualTo("Kuyho, 정규호");
        assertThat(result.getCategory()).isEqualTo(Item01UserCategory.KATFUN);
        assertThat(result.getCreatedDate()).isEqualTo(LocalDate.of(2023, 7, 30));
        assertThat(result.getUpdatedDate()).isEqualTo(LocalDate.of(2023, 7, 31));
    }

    @DisplayName("생성자를 통해 인스턴스를 생성한다")
    @Test
    void constructorInstanceTest() {
        // given
        Item01SomethingApiResponse stub = Item01SomethingStub.getInstance().getFirstStub();

        // when
        Item01SomethingResponse result = new Item01SomethingResponse(
                stub.getName() + ", " + stub.getKoreanName(),
                Item01UserCategory.valueOf(stub.getUserCategory()),
                LocalDate.parse(stub.getCreatedAt()),
                LocalDate.parse(stub.getUpdatedAt())
        );

        // then
        assertThat(result.getName()).isEqualTo("Kuyho, 정규호");
        assertThat(result.getCategory()).isEqualTo(Item01UserCategory.KATFUN);
        assertThat(result.getCreatedDate()).isEqualTo(LocalDate.of(2023, 7, 30));
        assertThat(result.getUpdatedDate()).isEqualTo(LocalDate.of(2023, 7, 31));
    }

}