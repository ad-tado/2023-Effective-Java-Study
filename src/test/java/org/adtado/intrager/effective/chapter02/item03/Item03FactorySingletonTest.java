package org.adtado.intrager.effective.chapter02.item03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;

class Item03FactorySingletonTest {

    @DisplayName("여러 번 호출해도 같은 인스턴스인가")
    @Test
    void sameInstanceTest() {
        // given
        Item03FactorySingleton firstInstance = Item03FactorySingleton.getInstance();
        Item03FactorySingleton secondInstance = Item03FactorySingleton.getInstance();
        Item03FactorySingleton thirdInstance = Item03FactorySingleton.getInstance();

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
        Constructor<Item03FactorySingleton> constructor = Item03FactorySingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        Item03FactorySingleton reflection = constructor.newInstance();
        Item03FactorySingleton singletonInstance = Item03FactorySingleton.getInstance();

        // when

        // then
        assertThat(reflection).isNotSameAs(singletonInstance);
        assertThat(reflection).isNotEqualTo(singletonInstance);
    }
}