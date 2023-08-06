package org.adtado.dhrdlxl.effective.item3.assignment;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {
        // 1. reflection에 의해 싱글턴이 깨진다.
        Elvis elvis1 = Elvis.INSTANCE;
        Elvis elvis2 = null;

        Constructor<Elvis> elvisConstructor = Elvis.class.getDeclaredConstructor();
        elvisConstructor.setAccessible(true);
        elvis2 = elvisConstructor.newInstance();

        if (elvis1 != elvis2) {
            log.info("[Elvis] Not singleton");
        }



        // 2. reflection에 의한 싱글턴 깨짐 방어.
        Presley presley1 = Presley.INSTANCE;
        Presley presley2 = null;

        try {
            Constructor<Presley> presleyConstructor = Presley.class.getDeclaredConstructor();
            presleyConstructor.setAccessible(true);
            presley2 = presleyConstructor.newInstance();
        } catch (InvocationTargetException e) {
            log.error("[Presley] fail to make instance.", e.getCause());
        }

    }
}
