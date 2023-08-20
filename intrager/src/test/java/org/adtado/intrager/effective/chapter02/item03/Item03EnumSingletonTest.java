package org.adtado.intrager.effective.chapter02.item03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Item03EnumSingletonTest {

    @DisplayName("여러 번 호출해도 같은 인스턴스이다")
    @Test
    void sameInstanceTest() {
        // given
        Item03EnumSingleton firstInstance = Item03EnumSingleton.INSTANCE;
        Item03EnumSingleton secondInstance = Item03EnumSingleton.INSTANCE;
        Item03EnumSingleton thirdInstance = Item03EnumSingleton.INSTANCE;

        // when

        // then
        assertThat(firstInstance).isSameAs(secondInstance);
        assertThat(secondInstance).isSameAs(thirdInstance);

        assertThat(firstInstance).isEqualTo(secondInstance);
        assertThat(secondInstance).isEqualTo(thirdInstance);
    }
}