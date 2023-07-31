package org.adtado.katfun.effective.chapter02.item03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Item03EnumSingletonTest {
    @DisplayName("여러 번 호출해도 같은 인스턴스이다")
    @Test
    void sameInstanceTest() {
        // given
        Item03EnumSingleton instanceOne = Item03EnumSingleton.INSTANCE;
        Item03EnumSingleton instanceTwo = Item03EnumSingleton.INSTANCE;
        Item03EnumSingleton instanceThree = Item03EnumSingleton.INSTANCE;

        // when

        // then
        assertThat(instanceOne).isSameAs(instanceTwo);
        assertThat(instanceTwo).isSameAs(instanceThree);
    }
}