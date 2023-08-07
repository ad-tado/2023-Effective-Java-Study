package org.adtado.nowns.effective.item3;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


@Slf4j
class SingletonTest {

    @Test
    void outOfSingleton() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        val singletonExceptionService = SIngletonExceptionService.INSTANCE;
        Constructor constructor = SIngletonExceptionService.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        val singletonExceptionService2 = constructor.newInstance();
        Assertions.assertNotEquals(singletonExceptionService, singletonExceptionService2);

    }
}
