package org.adtado.intrager.effective.chapter02.item03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Item03FinalSingletonDefenseTest {

    @DisplayName("리플렉션을 이용해 싱글톤 파괴 시도 시 Exception 발생")
    @Test
    void reflectionSingletonViolationTest() throws NoSuchMethodException {
        // given
        Constructor<Item03FinalSingletonDefense> constructor = Item03FinalSingletonDefense.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        // when

        // then
        Exception e = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertThat("class " + e.getCause())
                .isEqualTo(RuntimeException.class + ": You can create only one INSTANCE of static final field");
    }
}