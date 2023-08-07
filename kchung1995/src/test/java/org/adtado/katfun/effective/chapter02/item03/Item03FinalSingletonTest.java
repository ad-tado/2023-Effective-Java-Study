package org.adtado.katfun.effective.chapter02.item03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;

class Item03FinalSingletonTest {
    @DisplayName("여러 번 호출해도 같은 인스턴스이다")
    @Test
    void sameInstanceTest() {
        // given
        Item03FinalSingleton instanceOne = Item03FinalSingleton.instance;
        Item03FinalSingleton instanceTwo = Item03FinalSingleton.instance;
        Item03FinalSingleton instanceThree = Item03FinalSingleton.instance;

        // when

        // then
        assertThat(instanceOne).isSameAs(instanceTwo);
        assertThat(instanceTwo).isSameAs(instanceThree);
    }

    @DisplayName("리플렉션을 이용해 싱글톤을 깰 수 있다")
    @Test
    void reflectionSingletonViolationTest() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // given
        Constructor<Item03FinalSingleton> constructor = Item03FinalSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Item03FinalSingleton reflection = constructor.newInstance();
        Item03FinalSingleton instanceSingleton = Item03FinalSingleton.instance;

        // when

        // then
        assertThat(reflection).isNotSameAs(instanceSingleton);
    }
}