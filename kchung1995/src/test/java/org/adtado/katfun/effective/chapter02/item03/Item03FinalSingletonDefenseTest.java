package org.adtado.katfun.effective.chapter02.item03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Item03FinalSingletonDefenseTest {
    @DisplayName("리플렉션을 이용해 싱글톤을 깨려고 시도할 경우 Exception이 발생한다")
    @Test
    void reflectionSingletonViolationTest() throws NoSuchMethodException {
        // given
        Constructor<Item03FinalSingletonDefense> constructor = Item03FinalSingletonDefense.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        // when

        // then
        Exception e = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertThat("class " + e.getCause())
                .isEqualTo(RuntimeException.class + ": Cannot create second instance of Item03FinalSingletonDefense.");
    }
}

