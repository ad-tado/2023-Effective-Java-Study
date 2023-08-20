package org.adtado.intrager.effective.chapter02.item03;

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
        Item03FinalSingleton firstInstance = Item03FinalSingleton.INSTANCE;
        Item03FinalSingleton secondInstance = Item03FinalSingleton.INSTANCE;
        Item03FinalSingleton thirdInstance = Item03FinalSingleton.INSTANCE;

        // when

        // then
        assertThat(firstInstance).isSameAs(secondInstance);
        assertThat(secondInstance).isSameAs(thirdInstance);

        assertThat(firstInstance).isEqualTo(secondInstance);
        assertThat(secondInstance).isEqualTo(thirdInstance);
    }

    @DisplayName("리플렉션을 이용해 싱글톤을 깰 수 있다")
    @Test
    void reflectionSingletonViolationTest()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // given
        Constructor<Item03FinalSingleton> constructor = Item03FinalSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        Item03FinalSingleton reflection = constructor.newInstance();
        Item03FinalSingleton singletonInstance = Item03FinalSingleton.INSTANCE;

        // when

        // then
        assertThat(reflection).isNotSameAs(singletonInstance);
        assertThat(singletonInstance).isNotEqualTo(reflection);
    }
}