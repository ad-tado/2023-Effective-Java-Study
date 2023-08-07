package org.adtado.verticalcode.effective.chap2.item3;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Controller
@Tag(name = "CHAP 2", description = "test")
@Slf4j
public class item3 {

    @GetMapping("/api/v1/test/item3")
    @Operation(summary = "Singleton 깨트리기")
    public void destroySingleton() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        SingletonEntity singletonEntity = SingletonEntity.getInstance();

        Constructor<SingletonEntity> constructor = SingletonEntity.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        SingletonEntity singletonEntity1 = constructor.newInstance();

        log.info(String.valueOf(singletonEntity == singletonEntity1));
        //false 출력 - 두 객체는 다른 객체 - Singleton이지만 두개 생성됨
    }
}
